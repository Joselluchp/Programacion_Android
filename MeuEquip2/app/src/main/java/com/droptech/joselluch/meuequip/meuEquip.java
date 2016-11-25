package com.droptech.joselluch.meuequip;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;


public class meuEquip extends AppCompatActivity {

    ImageButton a1, a2, a3, a4;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        i = new Intent();

        a1 = (ImageButton)findViewById(R.id.imageEncuesta);
        a2 = (ImageButton)findViewById(R.id.imageAjustes);
        a3 = (ImageButton)findViewById(R.id.imageAgradecimientos);
        a4 = (ImageButton)findViewById(R.id.imageComentarios);


    }

    public void openEncuestas( View view){
        Intent i = new Intent(getBaseContext(), encuestas.class );
        startActivity(i);
        //overridePendingTransition(R.anim.zoom_forward_in, R.anim.zoom_back_out);

    }

    public void openAjustes(final View view){ startActivity(new Intent(this, ajustes.class ));}
    public void openAgradecimientos(final View view){startActivity(new Intent(this, agradecimientos.class ));}
    public void openOpiniones(final View view){startActivity(new Intent(this, opiniones.class ));}
}
