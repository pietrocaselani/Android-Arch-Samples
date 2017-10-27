package io.github.pietrocaselani.todolistrx

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.github.pietrocaselani.todolistrx.list.TodoListFragment

class TodoListActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_todo_list)

		setupFragment()
	}

	private fun setupFragment() {
		val tag = "TodoListFragment"
		var fragment = supportFragmentManager.findFragmentByTag(tag) as? TodoListFragment

		if (fragment == null) {
			fragment = TodoListFragment()
			supportFragmentManager.beginTransaction()
					.add(R.id.fragment_container, fragment, tag)
					.commit()
		}
	}
}
