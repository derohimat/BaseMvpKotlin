package dev.comeet.basemvpkotlin.mvp

import dev.comeet.basemvpkotlin.util.DisposableManager
import io.reactivex.disposables.Disposable
import timber.log.Timber

/**
 * Base class that implements the Presenter interface and provides a base implementation for
 * attachView() and detachView(). It also handles keeping a reference to the mvpView that
 * can be accessed from the children classes by calling getMvpView().
 */
open class BasePresenter<T : MvpView> : Presenter<T> {

    var mvpView: T? = null

    override fun attachView(mvpView: T) {
        this.mvpView = mvpView
    }

    override fun detachView() {
        mvpView = null
        DisposableManager.dispose()
    }

    private val isViewAttached: Boolean
        get() = mvpView != null

    fun checkViewAttached() {
        if (!isViewAttached) {
            Timber.e(Throwable("Please call Presenter.attachView(MvpView) before requesting baseData to the Presenter"))
            mvpView?.let { attachView(it) }
        }
    }

    fun addDisposable(disposable: Disposable) {
        DisposableManager.add(disposable)
    }

}

