package io.github.pietrocaselani.githubrepos.api

/**
 * Created by pc on 06/06/17.
 */
data class RepositoryResponse(
        val id: Long,
        val name: String,
        val description: String?,
        val owner: OwnerResponse
)