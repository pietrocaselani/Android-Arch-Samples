package io.github.pietrocaselani.githubrepos.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.github.pietrocaselani.githubrepos.search.RepositorySearchActivity

/**
 * Created by pc on 07/06/17.
 */
@Module
abstract class RepositorySearchActivityModule {

    @ContributesAndroidInjector
    abstract fun provideRepositorySeachActivityInjector(): RepositorySearchActivity

}