package com.droptech.joselluch.meuequip;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.view.View;

import java.util.Calendar;

/**
 * Clase donde se generan, preparan y recogen las preferencias del Usuario
 */
public class ajustes extends PreferenceActivity {

    int IDR1 = 0, IDR2 = 1, IDR3 = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferencias);
    }

    @Override
    public void onBackPressed() {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                switch (which){

                    case DialogInterface.BUTTON_POSITIVE:
                        //setAlarma();
                        finish();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        NotificationEventReceiver.cancelAlarm(getApplicationContext());
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(new View(this).getContext());
        builder.setMessage("¿Volver atrás y guardar?").setPositiveButton("Sí", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }

    /**
     * Metodo apara instanciar las alarmas.
     *
     * ID AlarmaR1-> recordatorio preparacion de actividad: 0
     * ID AlarmaR2-> recordatorio de realizacion de encuesta: 1
     * ID Alarma especial de Vacaciones Mayo-Septiembre: 3
     *
     * Recordatorio: DAY_OF_WEEK empieza en Domingo y acaba en Sabado, de 1-7 respectivamente.
     *               Domingo - Lunes - Martes - Miercoles - Jueves - Viernes - Sabado - Domingo
     *
     */
    private void setAlarmas(){
         SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
         String notifR1 = sharedPreferences.getString("diaRecordPref", "nofunciona");
         String notifR2 = sharedPreferences.getString("diaJuniorPref", "nofunciona");

        //Si no quieren saber nada hasta el curso que viene
        if (notifR2 == "-1" && notifR1 == "-1")
        {
            Intent i = new Intent(ajustes.this, ManagerNotication.class);
            i.putExtra("IDnotificacion", IDR3);

            PendingIntent pendingintent = PendingIntent.getBroadcast(ajustes.this, 0, i, 0);

            AlarmManager nm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

            Calendar c = Calendar.getInstance();

            c.set(Calendar.YEAR, Calendar.YEAR+1);
            c.set(Calendar.MONTH, Calendar.OCTOBER);
            c.set(Calendar.DAY_OF_MONTH, 1);
            c.set(Calendar.HOUR_OF_DAY, 18);
            c.set(Calendar.MINUTE, 0);

            //nm.setInexactRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), AlarmManager.INTERVAL_DAY *7,pendingintent);
            nm.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 15000,pendingintent);
        }
        else
        {
            /**
             * Configurar Notificacion de Recordatorio de Actividad
             * Notificacion un dia de la semana a las 18:00
             */
            if ((notifR1 != "-2") && (notifR1 != "-1")){

                Intent i = new Intent(ajustes.this, ManagerNotication.class);
                i.putExtra("IDnotificacion", IDR1);
                PendingIntent pendingintent = PendingIntent.getBroadcast(ajustes.this, 0, i, 0);

                AlarmManager nm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

                Calendar c = Calendar.getInstance();

                c.set(Calendar.DAY_OF_WEEK, Integer.valueOf(notifR1));
                c.set(Calendar.HOUR_OF_DAY, 18);
                c.set(Calendar.MINUTE, 0);

                //nm.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 10000, pendingintent);
                //FIXME esta repeticion
                nm.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 15000,pendingintent);
            }
            /**
             * Configurar Notificacion de Recordatorio de Actividad
             * Notificacion un dia de la semana a las 21:00
             */
            if ((notifR2 != "-2") && (notifR2 != "-1")){
                Intent i = new Intent(ajustes.this, ManagerNotication.class);
                i.putExtra("IDnotificacion", IDR2);
                PendingIntent pendingintent = PendingIntent.getActivity(getBaseContext(), 0, i, PendingIntent.FLAG_UPDATE_CURRENT);

                AlarmManager nm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

                Calendar c = Calendar.getInstance();

                c.set(Calendar.DAY_OF_WEEK, Integer.valueOf(notifR2));
                c.set(Calendar.HOUR_OF_DAY, 21);
                c.set(Calendar.MINUTE, 0);

                //nm.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), AlarmManager.INTERVAL_DAY*7, pendingintent);
                //FIXME esta repeticion
                nm.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 15000,pendingintent);
            }

            if (true){
                //FIXME -> PONER PARA CUANDO SE CONFIGURAN LAS 2
            }
            else {
                NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                nm.cancelAll();
            }
        }
    }

    private void setAlarma(){ NotificationEventReceiver.setupAlarm(getApplicationContext()); }
}
/**
 * FIXME -> SOLO LE PASA EL ID DE REC ACTIV Y NO EL DEL RESTO. LE PASA UN 0 COMO ID Y NO OTRA COSA
 */