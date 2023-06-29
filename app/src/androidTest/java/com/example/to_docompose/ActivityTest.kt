package com.example.to_docompose

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.example.to_docompose.data.local.ToDoDao
import com.example.to_docompose.data.local.ToDoDatabase
import com.example.to_docompose.data.models.Priority
import com.example.to_docompose.presentation.MainActivity
import com.example.to_docompose.util.TestTags
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject


@HiltAndroidTest
class ActivityTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Inject
    lateinit var database: ToDoDatabase

    private lateinit var dao: ToDoDao

    @Before
    fun setup() {
        hiltRule.inject()
        dao = database.toDoDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun verifyTaskList() {
        runBlocking {
            val job = async { dao.getAllTasksRealTime().first() }
            val listSize = job.await().size

            val layoutTag = if (listSize == 0) {
                TestTags.EMPTY_LIST
            } else TestTags.TASK_LIST

            with(composeTestRule) {
                onNodeWithTag(layoutTag).assertIsDisplayed()
            }
        }
    }

    @Test
    fun testDeleteAll() {
        with(composeTestRule) {
            onNodeWithTag(TestTags.MORE_OPTIONS_TOOLBAR).performClick()
            onNodeWithTag(TestTags.DELETE_ALL_MORE_OPTION).performClick()
            onNodeWithTag(TestTags.EMPTY_LIST).assertIsDisplayed()
        }
    }

    @Test
    fun testDeleteSelectedTask() {
        runBlocking {
            val job = async { dao.getAllTasksRealTime().first() }
            val list = job.await()
            val listSize = list.size
            val taskId = list.firstOrNull()?.id ?: 0

            with(composeTestRule) {
                onNodeWithTag(TestTags.taskItem(taskId)).performClick()
                onNodeWithTag(TestTags.TASK_SCREEN).assertIsDisplayed()
                onNodeWithTag(TestTags.DELETE_TASK_TOOLBAR_OPTION).performClick()
                if (listSize > 1) {
                    onNodeWithTag(TestTags.TASK_LIST).assertIsDisplayed()
                    onNodeWithTag(TestTags.taskItem(taskId)).assertDoesNotExist()
                } else {
                    onNodeWithTag(TestTags.EMPTY_LIST).assertIsDisplayed()
                }
            }
        }
    }

    @Test
    fun testAddTask() {
        runBlocking {
            val job = async { dao.getAllTasksRealTime().first() }
            val list = job.await()
            val listSize = list.size

            val testTitle = "Reunião"
            val testDescription = "1:1 com o TechLead no final da tarde do dia 30/06/2023"
            val testPriority = Priority.HIGH

            with(composeTestRule) {
                onNodeWithTag(TestTags.FAB_BUTTON).performClick()
                onNodeWithTag(TestTags.TASK_SCREEN).assertIsDisplayed()
                onNodeWithTag(TestTags.TITLE_TEXT).performClick()
                onNodeWithTag(TestTags.TITLE_TEXT).performTextInput(testTitle)
                onNodeWithTag(TestTags.DROP_DOWN_MENU_PRIORITY).performClick()
                onNodeWithTag(TestTags.DROP_DOWN_ITEM_HIGH).performClick()
                onNodeWithTag(TestTags.DESCRIPTION_TEXT).performTextInput(testDescription)
                onNodeWithTag(TestTags.CONFIRM_TASK_ICON).performClick()
                onNodeWithTag(TestTags.LIST_SCREEN).assertIsDisplayed()

                val newJob = async { dao.getAllTasksRealTime().first() }
                val newList = newJob.await()
                val newListSize = newList.size

                assert(newListSize == listSize + 1)

                val addedTask = newList.first {
                    it.description == testDescription &&
                            it.title == testTitle &&
                            it.priority == testPriority
                }

                onNodeWithTag(TestTags.taskItem(addedTask.id)).assertExists()
            }
        }
    }
}