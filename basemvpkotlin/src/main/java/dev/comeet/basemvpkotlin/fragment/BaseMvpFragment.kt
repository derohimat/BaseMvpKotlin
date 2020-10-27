package dev.comeet.basemvpkotlin.fragment

import androidx.annotation.LayoutRes
import com.derohimat.sweetalertdialog.SweetAlertDialog
import dev.comeet.basemvpkotlin.mvp.MvpView
import dev.comeet.basemvpkotlin.util.DialogFactory

abstract class BaseMvpFragment(@LayoutRes layoutRes: Int) : BaseFragment(layoutRes), MvpView {

    protected var page: Int = 1
    protected var isOnLoad: Boolean = false
    private var progressDialog: SweetAlertDialog? = null

    protected abstract fun attachView()

    protected abstract fun detachView()

    override fun onDestroy() {
        super.onDestroy()
        detachView()
        if (progressDialog != null) {
            progressDialog?.dismiss()
            progressDialog = null
        }
    }

    override fun showProgress(show: Boolean, message: String) {
        if (!requireActivity().isFinishing) {
            if (show) {
                progressDialog = DialogFactory.createProgressDialog(mContext, message)
                if (progressDialog != null) progressDialog?.show()
            } else {
                if (progressDialog != null) progressDialog?.dismiss()
            }
        }
    }

    override fun showError(error: Throwable) {
        error.message?.let {
            DialogFactory.createErrorDialog(mContext, it).show()
            if (it == "Not a valid token!") {
                showLogin()
            }
        }
    }

    override fun showError(message: String) {
        message.let {
            DialogFactory.createErrorDialog(mContext, it).show()
            if (it == "Not a valid token!") {
                showLogin()
            }
        }
    }

}