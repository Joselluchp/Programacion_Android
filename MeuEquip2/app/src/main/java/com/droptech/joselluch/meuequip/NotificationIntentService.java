package com.droptech.joselluch.meuequip;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

import java.util.Random;

/**
 * Created by Jose Lluch on 16/10/2016.
 */

public class NotificationIntentService extends IntentService {
    private static final int NOTIFICATION_ID = 1, ENCUESTA_ID = 1;
    private static final String ACTION_START = "ACTION_START";
    private static final String ACTION_DELETE = "ACTION_DELETE";

    public NotificationIntentService() {
        super(NotificationIntentService.class.getSimpleName());
    }

    public static Intent createIntentStartNotificationService(Context context) {
        Intent intent = new Intent(context, NotificationIntentService.class);
        intent.setAction(ACTION_START);
        return intent;
    }

    public static Intent createIntentDeleteNotification(Context context) {
        Intent intent = new Intent(context, NotificationIntentService.class);
        intent.setAction(ACTION_DELETE);
        return intent;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(getClass().getSimpleName(), "onHandleIntent, started handling a notification event");
        try {
            String action = intent.getAction();
            if (ACTION_START.equals(action)) {
                processStartNotification();
            }
        } finally {
            WakefulBroadcastReceiver.completeWakefulIntent(intent);
        }
    }

    private void processDeleteNotification(Intent intent) {
        // Log something?
    }

    private void processStartNotification() {

        final NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("Aviso de meuEquip")
                .setAutoCancel(true)
                .setContentText(getMensajeNoti(ENCUESTA_ID))
                .setSmallIcon(R.drawable.ic_juniors_notify);

        Intent mainIntent = new Intent(this, meuEquip.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                NOTIFICATION_ID,
                mainIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        builder.setDeleteIntent(NotificationEventReceiver.getDeleteIntent(this));

        final NotificationManager manager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(NOTIFICATION_ID, builder.build());

    }
    /**
     * Dado un entero (0 si R1 y 1 si R2) obtendremos un posible mensaje para la notificacion
     *
     * @param noti
     * @return
     */
     private String getMensajeNoti(int noti){

        String mensaje = "Notificación de meuEquip";
        int ran, N_MENSAJES = 2;

        Random rand = new Random();
        ran = rand.nextInt(N_MENSAJES); // Gives n such that 0 <= n < 2

        if ( noti == 0){

            switch(ran){
                case 0:
                    mensaje = "¿Lo tienes todo listo para\nesta semana?";
                    break;
                case 1:
                    mensaje = "¡Que no se te olvide preparar\nla actividad de esta semana!";
                    break;
                case 2:
                    mensaje = "Tus niños te esperan.\n¿Todo preparado?";
                    break;
            }

        }
        else if (noti == 1) {
            switch (ran) {
                case 0:
                    mensaje = "No te habrás olvidad de hacer la encuesta, ¿verdad?";
                    break;
                case 1:
                    mensaje = "¿Oye, ¿has hecho la encuesta?";
                    break;
                case 2:
                    mensaje = "El jefe matará un gatito si no haces la encuesta.";
                    break;
            }
        }
        return mensaje;
    }
}
