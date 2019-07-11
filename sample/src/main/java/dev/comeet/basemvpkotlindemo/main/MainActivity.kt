package dev.comeet.basemvpkotlindemo.main

import android.os.Bundle
import dev.comeet.basemvpkotlin.activity.BaseMvpActivity
import dev.comeet.basemvpkotlin.util.DialogFactory
import dev.comeet.basemvpkotlindemo.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseMvpActivity(), MainView {

    private lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = MainPresenter()
        attachView()

        btnRetry.setOnClickListener { presenter.dummyCall() }

        presenter.dummyCall()
    }

    override fun attachView() {
        presenter.attachView(this)
    }

    override fun detachView() {
        presenter.detachView()
    }

    override fun layoutId(): Int = R.layout.activity_main

    override fun showToast(message: String) {
        DialogFactory.createDialog(mContext, "Sample", message).show()
    }
}
