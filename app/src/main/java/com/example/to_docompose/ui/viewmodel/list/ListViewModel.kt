package com.example.to_docompose.ui.viewmodel.list

import com.example.to_docompose.domain.repository.ToDoRepository
import com.example.to_docompose.ui.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val repository: ToDoRepository
) : BaseViewModel<ListContract.Event, ListContract.State, ListContract.Effect>() {
    override fun setInitialState(): ListContract.State {
        TODO("Not yet implemented")
    }

    override fun handleEvents(event: ListContract.Event) {
        TODO("Not yet implemented")
    }

}