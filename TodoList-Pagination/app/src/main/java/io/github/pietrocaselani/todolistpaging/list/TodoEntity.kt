package io.github.pietrocaselani.todolistpaging.list

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "Todos")
data class TodoEntity(
		@PrimaryKey(autoGenerate = true) var id: Int? = null,
		var text: String,
		var done: Boolean) {
	constructor() : this(0, "", false)
}