package com.sousajrps.covid19pt.scheduler

import io.reactivex.Scheduler

interface SchedulerProvider {

    fun mainThread(): Scheduler

    fun backgroundThread(): Scheduler
}
