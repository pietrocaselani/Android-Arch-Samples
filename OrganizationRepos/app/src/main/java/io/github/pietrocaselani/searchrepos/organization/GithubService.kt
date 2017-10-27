package io.github.pietrocaselani.searchrepos.organization

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {
	@GET("orgs/{org_name}/repos")
	fun organizationRepos(@Path("org_name") name: String,
	                      @Query("page") page: Int,
	                      @Query("per_page") perPage: Int = 10): Call<List<RepositoryResponse>>
}