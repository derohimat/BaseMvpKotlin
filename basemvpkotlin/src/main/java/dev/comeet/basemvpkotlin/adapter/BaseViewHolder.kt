package dev.comeet.basemvpkotlin.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<D, L : BaseRecyclerItemClickListener>(view: View) : RecyclerView.ViewHolder(view) {

    abstract fun onBind(item: D, listener: L?)
}