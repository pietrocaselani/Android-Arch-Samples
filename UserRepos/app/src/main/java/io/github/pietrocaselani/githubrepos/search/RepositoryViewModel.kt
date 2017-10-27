package io.github.pietrocaselani.githubrepos.search

import android.databinding.BaseObservable

/**
 * Created by pc on 07/06/17.
 */
data class RepositoryViewModel(
        val name: String,
        val description: String?
): BaseObservable()