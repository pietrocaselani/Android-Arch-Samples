package io.github.pietrocaselani.todolistpaging

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object AppExecutors {
	fun io(): Scheduler {
		return Schedulers.io()
	}

	fun computation(): Scheduler {
		return Schedulers.computation()
	}

	fun main(): Scheduler {
		return AndroidSchedulers.mainThread()
	}
}