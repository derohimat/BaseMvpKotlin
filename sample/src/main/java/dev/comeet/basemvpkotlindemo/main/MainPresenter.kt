package dev.comeet.basemvpkotlindemo.main

import android.os.Handler
import dev.comeet.basemvpkotlin.mvp.BasePresenter

class MainPresenter : BasePresenter<MainView>() {

    fun dummyCall() {
        checkViewAttached()

        mvpView?.apply {
            showProgress(true, "Progress Sample")

            Handler().postDelayed({
                showProgress(false, "")
                showToast("Success")
            }, 3000)
        }
    }

}
