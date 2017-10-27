package io.github.pietrocaselani.githubrepos.repository

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey

/**
 * Created by pc on 06/06/17.
 */
@Entity(tableName = "Repositories",
        foreignKeys = arrayOf(
                ForeignKey(
                        entity = UserEntity::class,
                        parentColumns = arrayOf("id"),
                        childColumns = arrayOf("userId")
                )
        ),
        indices = arrayOf(Index("userId"))
)
data class RepositoryEntity(
        @PrimaryKey var id: Long,
        var userId: Long,
        var name: String,
        var description: String?
)