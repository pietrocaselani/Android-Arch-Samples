package io.github.pietrocaselani.githubrepos.search

import io.github.pietrocaselani.githubrepos.api.GithubService
import io.github.pietrocaselani.githubrepos.repository.RepositoryEntity
import io.github.pietrocaselani.githubrepos.repository.UserDao
import io.github.pietrocaselani.githubrepos.repository.UserEntity
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by pc on 06/06/17.
 */
class RepositorySearchInteractor @Inject constructor(
        private val userDao: UserDao,
        private val githubService: GithubService
) : RepositorySearchInteractorInput {

    override fun searchRepositoriesForUser(username: String): Flowable<UserEntity> {
        val database = fetchFromDatabase(username)
        val api = fetchFromAPI(username)

        return Flowable.merge(database, api)
                .distinct()
                .subscribeOn(Schedulers.io())
    }

    private fun fetchFromDatabase(username: String): Flowable<UserEntity> {
        return userDao.findUser(username)
    }

    private fun fetchFromAPI(username: String): Flowable<UserEntity> {
        return githubService.getRepositories(username).flatMap {
            val repositories = it.map {
                RepositoryEntity(it.id, it.owner.id, it.name, it.description)
            }.toList()

            val owner = it.first().owner

            Flowable.just(UserEntity(owner.id, owner.login, repositories))
        }.doOnNext {
            userDao.insertUser(it)
        }
    }

}