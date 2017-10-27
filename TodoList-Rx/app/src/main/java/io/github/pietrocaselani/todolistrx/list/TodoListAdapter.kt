package io.github.pietrocaselani.todolistrx.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.TextView

class TodoListAdapter(
		private var viewModels: List<TodoViewModel>,
		private val clickListener: OnClickListener) : RecyclerView.Adapter<TodoListViewHolder>() {

	fun update(viewModels: List<TodoViewModel>) {
		this.viewModels = viewModels
		notifyDataSetChanged()
	}

	override fun onBindViewHolder(holder: TodoListViewHolder?, position: Int) {
		val viewModel = viewModels[position]
		holder?.textView?.text = viewModel.title
	}

	override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): TodoListViewHolder {
		val view = LayoutInflater.from(parent?.context).inflate(android.R.layout.simple_list_item_1, parent, false)
		return TodoListViewHolder(view, clickListener)
	}

	override fun getItemCount(): Int {
		return viewModels.count()
	}
}

class TodoListViewHolder(view: View, clickListener: OnClickListener) : RecyclerView.ViewHolder(view) {
	val textView: TextView = view.findViewById(android.R.id.text1)

	init {
		view.setOnClickListener(clickListener)
	}
}