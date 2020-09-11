package com.secure.musocity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class NotifiactionActionService extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        context.sendBroadcast(new Intent("TRACK_TRACKS")
                .putExtra("action_name", intent.getAction()));
    }
}
