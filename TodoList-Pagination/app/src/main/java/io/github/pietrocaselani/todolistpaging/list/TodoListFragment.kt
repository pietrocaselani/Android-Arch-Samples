package io.github.pietrocaselani.todolistpaging.list

import android.arch.lifecycle.Observer
import android.arch.paging.PagedList
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import io.github.pietrocaselani.todolistpaging.Environment
import io.github.pietrocaselani.todolistpaging.R
import io.github.pietrocaselani.todolistpaging.ViewModelFactory
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_todo_list.*

class TodoListFragment : Fragment() {
	companion object {
		val TAG = TodoListFragment::javaClass.name
	}

	private val disposables = CompositeDisposable()

	private lateinit var adapter: TodoListAdapter

	private val viewModel: TodoListViewModel by lazy {
		val environment = Environment.instance(activity.application)
		val dataSource = environment.dataSource
		ViewModelFactory.instance(dataSource).create(TodoListViewModel::class.java)
	}

	override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		return inflater?.inflate(R.layout.fragment_todo_list, container, false)
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		setHasOptionsMenu(true)
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)

		setupRecyclerView()
		setupAddTodoWidgets()

		viewModel.start()
	}

	override fun onStop() {
		disposables.dispose()
		super.onStop()
	}

	private fun setupAddTodoWidgets() {
		button.setOnClickListener {
			val text = editText.text.toString()
			val disposable = viewModel.addTodo(text).subscribe()
			disposables.add(disposable)
		}
	}

	private fun setupRecyclerView() {
		adapter = TodoListAdapter(OnClickListener {
			recyclerView.getChildAdapterPosition(it).let {
				val disposable = viewModel.selectTodoAt(it).subscribe()
				disposables.add(disposable)
			}
		})

		recyclerView.layoutManager = LinearLayoutManager(activity)
		recyclerView.adapter = adapter

		viewModel.todos.observe(this, Observer<PagedList<TodoEntity>> { pagedList -> adapter.setList(pagedList) })
	}
}