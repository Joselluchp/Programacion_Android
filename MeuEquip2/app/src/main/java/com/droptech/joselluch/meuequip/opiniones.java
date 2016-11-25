package com.droptech.joselluch.meuequip;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.XmlResourceParser;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.OutputStreamWriter;

public class opiniones extends AppCompatActivity {

    private EditText trimestre;
    private EditText curso;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */



    @Override
    /**
     *   constructor de la activity (todo el modelo rollamen de este activity)
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opiniones);

        trimestre = (EditText) findViewById(R.id.editText3);
        curso = (EditText) findViewById(R.id.editText4);
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

    @Override
    public void onBackPressed() {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                switch (which) {

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

    public String getComentTrim() {
        return trimestre.getText().toString();
    }

    public String getComentCurso() {
        return curso.getText().toString();
    }

    public void escribeFichero(final View view) {
        //ya veremos

        return;
    }

    public String leeComentario(final View view)  {

        //aun nada

        return "hola";
    }

    public void onClick(final View view){
        Snackbar.make(view, "Sección no funcional", Snackbar.LENGTH_LONG).setAction("Action", null).show();
    }


}
