package com.droptech.joselluch.meuequip5;

import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.v7.app.AlertDialog;
import android.view.View;

/**
 * Created by Jose Lluch on 24/11/2016.
 */

public class ajustes extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /**
         * fixme inicia mal el addPreferenceSource. Ver como es en Android 5.1
         */
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
                        //NotificationEventReceiver.cancelAlarm(getApplicationContext());
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(new View(this).getContext());
        builder.setMessage("¿Volver atrás y guardar?").setPositiveButton("Sí", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }

}
