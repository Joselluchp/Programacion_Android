package com.droptech.joselluch.meuequip;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class agradecimientos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agradecimientos);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: //hago un case por si en un futuro agrego mas opciones
                Log.i("ActionBar", "Atrás!");
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void muestraPantalla(View v) {
        Toast toast = Toast.makeText(this, "A tí, por embancarte en una gran aventura. SU gran aventura", Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void onBackPressed() {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                switch (which){

                    case DialogInterface.BUTTON_POSITIVE:
                        //pasamos datos al modelo
                        finish();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:

                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(new View(this).getContext());
        builder.setMessage("¿Volver atrás?").setPositiveButton("Sí", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }

    /**
     * Abre una accion aparentemente inexistente
     *
     * @param view
     */
    public void funcionOculta(final View view){

        TextView citas = (TextView)findViewById(R.id.citasText);
        int i = 0, semilla = 28;
        String cita ="";
        Random rand = new Random();

        citas.clearComposingText();

        i = rand.nextInt(semilla); // Gives n such that 0 <= n < 20

        switch (i){
            case 0: cita = "Romanos 12:21";
                    break;
            case 1: cita = "Galatas 5:22-25";
                    break;
            case 2: cita = "Lamentaciones 3:27 ";
                    break;
            case 3: cita = "Salmos 37:25 ";
                    break;
            case 4: cita = "Salmos 119:9-10 ";
                    break;
            case 5: cita = "Isaias 40:29-31";
                    break;
            case 6: cita = "1 Pedro 5:5";
                    break;
            case 7: cita = "Salmos 25:7";
                    break;
            case 8: cita = "Lamentaciones 3:27 ";
                    break;
            case 9: cita = "Mateo 19:20-21 ";
                    break;
            case 10: cita = "Provebrios 22:6 ";
                    break;
            case 11: cita = "Romanos 12:17 ";
                    break;
            case 12: cita = "Mateo 14:27";
                    break;
            case 13: cita = "Mateo 9:22";
                    break;
            case 14: cita = "Hechos 23:11";
                    break;
            case 15: cita = "Juan 16:33";
                    break;
            case 16: cita = "Mateo 5:11-12";
                    break;
            case 17: cita = "Proverbios 3:5";
                    break;
            case 18: cita = "Apocalipsis 21:4";
                    break;
            case 19: cita = "Salmos 62:1";
                    break;
            case 20: cita = "2 Corintios 12:9";
                    break;
            case 21: cita = "Proverbios 22:6";
                    break;
            case 22: cita = "Mateo 28:18";
                    break;
            case 23: cita = "Eclesiastes 3:1";
                    break;
            case 24:cita = "Galatas 6:9";
                    break;
            case 25 :cita = "Salmos 119:90";
                    break;
            case 26:cita = "1 Corintios 13:13";
                    break;
            case 27:cita = "Mateo 5:15-16";
                    break;
            case 28:cita = "Salmos 23";
                    break;
        }

        citas.setText(cita);

    }
}
