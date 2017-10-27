package io.github.pietrocaselani.todolistpaging.list

import android.arch.paging.PagedListAdapter
import android.support.v7.recyclerview.extensions.DiffCallback
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.TextView

class TodoListAdapter(private val clickListener: OnClickListener) :
		PagedListAdapter<TodoEntity, TodoListViewHolder>(DIFF_CALLBACK) {

	companion object {
		private val DIFF_CALLBACK = object: DiffCallback<TodoEntity>() {
			override fun areItemsTheSame(oldItem: TodoEntity, newItem: TodoEntity): Boolean {
				return oldItem.id == newItem.id
			}

			override fun areContentsTheSame(oldItem: TodoEntity, newItem: TodoEntity): Boolean {
				return oldItem == newItem
			}
		}
	}

	override fun onBindViewHolder(holder: TodoListViewHolder?, position: Int) {
		val viewModel = getItem(position)
		holder?.textView?.text = viewModel?.text
	}

	override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): TodoListViewHolder {
		val view = LayoutInflater.from(parent?.context).inflate(android.R.layout.simple_list_item_1, parent, false)
		return TodoListViewHolder(view, clickListener)
	}
}

class TodoListViewHolder(view: View, clickListener: OnClickListener) : RecyclerView.ViewHolder(view) {
	val textView: TextView = view.findViewById(android.R.id.text1)

	init {
		view.setOnClickListener(clickListener)
	}
}