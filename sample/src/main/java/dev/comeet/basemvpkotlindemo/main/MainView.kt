package dev.comeet.basemvpkotlindemo.main

import dev.comeet.basemvpkotlin.mvp.MvpView

interface MainView : MvpView {
    fun showToast(message: String)
}
