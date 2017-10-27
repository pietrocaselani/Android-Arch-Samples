package io.github.pietrocaselani.githubrepos.search

import android.databinding.BaseObservable
import android.databinding.ObservableField
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by pc on 06/06/17.
 */
class RepositorySearchViewModel @Inject constructor(
        private val interactor: RepositorySearchInteractorInput
): BaseObservable() {

    val queryText = ObservableField<String>()
    val repositories = ObservableField<List<RepositoryViewModel>>(emptyList())

    private val disposables = CompositeDisposable()

    fun search() {
        val username = queryText.get()

        val disposable = interactor.searchRepositoriesForUser(username).map {
            it.repositories.map {
                RepositoryViewModel(it.name, it.description)
            }
        }.observeOn(AndroidSchedulers.mainThread()).subscribe {
            repositories.set(it)
        }
        disposables.add(disposable)
    }

    fun onStop() {
        disposables.clear()
    }

}