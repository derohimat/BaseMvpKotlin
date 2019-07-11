package dev.comeet.basemvpkotlin.activity

import com.derohimat.sweetalertdialog.SweetAlertDialog
import dev.comeet.basemvpkotlin.mvp.MvpView
import dev.comeet.basemvpkotlin.util.DialogFactory

abstract class BaseMvpActivity : BaseActivity(), MvpView {

    private var progressDialog: SweetAlertDialog? = null

    protected abstract fun attachView()

    protected abstract fun detachView()
    protected var page: Int = 1
    protected var isOnLoad: Boolean = false

    override fun onPause() {
        super.onPause()
        showProgress(false, "")
    }

    override fun onStop() {
        super.onStop()
        showProgress(false, "")
    }

    override fun onDestroy() {
        detachView()
        super.onDestroy()
        if (progressDialog != null) {
            progressDialog?.dismiss()
            progressDialog = null
        }
    }

    override fun showProgress(show: Boolean, message: String) {
        if (!isFinishing) {
            if (show) {
                progressDialog = DialogFactory.createProgressDialog(mContext, message)
                if (progressDialog != null) progressDialog?.show()
            } else {
                if (progressDialog != null) progressDialog?.dismiss()
            }
        }
    }

    override fun showError(error: Throwable) {
        if (!isFinishing) {
            error.message?.let {
                DialogFactory.createErrorDialog(mContext, it).show()
                if (it == "Not a valid token!") {
//                    showLogin401()
                }
            }
        }
    }

    override fun showError(error: String) {
        if (!isFinishing) {
            if (error == "Not a valid token!") {
//                showLogin401()
            }
            DialogFactory.createErrorDialog(mContext, error).show()
        }
    }

}