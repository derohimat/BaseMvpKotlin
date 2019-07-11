package dev.comeet.basemvpkotlin.util.scheduler

/**
 * Created by lam on 2/6/17.
 */

object SchedulerUtils {

    fun <T> ioToMain(): IoMainScheduler<T> {
        return IoMainScheduler()
    }
}
