package io.github.pietrocaselani.todolistpaging

import android.app.Application
import android.arch.persistence.room.Room
import io.github.pietrocaselani.todolistpaging.datasources.TodoDatabase

class Environment private constructor(application: Application) {
	companion object {
		fun instance(application: Application): Environment = Environment(application)
	}

	private val database = Room.databaseBuilder(application, TodoDatabase::class.java, TodoDatabase.DATABASE_NAME).build()

	val dataSource = database.todoDao()
}