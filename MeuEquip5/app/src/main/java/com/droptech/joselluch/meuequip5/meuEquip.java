package com.droptech.joselluch.meuequip5;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

public class meuEquip extends AppCompatActivity {

    ImageButton a1, a2, a3, a4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        a1 = (ImageButton)findViewById(R.id.imageEncuesta);
        a2 = (ImageButton)findViewById(R.id.imageAjustes);
        a3 = (ImageButton)findViewById(R.id.imageAgradecimientos);
        a4 = (ImageButton)findViewById(R.id.imageComentarios);
    }

    public void openEncuestas( View view){
        Intent i = new Intent(getBaseContext(), encuestas.class);
        startActivity(i);
    }

    public void openAgradecimientos(final View view){startActivity(new Intent(this, agradecimientos.class ));}

    public void openOpiniones(final View view){startActivity(new Intent(this, opiniones.class ));}

    public void openAjustes(final View view){

        Intent i = new Intent(this, ajustes.class );
        startActivity(i);

    }
}
