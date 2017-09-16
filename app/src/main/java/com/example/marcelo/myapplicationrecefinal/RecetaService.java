package com.example.marcelo.myapplicationrecefinal;



import com.example.marcelo.myapplicationrecefinal.model.DatosResponse;
import com.example.marcelo.myapplicationrecefinal.model.IngredienteResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface RecetaService {

    @GET("search")
    Call<DatosResponse> dameRecetas(@Query("key") String resourceId, @Query("q") String query);

    @GET("get")
    Call<IngredienteResponse> dameIngredientes(@Query("key") String resourceId, @Query("rId") String queryReceta);


}
