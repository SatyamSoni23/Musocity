package com.secure.musocity;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.media.session.MediaSessionCompat;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class CreateNotification {
    public static final String CHANNEL_ID = "channel1";
    public static final String ACTION_PREVIOUS = "actionprevious";
    public static final String ACTION_PLAY = "actionplay";
    public static final String ACTION_NEXT = "actionnext";
    public static final String ACTION_SHUFFLE = "actionshuffle";
    public static final String ACTION_REPEAT = "actionrepeat";

    public static Notification notification;
    public static void createNotification(Context context, MusicFiles track, int  playbutton, int pos, int size){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
            MediaSessionCompat mediaSessionCompat = new MediaSessionCompat(context, "tag");

            PendingIntent pendingIntentPrevious;

            int drw_previous;
            Intent intentPrevious = new Intent(context, NotifiactionActionService.class)
                .setAction(ACTION_PREVIOUS);
            pendingIntentPrevious = PendingIntent.getBroadcast(context, 0,
                    intentPrevious, PendingIntent.FLAG_UPDATE_CURRENT);
            drw_previous = R.drawable.ic_skip_previous_notification;

            int drw_play;
            if(PlayerActivity.mediaPlayer.isPlaying()){
                drw_play = R.drawable.ic_pause;
            }
            else{
                drw_play = R.drawable.ic_play;
            }
            Intent intentPlay = new Intent(context, NotifiactionActionService.class)
                    .setAction(ACTION_PLAY);
            PendingIntent pendingIntentPlay = PendingIntent.getBroadcast(context, 0,
                    intentPlay, PendingIntent.FLAG_UPDATE_CURRENT);


            PendingIntent pendingIntentNext;

            int drw_next;
            Intent intentNext = new Intent(context, NotifiactionActionService.class)
                    .setAction(ACTION_NEXT);
            pendingIntentNext = PendingIntent.getBroadcast(context, 0,
                    intentNext, PendingIntent.FLAG_UPDATE_CURRENT);
            drw_next = R.drawable.ic_skip_next_notification;
             /*
            int drw_shuffle;
            Intent intentShuffle = new Intent(context, NotifiactionActionService.class)
                    .setAction(ACTION_SHUFFLE);
            PendingIntent pendingIntentShuffle = PendingIntent.getBroadcast(context, 0,
                    intentShuffle, PendingIntent.FLAG_UPDATE_CURRENT);
            drw_shuffle = R.drawable.ic_shuffle_off_notification;

            int drw_repeat;
            Intent intentRepeat = new Intent(context, NotifiactionActionService.class)
                    .setAction(ACTION_REPEAT);
            PendingIntent pendingIntentFRepeat = PendingIntent.getBroadcast(context, 0,
                    intentRepeat, PendingIntent.FLAG_UPDATE_CURRENT);
            drw_repeat = R.drawable.ic_repeat_off_notification;
            */
            //create notifiaction
            notification = new NotificationCompat.Builder(context, CHANNEL_ID)
                    .setSmallIcon(R.drawable.logo)
                    //.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.logo)) // set a png or jpg images
                    .setContentTitle(track.getTitle())
                    .setContentText(track.getArtist())
                    .setOnlyAlertOnce(true) //show notification for only first time
                    .setShowWhen(false)
                    .addAction(drw_previous, "Previous", pendingIntentPrevious)
                    .addAction(drw_play, "Play", pendingIntentPlay)
                    .addAction(drw_next, "Next", pendingIntentNext)
                    .setStyle(new androidx.media.app.NotificationCompat.MediaStyle()
                            .setShowActionsInCompactView(0,1,2)
                            .setMediaSession(mediaSessionCompat.getSessionToken()))
                    .setPriority(NotificationCompat.PRIORITY_LOW)
                    .build();
            notificationManagerCompat.notify(1, notification);

        }
    }
}
