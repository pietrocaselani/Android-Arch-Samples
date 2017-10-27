package io.github.pietrocaselani.searchrepos

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import io.github.pietrocaselani.searchrepos.organization.OrganizationViewModel

object ViewModelFactory : ViewModelProvider.NewInstanceFactory() {

	override fun <T : ViewModel?> create(modelClass: Class<T>?): T {
		if (modelClass?.isAssignableFrom(OrganizationViewModel::class.java) == true) {
			return OrganizationViewModel(Environment.githubService) as T
		}

		return super.create(modelClass)
	}
}