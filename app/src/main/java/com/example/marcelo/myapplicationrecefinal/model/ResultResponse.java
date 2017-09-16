package com.example.marcelo.myapplicationrecefinal.model;

import java.util.ArrayList;


public class ResultResponse {

    private ArrayList<Receta> recipes;

    public ArrayList<Receta> getResult() {
        return recipes;
    }

    public void setResult(ArrayList<Receta> recipes) {
        this.recipes = recipes;
    }
}
