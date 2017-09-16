package com.example.marcelo.myapplicationrecefinal.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DatosResponse {

    private int count;
    @SerializedName("recipes")
    private ArrayList<Receta> receta;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ArrayList<Receta> getReceta() {
        return receta;
    }

    public void setReceta(ArrayList<Receta> receta) {
        this.receta = receta;
    }
}
