package dev.comeet.basemvpkotlin.activity

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import dev.comeet.basemvpkotlin.R
import dev.comeet.basemvpkotlin.util.setSystemBarColor
import timber.log.Timber
import java.util.concurrent.atomic.AtomicLong

/**
 * Abstract activity that every other Activity in this application must implement. It provides the
 * following functionality:
 * - Handles creation of Dagger components and makes sure that instances of
 * ConfigPersistentComponent are kept across configuration changes.
 * - Set up and handles a GoogleApiClient instance that can be used to access the Google sign in
 * api.
 * - Handles signing out when an authentication error event is received.
 */
abstract class BaseActivity(@LayoutRes layoutRes: Int) : AppCompatActivity(layoutRes) {

    private var activityId = 0L
    protected lateinit var mContext: Context

    companion object {
        private const val KEY_ACTIVITY_ID = "KEY_ACTIVITY_ID"
        private val NEXT_ID = AtomicLong(0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        // Create the ActivityComponent and reuses cached ConfigPersistentComponent if this is
        // being called after a configuration change.
        activityId = savedInstanceState?.getLong(KEY_ACTIVITY_ID) ?: NEXT_ID.getAndIncrement()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong(KEY_ACTIVITY_ID, activityId)
    }

    override fun onDestroy() {
        if (!isChangingConfigurations) {
            Timber.i("Clearing ConfigPersistentComponent id=%d", activityId)
//            componentsArray.remove(activityId)
        }
        super.onDestroy()
    }

    fun initToolbar(toolbar: Toolbar) {
        setSupportActionBar(toolbar)
        setSystemBarColor(this, R.color.colorPrimaryDark)
    }

    fun initToolbar(toolbar: Toolbar, title: String) {
        initToolbar(toolbar)
        setTitle(title)
    }

    fun initToolbar(toolbar: Toolbar, title: String, showHome: Boolean = false) {
        initToolbar(toolbar)
        setTitle(title)
        showHome(showHome)
    }

    fun setTitle(title: String) {
        supportActionBar?.title = title
    }

    fun showHome(show: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(show)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        android.R.id.home -> {
            finish()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

}