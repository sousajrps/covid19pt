package com.sousajrps.covid19pt

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FcmService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        handleNotificationData(remoteMessage.data)
        handleNotification(remoteMessage.notification)
    }

    private fun handleNotification(remoteMessageNotification: RemoteMessage.Notification?) {
        remoteMessageNotification?.let { notification ->
            val title = notification.title ?: getString(R.string.app_name)
            showNotification(title, notification.body.orEmpty())
        }
    }

    override fun onNewToken(token: String) {
        Log.d(TAG, "newToken: $token")
    }

    private fun handleNotificationData(remoteMessageData: MutableMap<String, String>) {
        if (remoteMessageData.isNotEmpty()) {
            if (remoteMessageData[DATA_REFRESH].toBoolean()) {
                AppModule.getAppSharedPreferences().dataTimeStamp = 0L
            }
            if (remoteMessageData[VACCINATION_REFRESH].toBoolean()) {
                AppModule.getAppSharedPreferences().vaccinationTimeStamp = 0L
            }
            if (remoteMessageData[VACCINATION_WEEKLY_REFRESH].toBoolean()) {
                AppModule.getAppSharedPreferences().vaccinationWeeklyTimeStamp = 0L
            }
        }
    }

    private fun showNotification(title: String, messageBody: String) {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)
        val channelId = getString(R.string.default_notification_channel_id)
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle(title)
            .setContentText(messageBody)
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
            .setContentIntent(pendingIntent)
            .setStyle(NotificationCompat.BigTextStyle().setBigContentTitle(title))

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                getString(R.string.app_name),
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }
        notificationManager.notify(0, notificationBuilder.build())
    }

    companion object {
        private const val TAG = "FcmService"
        private const val DATA_REFRESH = "dRefresh"
        private const val VACCINATION_REFRESH = "vRefresh"
        private const val VACCINATION_WEEKLY_REFRESH = "vwRefresh"
    }
}
