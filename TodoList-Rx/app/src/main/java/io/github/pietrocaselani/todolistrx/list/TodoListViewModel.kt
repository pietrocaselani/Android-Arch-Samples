package io.github.pietrocaselani.todolistrx.list

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.util.Log
import io.github.pietrocaselani.todolistrx.AppExecutors
import io.github.pietrocaselani.todolistrx.datasources.TodoDao
import io.reactivex.Completable
import io.reactivex.internal.operators.completable.CompletableFromAction

class TodoListViewModel(private val dataSource: TodoDao, private val executors: AppExecutors) : ViewModel() {
	private var allTodos = emptyList<TodoEntity>()
	val todos = ObservableField<List<TodoViewModel>>(emptyList())

	fun start() {
		dataSource.getTodos()
				.doOnNext { allTodos = it }
				.map { it.map { TodoViewModel(it.text) } }
				.observeOn(executors.main())
				.subscribe({
					todos.set(it)
				}, {
					Log.e("TodoListViewModel", it.localizedMessage)
				}, {
					Log.v("TodoListViewModel", "onCompleted")
				})
	}

	fun selectTodoAt(position: Int): Completable {
		val todoEntity = allTodos[position]
		todoEntity.done = true

		return CompletableFromAction({
			dataSource.markAsDone(todoEntity)
		}).subscribeOn(executors.io())
	}

	fun addTodo(text: String): Completable {
		return CompletableFromAction({
			val todoEntity = TodoEntity(null, text, false)
			dataSource.insert(todoEntity)
		}).subscribeOn(executors.io())
	}

	fun search(text: String) {
		dataSource.searchTodos(text)
				.map { it.map { TodoViewModel(it.text) } }
				.observeOn(executors.main())
				.subscribe({
					todos.set(it)
				}, {
					Log.e("TodoListViewModel", it.localizedMessage)
				})
	}
}