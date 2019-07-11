package dev.comeet.basemvpkotlin.adapter

import android.view.View

interface BaseRecyclerItemClickListener

interface OnRecyclerItemClickListener<D> : BaseRecyclerItemClickListener {

    fun onItemClicked(item: D, position: Int, view: View)
}