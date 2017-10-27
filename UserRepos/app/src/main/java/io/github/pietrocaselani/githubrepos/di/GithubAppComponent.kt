package io.github.pietrocaselani.githubrepos.di

import dagger.Component
import io.github.pietrocaselani.githubrepos.GithubApplication
import javax.inject.Singleton

/**
 * Created by pc on 06/06/17.
 */
@Singleton
@Component(modules = arrayOf(
        AppModule::class,
        RepositorySearchActivityModule::class
))
interface GithubAppComponent {

    fun inject(githubApplication: GithubApplication)

}

