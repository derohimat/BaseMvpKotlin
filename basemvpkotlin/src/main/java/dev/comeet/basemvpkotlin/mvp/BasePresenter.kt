package dev.comeet.basemvpkotlin.mvp

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

/**
 * Base class that implements the Presenter interface and provides a base implementation for
 * attachView() and detachView(). It also handles keeping a reference to the mvpView that
 * can be accessed from the children classes by calling getMvpView().
 */
open class BasePresenter<T : MvpView> : Presenter<T>, CoroutineScope {

    var job = Job()
    var mvpView: T? = null
    override val coroutineContext: CoroutineContext = job + Dispatchers.IO

    override fun attachView(mvpView: T) {
        this.mvpView = mvpView
    }

    override fun detachView() {
        mvpView = null
        job.complete()
    }

    private val isViewAttached: Boolean
        get() = mvpView != null

    fun checkViewAttached() {
        if (!isViewAttached) {
            Timber.e(Throwable("Please call Presenter.attachView(MvpView) before requesting baseData to the Presenter"))
            mvpView?.let { attachView(it) }
        }
    }

}
