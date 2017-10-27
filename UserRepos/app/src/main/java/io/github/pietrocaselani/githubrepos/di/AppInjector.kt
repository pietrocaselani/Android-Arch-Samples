package io.github.pietrocaselani.githubrepos.di

import android.app.Activity
import android.app.Application
import android.os.Bundle
import dagger.android.AndroidInjection
import io.github.pietrocaselani.githubrepos.GithubApplication

/**
 * Created by pc on 06/06/17.
 */
class AppInjector {

    companion object {
        fun init(githubApplication: GithubApplication): GithubAppComponent {
            val appComponent = DaggerGithubAppComponent.builder()
                    .appModule(AppModule(githubApplication))
                    .build()

            appComponent.inject(githubApplication)

            val value: Application.ActivityLifecycleCallbacks = object : Application.ActivityLifecycleCallbacks {
                override fun onActivityPaused(p0: Activity?) {}

                override fun onActivityResumed(p0: Activity?) {}

                override fun onActivityStarted(p0: Activity?) {}

                override fun onActivityDestroyed(p0: Activity?) {}

                override fun onActivitySaveInstanceState(p0: Activity?, p1: Bundle?) {}

                override fun onActivityStopped(p0: Activity?) {}

                override fun onActivityCreated(p0: Activity?, p1: Bundle?) {
                    if (p0 is Injectable) {
                        AndroidInjection.inject(p0)
                    }
                }

            }

            githubApplication.registerActivityLifecycleCallbacks(value)

            return appComponent
        }
    }

}