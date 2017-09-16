package com.example.marcelo.myapplicationrecefinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.marcelo.myapplicationrecefinal.adapters.AdaptadorReceta;
import com.example.marcelo.myapplicationrecefinal.adapters.OnRecetaItemClickListener;
import com.example.marcelo.myapplicationrecefinal.model.DatosResponse;
import com.example.marcelo.myapplicationrecefinal.model.Ingrediente;
import com.example.marcelo.myapplicationrecefinal.model.IngredienteResponse;
import com.example.marcelo.myapplicationrecefinal.model.Receta;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements OnRecetaItemClickListener{
 private static final String TAG = "FOOD";

    RecyclerView recyclerView;

    AdaptadorReceta adaptadorReceta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView)findViewById(R.id.recetasRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String buscaReceta = getIntent().getStringExtra("buscaMiReceta");

        cargarRecetas(buscaReceta);
    }

    private void cargarRecetas(String buscaLaReceta) {
        RecetaService service = ServiceGenerator.createService(RecetaService.class);

        Call<DatosResponse> call = service.dameRecetas(BuildConfig.FOOD_RECIPES_ACM,buscaLaReceta);
        call.enqueue(new Callback<DatosResponse>() {
            @Override
            public void onResponse(Call<DatosResponse> call, Response<DatosResponse> response) {
                if(response.isSuccessful()){
                    DatosResponse misDatos = response.body();
                    ArrayList<Receta> listaRecetas = misDatos.getReceta();
                    adaptadorReceta = new AdaptadorReceta(listaRecetas,MainActivity.this);
                    recyclerView.setAdapter(adaptadorReceta);
                }else{
                    Log.e(TAG, "onResponde" + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<DatosResponse> call, Throwable t) {
                Log.e(TAG,"on failure" + t.getMessage());
            }
        });
    }
    @Override
    public void onRecetaItemClick(Receta r) {
        Intent intent = new Intent(getApplicationContext(), IngredienteActivity.class);
        intent.putExtra("idReceta", r.getRecipe_id());
        intent.putExtra("urlImage", r.getImage_url());
        intent.putExtra("tituloReceta", r.getTitle());
        startActivity(intent);
    }

}
