package com.droptech.joselluch.meuequip5;

import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class opiniones extends AppCompatActivity {

    private EditText trimestre;
    private EditText curso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opiniones);
        trimestre = (EditText) findViewById(R.id.editText3);
        curso = (EditText) findViewById(R.id.editText4);
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
