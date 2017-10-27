package io.github.pietrocaselani.searchrepos.organization

import android.arch.paging.PagedListAdapter
import android.support.v7.recyclerview.extensions.DiffCallback
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class RepositoriesAdapter: PagedListAdapter<RepositoryResponse, RepositoryViewHolder>(DIFF_CALLBACK) {
	companion object {
		private val DIFF_CALLBACK = object : DiffCallback<RepositoryResponse>() {
			override fun areContentsTheSame(oldItem: RepositoryResponse, newItem: RepositoryResponse): Boolean = oldItem == newItem

			override fun areItemsTheSame(oldItem: RepositoryResponse, newItem: RepositoryResponse): Boolean = oldItem == newItem
		}
	}

	override fun onBindViewHolder(holder: RepositoryViewHolder?, position: Int) {
		val repository = getItem(position)

		if (repository != null) {
			var text = repository.name

			if (repository.language?.isNotEmpty() == true) {
				text += " (${repository.language})"
			}

			holder?.textView?.text = text
		} else {
			holder?.textView?.text = null
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RepositoryViewHolder {
		val view = LayoutInflater.from(parent?.context).inflate(android.R.layout.simple_list_item_1, parent, false)
		return RepositoryViewHolder(view)
	}

}

class RepositoryViewHolder(view: View): RecyclerView.ViewHolder(view) {
	val textView = view.findViewById<TextView>(android.R.id.text1)!!
}