package io.github.pietrocaselani.todolistrx.list

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "Todos")
data class TodoEntity(
		@PrimaryKey(autoGenerate = true) var id: Int?,
		var text: String,
		var done: Boolean) {
	constructor() : this(0, "", false)
}