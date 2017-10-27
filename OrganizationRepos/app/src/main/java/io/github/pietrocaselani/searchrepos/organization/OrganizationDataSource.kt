package io.github.pietrocaselani.searchrepos.organization

import android.arch.paging.DataSource
import android.arch.paging.TiledDataSource

class OrganizationDataSource(
		private val organizationName: String,
		private val githubService: GithubService) : TiledDataSource<RepositoryResponse>() {

	private var currentPage = 1

	override fun loadRange(startPosition: Int, count: Int): MutableList<RepositoryResponse> {
		val call = githubService.organizationRepos(organizationName, currentPage++, count)

		val repositories = call.execute().body()?.toMutableList()

		return repositories ?: mutableListOf()
	}

	override fun countItems(): Int = DataSource.COUNT_UNDEFINED

}