package com.droptech.joselluch.meuequip5;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;

public class encuestas extends AppCompatActivity {

    private EditText nombreActividad, numNinyos, comentarios;
    private RatingBar valoracion;
    DatePicker fechaenc;

    private static final String REGISTER_URL = "http://www.meuequip16.esy.es/conexionAPP/encuestaJSON.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.encuestas);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        nombreActividad = (EditText) findViewById(R.id.editText2);
        numNinyos = (EditText) findViewById(R.id.editText3);
        comentarios = (EditText) findViewById(R.id.editText4);
        valoracion = (RatingBar) findViewById(R.id.ratingBar);
        fechaenc = (DatePicker) findViewById(R.id.datePicker);

        valoracion.setNumStars(5);
        valoracion.setMax(5);

    }

    public String getNombreActividad() {
        return nombreActividad.getText().toString();
    }

    public String getNumNinyos() {
        return numNinyos.getText().toString();
    }

    public String getComentario() {
        return comentarios.getText().toString();
    }

    public String getValoracion() { return String.valueOf(valoracion.getRating()); }

    public String getFecha() {

        String fecha = "";

        fecha += String.valueOf(fechaenc.getDayOfMonth()) + "/"
                + String.valueOf(fechaenc.getMonth() + 1) + "/"
                + String.valueOf(fechaenc.getYear());

        return fecha;
    }

    public String toJSON() {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("etapa", sharedPreferences.getString("etapaPref", "nofunciona")); //Obtenemos de las preferencias
            jsonObject.put("nombre_actividad", getNombreActividad());
            jsonObject.put("numero_ninyos", getNumNinyos());
            jsonObject.put("valoracion", getValoracion());
            jsonObject.put("fechaEnc", getFecha());
            jsonObject.put("comentarios", getComentario());
            jsonObject.put("contrasenya", sharedPreferences.getString("contraPref", "nofunciona")); //Obtenemos de las preferencias

            return jsonObject.toString();

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    private void vaciador() {
        nombreActividad.setText("");
        numNinyos.setText("");
        comentarios.setText("");
        valoracion.setRating((float) 3.5);
    }

    private boolean conectadoInternet(){
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(getBaseContext().CONNECTIVITY_SERVICE);
        //we are connected to a network
        connected = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED;

        return connected;
    }

    @Override
    public void onBackPressed() {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                switch (which) {

                    case DialogInterface.BUTTON_POSITIVE:
                        finish();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(new View(this).getContext());

        builder.setMessage("¿Volver atrás?")
                .setPositiveButton("Sí", dialogClickListener)
                .setNegativeButton("No", dialogClickListener)
                .show();
    }

    /**
     * Metodo que lanza un Toast con un mensaje especifico.
     *
     * i = 0 -> Mensaje de muestraToast
     * i = 1 -> Mensaje de no-conexion-internet
     * i = 2 -> Mensaje de no funcionalidad
     * i = 3 -> Mensaje de deberia-pasar-esto
     * i = 4 -> No hay conexion a internet
     *
     * @param v
     * @param i
     */
    public void muestraToast(View v, int i) {

        String mensaje = "Cancelado";

        switch (i) {
            case 0:
                mensaje = "Misión abortada. Sin problemas";
                break;
            case 1:
                mensaje = "No puedes enviar nada sin conexión a Internet";
                break;
            case 2:
                mensaje = "Sección no funcional";
                break;
            case 3:
                mensaje = "Ahora realizaria la encuesta";
                break;
            case 4:
                mensaje = "Si no hay conexión a internet, no podemos enviar nada";
                break;
        }

        Toast toast = Toast.makeText(this, "" + mensaje, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void buttonClick(final View view) {

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                switch (which) {

                    case DialogInterface.BUTTON_POSITIVE:

                        if (conectadoInternet()) {
                            //muestraToast(view, 3);
                            doEncuesta();

                            //metodo de limpiar textfields
                            vaciador();
                        }
                        else
                            muestraToast(view, 4);
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        muestraToast(view, 0);
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setMessage("¿Seguro que deseas guardar la encuesta?\n" +
                "- Nombre Actividad: " + getNombreActividad() + "\n" +
                "- Numero de niños: " + getNumNinyos() + "\n" +
                "- Valoracion: " + getValoracion() + "\n"
                + "- Comentarios: " + getComentario())
                .setPositiveButton("Sí", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }

    public void butonClic (final View view){
        if (conectadoInternet()) {
            //muestraToast(view, 3);
            doEncuesta();

            //metodo de limpiar textfields
            vaciador();

        }
        else
            muestraToast(view, 4);
    }

    private void doEncuesta() {

        class conexionBD extends AsyncTask<String, String, String> {


            @Override
            protected String doInBackground(String... params) {

                String JsonResponse = null;
                String JsonDATA = params[1];

                HttpURLConnection urlConnection = null;
                BufferedReader reader = null;

                try {
                    URL url = new URL(params[0]);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setDoOutput(true);

                    // is output buffer writter
                    urlConnection.setRequestMethod("POST");
                    urlConnection.setRequestProperty("Content-Type", "application/json");
                    urlConnection.setRequestProperty("Accept", "application/json");

                    //set headers and method
                    Writer writer = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream(), "UTF-8"));
                    writer.write(JsonDATA);
                    // json data
                    writer.close();
                    InputStream inputStream = urlConnection.getInputStream();
                    //input stream
                    StringBuffer buffer = new StringBuffer();
                    if (inputStream == null) {
                        // Nothing to do.
                        return null;
                    }
                    reader = new BufferedReader(new InputStreamReader(inputStream));

                    String inputLine;
                    while ((inputLine = reader.readLine()) != null)
                        buffer.append(inputLine + "\n");
                    if (buffer.length() == 0) {
                        // Stream was empty. No point in parsing.
                        return null;
                    }
                    JsonResponse = buffer.toString();

                    //send to post execute
                    return JsonResponse;

                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (final IOException e) {
                        }
                    }
                }
                return null;

            }

            @Override
            protected void onPreExecute() {

                return;
            }

            @Override
            protected void onPostExecute(String buffer) {

                System.out.println(buffer);
                Toast.makeText(encuestas.this, buffer, Toast.LENGTH_LONG).show();
                return;
            }


        }

        //enviamos encuesta
        conexionBD con = new conexionBD();
        con.execute(REGISTER_URL, toJSON());
    }

}
