package com.sousajrps.covid19pt.scheduler

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AppSchedulerProvider : SchedulerProvider {

    override fun mainThread(): Scheduler = AndroidSchedulers.mainThread()

    override fun backgroundThread(): Scheduler = Schedulers.io()
}
