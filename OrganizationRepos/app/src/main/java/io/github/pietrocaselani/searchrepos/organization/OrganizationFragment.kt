package io.github.pietrocaselani.searchrepos.organization


import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.pietrocaselani.searchrepos.R
import io.github.pietrocaselani.searchrepos.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_organization.*

class OrganizationFragment : Fragment() {

	private val viewModel: OrganizationViewModel by lazy {
		ViewModelFactory.create(OrganizationViewModel::class.java)
	}

	private val repositoriesAdapter: RepositoriesAdapter by lazy {
		RepositoriesAdapter()
	}

	override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
	                          savedInstanceState: Bundle?): View? {
		return inflater!!.inflate(R.layout.fragment_organization, container, false)
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)

		setupSearchWidgets()
		setupRecyclerView()
	}

	private fun setupRecyclerView() {
		recyclerview.layoutManager = LinearLayoutManager(activity)
		recyclerview.adapter = repositoriesAdapter
	}

	private fun setupSearchWidgets() {
		button.setOnClickListener {
			val organizationName = editText.text.toString()
			performSearch(organizationName)
		}
	}

	private fun performSearch(organizationName: String) {
		val pagedList = viewModel.search(organizationName)

		pagedList.observe(this, Observer { repositoriesAdapter.setList(it) })
	}

}
