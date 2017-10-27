package io.github.pietrocaselani.githubrepos.repository

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Flowable

/**
 * Created by pc on 06/06/17.
 */
@Dao
interface RoomUserDao {

    @Query("SELECT * FROM Users")
    fun allUsers(): Flowable<List<UserEntity>>

    @Query("SELECT * FROM Users WHERE name = :p0 LIMIT 1")
    fun findUser(name: String): Flowable<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: UserEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(repositories: List<RepositoryEntity>)

    @Query("SELECT * FROM Repositories WHERE userId = :p0")
    fun findRepositoriesForUser(userId: Long): Flowable<List<RepositoryEntity>>

}