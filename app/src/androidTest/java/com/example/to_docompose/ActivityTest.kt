package com.example.to_docompose

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.example.to_docompose.data.local.ToDoDao
import com.example.to_docompose.data.local.ToDoDatabase
import com.example.to_docompose.presentation.MainActivity
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
                "EmptyListLayoutTestTag"
            } else "TaskListLayoutTestTag"

            with(composeTestRule) {
                onNodeWithTag(layoutTag).assertIsDisplayed()
            }
        }
    }


}