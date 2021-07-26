package com.deltasoft.ihma.view.home

import android.R
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.ContentValues.TAG
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class MyFirebseInstanceIdService : FirebaseMessagingService() {

    var message: String? = null
    var title:String? = null

    override fun onNewToken(refreshedtoken: String) {
        super.onNewToken(refreshedtoken)
        val token=FirebaseMessaging.getInstance().token

        Log.v(TAG,"Refreshed token: $token")
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
       
        Log.d(TAG, "From: " + remoteMessage.from)
        if (remoteMessage.data.size > 0) {

            Log.d(TAG, "Message data payload: " + remoteMessage.data)
            message = remoteMessage.data["text"].toString()
            title = remoteMessage.data["title"].toString()

            sendNotification(message.toString(), title.toString())
            Log.d(
                TAG,
                "Message: " + remoteMessage.notification!!.body + " ," + "Title: " + remoteMessage.notification!!
                    .title
            )
        }
// Check if message contains a notification payload.
        // Check if message contains a notification payload.
        if (remoteMessage.notification != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.notification!!.body)
            message = remoteMessage.notification!!.body
            title = remoteMessage.notification!!.title
            
            sendNotification(
                message.toString(),
                title.toString(),
            )
            Log.d(
                TAG,
                "Message: " + remoteMessage.notification!!.body + " ," + "Title: " + remoteMessage.notification!!
                    .title
            )
        }
    }

    private fun sendNotification(message: String, title: String) {

        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        if (Build.VERSION.SDK_INT >= 26) {
            val notifyid = 0
            val CHANNEL_ID = "my_channel_01"
            val Nname: CharSequence = "channel_name"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val mchannel = NotificationChannel(CHANNEL_ID, Nname, importance)
            val notificationBuilder: NotificationCompat.Builder = NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_dialog_alert)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setChannelId(CHANNEL_ID)

            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mchannel)
            notificationManager.notify(
                notifyid /* ID of notification */,
                notificationBuilder.build()
            )
        } else {
            val builder: NotificationCompat.Builder = NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_dialog_alert)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)

            // Add as notification
            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            manager.notify(0, builder.build())
        }
    }

    }


    

