package io.github.pietrocaselani.githubrepos.repository

import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Created by pc on 06/06/17.
 */
class UserDao @Inject constructor(private val userDao: RoomUserDao) {

    fun insertUser(user: UserEntity) {
        userDao.insert(user)
        userDao.insert(user.repositories)
    }

    fun findUser(username: String): Flowable<UserEntity> {
        return userDao.findUser(username).flatMap { user ->
            userDao.findRepositoriesForUser(user.id).flatMap {
                user.repositories = it
                Flowable.just(user)
            }
        }
    }

}