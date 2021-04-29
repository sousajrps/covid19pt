package com.sousajrps.covid19pt

import android.app.Application
import com.google.firebase.messaging.FirebaseMessaging
import com.sousajrps.covid19pt.local.LocalModule
import com.sousajrps.covid19pt.sharedPreferences.AppSharedPreferencesModule
import com.sousajrps.covid19pt.sharedPreferences.AppSharedPreferencesUtils


class MyApplication : Application() {
    private val sharedPreferences = AppSharedPreferencesModule.getAppSharedPreferences()
    override fun onCreate() {
        super.onCreate()
        sharedPreferences.initialize(this)
        LocalModule.initializeDatabase(this)
        verifyNotificationSubscription()
    }

    private fun verifyNotificationSubscription() {
        if (sharedPreferences.notificationTopicsVersion < AppSharedPreferencesUtils.NOTIFICATION_VERSION) {
            val firebaseMessaging = FirebaseMessaging.getInstance()
            firebaseMessaging.subscribeToTopic(getString(R.string.notification_topic_daily_report))
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        sharedPreferences.dailyNotificationSubscribed = true
                    }
                }
            firebaseMessaging.subscribeToTopic(getString(R.string.notification_topic_general))
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        sharedPreferences.notificationTopicsVersion =
                            AppSharedPreferencesUtils.NOTIFICATION_VERSION
                    }
                }
        }
    }
}
