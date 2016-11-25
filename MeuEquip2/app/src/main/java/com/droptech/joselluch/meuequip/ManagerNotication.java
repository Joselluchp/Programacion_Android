package com.droptech.joselluch.meuequip;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Jose Lluch on 15/10/2016.
 */

public class ManagerNotication extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationEventReceiver.setupAlarm(context);
    }
}
