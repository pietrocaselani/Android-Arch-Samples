package io.github.pietrocaselani.todolistrx.list

import android.databinding.Observable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import io.github.pietrocaselani.todolistrx.Environment
import io.github.pietrocaselani.todolistrx.R
import io.github.pietrocaselani.todolistrx.ViewModelFactory
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_todo_list.*

class TodoListFragment : Fragment() {
	private val disposables = CompositeDisposable()

	private lateinit var adapter: TodoListAdapter

	private val viewModel: TodoListViewModel by lazy {
		val environment = Environment.instance(activity.application)
		val dataSource = environment.dataSource
		ViewModelFactory.instance(dataSource).create(TodoListViewModel::class.java)
	}

	private val propertyChangeCallback = object : Observable.OnPropertyChangedCallback() {
		override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
			adapter.update(viewModel.todos.get())
		}
	}

	override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		return inflater?.inflate(R.layout.fragment_todo_list, container, false)
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		setHasOptionsMenu(true)

		viewModel.todos.addOnPropertyChangedCallback(propertyChangeCallback)
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)

		setupRecyclerView()
		setupAddTodoWidgets()
		setupSearchWidgets()

		viewModel.start()
	}

	override fun onStop() {
		disposables.dispose()
		super.onStop()
	}

	override fun onDestroy() {
		viewModel.todos.removeOnPropertyChangedCallback(propertyChangeCallback)
		super.onDestroy()
	}

	private fun setupAddTodoWidgets() {
		buttonAdd.setOnClickListener {
			val text = editText.text.toString()
			val disposable = viewModel.addTodo(text).subscribe()
			disposables.add(disposable)
		}
	}

	private fun setupSearchWidgets() {
		buttonSearch.setOnClickListener {
			val text = editText.text.toString()
			viewModel.search(text)
		}
	}

	private fun setupRecyclerView() {
		adapter = TodoListAdapter(emptyList(), OnClickListener {
			recyclerView.getChildAdapterPosition(it).let {
				val disposable = viewModel.selectTodoAt(it).subscribe()
				disposables.add(disposable)
			}
		})

		recyclerView.layoutManager = LinearLayoutManager(activity)
		recyclerView.adapter = adapter
	}
}