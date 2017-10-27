package io.github.pietrocaselani.githubrepos.search

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.github.pietrocaselani.githubrepos.GithubApplication
import io.github.pietrocaselani.githubrepos.R
import io.github.pietrocaselani.githubrepos.databinding.ActivityRepositorySearchBinding
import io.github.pietrocaselani.githubrepos.di.Injectable
import javax.inject.Inject

class RepositorySearchActivity : AppCompatActivity(), Injectable {

    @Inject lateinit var viewModel: RepositorySearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding =  DataBindingUtil.
                setContentView<ActivityRepositorySearchBinding>(this,
                R.layout.activity_repository_search)
        binding.viewModel = viewModel
    }

    override fun onStop() {
        super.onStop()

        viewModel.onStop()
    }
}
