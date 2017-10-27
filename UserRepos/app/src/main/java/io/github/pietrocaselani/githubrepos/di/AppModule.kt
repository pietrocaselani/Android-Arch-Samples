package io.github.pietrocaselani.githubrepos.di

import android.arch.persistence.room.Room
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import io.github.pietrocaselani.githubrepos.GithubApplication
import io.github.pietrocaselani.githubrepos.api.GithubService
import io.github.pietrocaselani.githubrepos.repository.AppDatabase
import io.github.pietrocaselani.githubrepos.repository.RoomUserDao
import io.github.pietrocaselani.githubrepos.repository.UserDao
import io.github.pietrocaselani.githubrepos.search.RepositorySearchInteractor
import io.github.pietrocaselani.githubrepos.search.RepositorySearchInteractorInput
import io.github.pietrocaselani.githubrepos.search.RepositorySearchViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by pc on 06/06/17.
 */
@Module
class AppModule(val githubApplication: GithubApplication) {

    @Provides
    fun provideApplication(): GithubApplication {
        return githubApplication
    }

    @Provides
    @Singleton
    fun provideGithubService(): GithubService {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        return retrofit.create(GithubService::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(application: GithubApplication): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, AppDatabase.DATABASE_NAME).build()
    }

    @Provides
    @Singleton
    fun provideRoomUserDao(appDatabase: AppDatabase): RoomUserDao {
        return appDatabase.userDao()
    }

    @Provides
    @Singleton
    fun provideUserDao(userDao: RoomUserDao): UserDao {
        return UserDao(userDao)
    }

    @Provides
    @Singleton
    fun provideRepositorySearchInteractor(userDao: UserDao, githubService: GithubService): RepositorySearchInteractorInput {
        return RepositorySearchInteractor(userDao, githubService)
    }

    @Provides
    @Singleton
    fun provideRepositorySearchViewModel(interactorInput: RepositorySearchInteractorInput): RepositorySearchViewModel {
        return RepositorySearchViewModel(interactorInput)
    }

}