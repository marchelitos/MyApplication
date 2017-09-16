package com.example.marcelo.myapplicationrecefinal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.marcelo.myapplicationrecefinal.model.Ingrediente;
import com.example.marcelo.myapplicationrecefinal.model.IngredienteResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Marcelo on 15/09/2017.
 */

public class IngredienteActivity extends AppCompatActivity{
    private ImageView posterImageView;
    private TextView tituloTextView;
    private TextView ingredientesTextView;

    private String idReceta;
    private String urlImage;
    private String tituloReceta;

    @Override
    protected  void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_ingrediente);

        posterImageView = (ImageView)findViewById(R.id.posterImageView);
        tituloTextView = (TextView)findViewById(R.id.tituloRecetaTextView);
        ingredientesTextView = (TextView)findViewById(R.id.ingredientesTextView);

        tituloReceta = getIntent().getStringExtra("tituloReceta");
        urlImage = getIntent().getStringExtra("urlImage");
        idReceta = getIntent().getStringExtra("idReceta");
        tituloTextView.setText(tituloReceta);
        Glide.with(this).load(urlImage).into(posterImageView);
        cargarIngredientes();
    }

    private void cargarIngredientes() {
        RecetaService service = ServiceGenerator.createService(RecetaService.class);

        Call<IngredienteResponse> call = service.dameIngredientes(BuildConfig.FOOD_RECIPES_ACM,idReceta);
        call.enqueue(new Callback<IngredienteResponse>() {
            @Override
            public void onResponse(Call<IngredienteResponse> call, Response<IngredienteResponse> response) {
                if(response.isSuccessful()) {
                    ArrayList<String> ingre = response.body().getRecipe().getIngredients();
                    String datos = "";
                    for(int i=0; i< ingre.size();i++){
                        datos = datos + "* " + ingre.get(i) + "\n";
                    }
                   ingredientesTextView.setText(datos);
                }
            }

            @Override
            public void onFailure(Call<IngredienteResponse> call, Throwable t) {
            }
        });
    }

}
