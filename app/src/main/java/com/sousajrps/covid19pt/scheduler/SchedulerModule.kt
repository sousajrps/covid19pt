package com.sousajrps.covid19pt.scheduler

object SchedulerModule {

    fun schedulerProvider(): SchedulerProvider = AppSchedulerProvider()
}
