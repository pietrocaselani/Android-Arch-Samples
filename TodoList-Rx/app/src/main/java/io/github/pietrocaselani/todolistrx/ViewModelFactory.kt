package io.github.pietrocaselani.todolistrx

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import io.github.pietrocaselani.todolistrx.datasources.TodoDao
import io.github.pietrocaselani.todolistrx.list.TodoListViewModel

class ViewModelFactory private constructor(private val dataSource: TodoDao) : ViewModelProvider.NewInstanceFactory() {
	companion object {
		fun instance(dataSource: TodoDao): ViewModelFactory {
			return ViewModelFactory(dataSource)
		}
	}

	override fun <T : ViewModel?> create(modelClass: Class<T>?): T {
		if (modelClass?.isAssignableFrom(TodoListViewModel::class.java) == true) {
			return TodoListViewModel(dataSource, AppExecutors) as T
		}

		return super.create(modelClass)
	}
}