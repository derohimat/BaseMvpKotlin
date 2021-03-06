package dev.comeet.basemvpkotlin.util

import android.graphics.Rect
import android.view.View

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SpacingItemDecoration @JvmOverloads constructor(private val spacing: Int, private val bound: Boolean = false, private var displayMode: Int = -1) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildViewHolder(view).adapterPosition
        val itemCount = state.itemCount
        val layoutManager = parent.layoutManager
        setSpacingForDirection(outRect, layoutManager, position, itemCount)
    }

    private fun setSpacingForDirection(outRect: Rect,
                                       layoutManager: RecyclerView.LayoutManager?,
                                       position: Int,
                                       itemCount: Int) {

        // Resolve display mode automatically
        if (displayMode == -1) {
            displayMode = resolveDisplayMode(layoutManager)
        }

        when (displayMode) {
            HORIZONTAL -> {
                outRect.left = if (position != 0) spacing else 0
                outRect.right = if (position == itemCount - 1) spacing else 0
                outRect.top = if (bound) spacing else 0
                outRect.bottom = if (bound) spacing else 0
            }
            VERTICAL -> {
                outRect.left = if (bound) spacing else 0
                outRect.right = if (bound) spacing else 0
                outRect.top = if (position != 0) spacing else 0
                outRect.bottom = if (position == itemCount - 1) spacing else 0
            }
            GRID -> if (layoutManager is GridLayoutManager) {
                val gridLayoutManager = layoutManager as GridLayoutManager?
                val cols = gridLayoutManager!!.spanCount
                val rows = itemCount / cols

                outRect.left = spacing
                outRect.right = if (position % cols == cols - 1) spacing else 0
                outRect.top = spacing
                outRect.bottom = if (position / cols == rows - 1) spacing else 0
            }
        }
    }

    private fun resolveDisplayMode(layoutManager: RecyclerView.LayoutManager?): Int {
        if (layoutManager is GridLayoutManager) return GRID
        return if (layoutManager!!.canScrollHorizontally()) HORIZONTAL else VERTICAL
    }

    companion object {
        const val HORIZONTAL = 0
        const val VERTICAL = 1
        const val GRID = 2
    }
}