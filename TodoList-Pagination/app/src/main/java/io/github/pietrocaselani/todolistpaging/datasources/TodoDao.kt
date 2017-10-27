package io.github.pietrocaselani.todolistpaging.datasources

import android.arch.paging.LivePagedListProvider
import android.arch.persistence.room.*
import io.github.pietrocaselani.todolistpaging.list.TodoEntity

@Dao
interface TodoDao {
	@Query("SELECT * FROM Todos WHERE done = 0")
	fun getTodos(): LivePagedListProvider<Int, TodoEntity>

	@Insert(onConflict = OnConflictStrategy.IGNORE)
	fun insert(todo: TodoEntity): Long

	@Insert(onConflict = OnConflictStrategy.IGNORE)
	fun insert(todos: List<TodoEntity>): List<Long>

	@Update(onConflict = OnConflictStrategy.FAIL)
	fun markAsDone(todo: TodoEntity)
}