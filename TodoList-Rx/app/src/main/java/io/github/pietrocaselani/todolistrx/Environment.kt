package io.github.pietrocaselani.todolistrx

import android.app.Application
import android.arch.persistence.room.Room
import io.github.pietrocaselani.todolistrx.datasources.TodoDao
import io.github.pietrocaselani.todolistrx.datasources.TodoDatabase

class Environment private constructor(application: Application) {
	companion object {
		fun instance(application: Application): Environment = Environment(application)
	}

	val dataSource: TodoDao

	init {
		val database = Room.databaseBuilder(application, TodoDatabase::class.java, TodoDatabase.DATABASE_NAME).build()
		dataSource = database.todoDao()
	}
}