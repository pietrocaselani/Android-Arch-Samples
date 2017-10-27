package io.github.pietrocaselani.todolistrx.datasources

import android.arch.persistence.room.*
import io.github.pietrocaselani.todolistrx.list.TodoEntity
import io.reactivex.Flowable

@Dao
interface TodoDao {
	@Query("SELECT * FROM Todos WHERE done = 0")
	fun getTodos(): Flowable<List<TodoEntity>>

	@Query("SELECT * FROM Todos WHERE done = 0 AND text LIKE '%' || :text || '%'")
	fun searchTodos(text: String): Flowable<List<TodoEntity>>

	@Insert(onConflict = OnConflictStrategy.IGNORE)
	fun insert(todo: TodoEntity)

	@Update(onConflict = OnConflictStrategy.FAIL)
	fun markAsDone(todo: TodoEntity)
}