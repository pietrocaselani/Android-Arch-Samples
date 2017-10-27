package io.github.pietrocaselani.todolistpaging.datasources

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import io.github.pietrocaselani.todolistpaging.list.TodoEntity

@Database(entities = arrayOf(TodoEntity::class), version = 1)
abstract class TodoDatabase : RoomDatabase() {
	companion object {
		const val DATABASE_NAME = "todos.sqlite"
	}

	abstract fun todoDao(): TodoDao

}