package io.github.pietrocaselani.githubrepos.search

import io.github.pietrocaselani.githubrepos.repository.UserEntity
import io.reactivex.Flowable

/**
 * Created by pc on 06/06/17.
 */
interface RepositorySearchInteractorInput {

    fun searchRepositoriesForUser(username: String): Flowable<UserEntity>

}