package io.github.pietrocaselani.searchrepos.organization

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.DataSource
import android.arch.paging.LivePagedListProvider
import android.arch.paging.PagedList

class OrganizationViewModel(private val githubService: GithubService): ViewModel() {

	fun search(organizationName: String): LiveData<PagedList<RepositoryResponse>> {
		val value = object : LivePagedListProvider<Int, RepositoryResponse>() {
			override fun createDataSource(): DataSource<Int, RepositoryResponse> {
				return OrganizationDataSource(organizationName, githubService)
			}
		}

		val config = PagedList.Config.Builder()
				.setPageSize(10)
				.setInitialLoadSizeHint(10)
				.setEnablePlaceholders(false)
				.build()

		return value.create(0, config)
	}

}