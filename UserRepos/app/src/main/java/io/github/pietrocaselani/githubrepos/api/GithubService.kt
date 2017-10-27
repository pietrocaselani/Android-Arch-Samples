package io.github.pietrocaselani.githubrepos.api

import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by pc on 06/06/17.
 */
interface GithubService {

    @GET("users/{user}/repos")
    fun getRepositories(@Path("user") user: String): Flowable<List<RepositoryResponse>>

}