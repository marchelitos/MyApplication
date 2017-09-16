package com.example.marcelo.myapplicationrecefinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.marcelo.myapplicationrecefinal.model.DatosResponse;

import retrofit2.Call;

/**
 * Created by Marcelo on 15/09/2017.
 */

public class MiMainActivity extends AppCompatActivity {
    private EditText datosABuscar;
    private Button buscarButton;

    @Override
    protected  void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.main_activity);

        datosABuscar = (EditText)findViewById(R.id.datosBuscarEditText);
        buscarButton = (Button)findViewById(R.id.buscarButton);
        buscarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buscarReceta(datosABuscar.getText().toString());
            }
        });

    }

    private void buscarReceta(String s) {
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        intent.putExtra("buscaMiReceta", s);
        startActivity(intent);
    }


}
