package io.github.pietrocaselani.githubrepos.repository

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

/**
 * Created by pc on 06/06/17.
 */
@Database(
        entities = arrayOf(UserEntity::class, RepositoryEntity::class),
        version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): RoomUserDao

    companion object {
        const val DATABASE_NAME = "GithubUsersDatabase"
    }

}