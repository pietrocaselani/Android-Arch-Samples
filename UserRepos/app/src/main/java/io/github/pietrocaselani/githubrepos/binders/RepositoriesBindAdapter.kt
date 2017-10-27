package io.github.pietrocaselani.githubrepos.binders

import android.databinding.BindingAdapter
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.pietrocaselani.githubrepos.BR
import io.github.pietrocaselani.githubrepos.R
import io.github.pietrocaselani.githubrepos.search.RepositoryViewModel

/**
 * Created by pc on 07/06/17.
 */
@BindingAdapter("repositories")
fun bindRepositories(recyclerView: RecyclerView, repositories: List<RepositoryViewModel>) {

    recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
    recyclerView.adapter = RepositoriesAdapter(repositories)
}

private class RepositoriesAdapter(
        val repositories: List<RepositoryViewModel>
) : RecyclerView.Adapter<RepositoryViewHolder>() {

    override fun onBindViewHolder(holder: RepositoryViewHolder?, position: Int) {
        val repo = repositories[position]
        holder?.bind(repo)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RepositoryViewHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.repository_view_model_list_item, parent, false)
        return RepositoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return repositories.count()
    }

}

private class RepositoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding: ViewDataBinding = DataBindingUtil.bind(itemView)

    fun bind(repositoryViewModel: RepositoryViewModel) = with(itemView) {
        binding.setVariable(BR.viewModel, repositoryViewModel)
    }

}