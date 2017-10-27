package io.github.pietrocaselani.todolistpaging.list

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.PagedList
import android.util.Log
import io.github.pietrocaselani.todolistpaging.AppExecutors
import io.github.pietrocaselani.todolistpaging.datasources.TodoDao
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.internal.operators.completable.CompletableFromAction

class TodoListViewModel(private val dataSource: TodoDao, private val executors: AppExecutors) : ViewModel() {
	val todos: LiveData<PagedList<TodoEntity>>

	init {
		val initialPosition = 0

		val config = PagedList.Config.Builder()
				.setPageSize(10)
				.setPrefetchDistance(20)
				.setEnablePlaceholders(true)
				.build()

		todos = dataSource.getTodos().create(initialPosition, config)
	}

	fun start() {
		fillDB()
	}

	fun selectTodoAt(position: Int): Completable {
		val todoEntity = todos.value?.get(position) ?: return Completable.complete()

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

	private fun fillDB() {
		val single = Single.fromCallable {
			val todos = 0.rangeTo(150).map {
				TodoEntity(text = "Task $it", done = false)
			}

			return@fromCallable dataSource.insert(todos)
		}

		single.subscribeOn(executors.io())
				.observeOn(executors.main())
				.subscribe({
					Log.v("PC", it.toString())
				}, {
					Log.e("PC", it.localizedMessage)
				})
	}
}