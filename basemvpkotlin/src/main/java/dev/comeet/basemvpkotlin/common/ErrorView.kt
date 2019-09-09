package dev.comeet.basemvpkotlin.common

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import dev.comeet.basemvpkotlin.R
import dev.comeet.basemvpkotlin.util.getColorCompat
import dev.comeet.basemvpkotlin.util.visible
import kotlinx.android.synthetic.main.view_error.view.*
import org.jetbrains.anko.backgroundColor

class ErrorView : LinearLayout {

    private var errorListener: ErrorListener? = null

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    ) {
        init()
    }

    private fun init() {
        orientation = VERTICAL
        gravity = Gravity.CENTER
        layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        backgroundColor = context.getColorCompat(R.color.bgColor)
        LayoutInflater.from(context).inflate(R.layout.view_error, this)
        btnReload.setOnClickListener {
            errorListener?.let { errorListener?.onReloadData() }
        }
    }

    fun setErrorListener(errorListener: ErrorListener) {
        this.errorListener = errorListener
    }

    fun showNoData(message: String?) {
        txtError.text = message
        btnReload.visible()
        visible()
    }

    fun show(message: String?) {
        txtError.text = message
        btnReload.visible()
        visible()
    }

    fun setButtonText(text: String) {
        btnReload.text = text
    }

    interface ErrorListener {
        fun onReloadData()
    }
}
