package io.github.pietrocaselani.searchrepos

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.github.pietrocaselani.searchrepos.organization.OrganizationFragment

class MainActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		setupFragmet()
	}

	private fun setupFragmet() {
		val tag = "OrganizationFragment"
		var fragment = supportFragmentManager.findFragmentByTag(tag) as? OrganizationFragment

		if (fragment == null) {
			fragment = OrganizationFragment()
			supportFragmentManager.beginTransaction()
					.add(R.id.fragment_container, fragment, tag)
					.commit()
		}
	}
}
