package io.github.pietrocaselani.githubrepos.repository

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey

/**
 * Created by pc on 06/06/17.
 */
@Entity(tableName = "Users",
        indices = arrayOf(
                Index("name", unique = true)
        )
)
data class UserEntity(
        @PrimaryKey var id: Long,
        var name: String,
        @Ignore var repositories: List<RepositoryEntity> = listOf()
) {
    constructor(id: Long, name: String) : this(id = id, name = name, repositories = listOf())

    override fun equals(other: Any?): Boolean {
        if (super.equals(other) && other is UserEntity) {
            return repositories == other.repositories
        }

        return false
    }

    override fun hashCode(): Int {
        return super.hashCode() * repositories.hashCode() * 11
    }
}