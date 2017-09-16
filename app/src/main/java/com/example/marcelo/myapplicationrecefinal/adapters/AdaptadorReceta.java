package com.example.marcelo.myapplicationrecefinal.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.bumptech.glide.Glide;
import com.example.marcelo.myapplicationrecefinal.MainActivity;
import com.example.marcelo.myapplicationrecefinal.R;
import com.example.marcelo.myapplicationrecefinal.model.Receta;

/**
 * Created by Marcelo on 13/09/2017.
 */

public class AdaptadorReceta extends RecyclerView.Adapter<AdaptadorReceta.DeviceViewHolder> {
    private Context context;
    private ArrayList<Receta> dataset;
    private OnRecetaItemClickListener onRecetaItemClickListener;


    public AdaptadorReceta(ArrayList<Receta> dataset, MainActivity context) {
        this.dataset = dataset;
        this.context = context;
        this.onRecetaItemClickListener = context;
    }

    @Override
    public DeviceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_unidad_receta,parent,false);
        return new DeviceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DeviceViewHolder holder, int position) {
        Receta u = dataset.get(position);
        holder.titleTextview.setText(u.getTitle());
        holder.puntuacionIdTextview.setText("Preferencia : " + String.format("%.2f pts", u.getSocial_rank()));
        String url = u.getImage_url();
        Glide.with(context).load(url).into(holder.fotoImageView);
        holder.setOnRecetaItemClick(u,onRecetaItemClickListener);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public class DeviceViewHolder extends RecyclerView.ViewHolder {
        View cardView;
        TextView puntuacionIdTextview;
        TextView titleTextview;
        ImageView fotoImageView;


        public DeviceViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);

            titleTextview= (TextView)itemView.findViewById(R.id.tituloTextView);
            puntuacionIdTextview = (TextView)itemView.findViewById(R.id.puntuacionTextView);
            fotoImageView = (ImageView)itemView.findViewById(R.id.fotoImageView);
        }


        public void setOnRecetaItemClick(final Receta u, final OnRecetaItemClickListener onRecetaItemClickListener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onRecetaItemClickListener.onRecetaItemClick(u);
                }
            });
        }
    }
    public void add(Receta receta){
        dataset.add(receta);
        notifyDataSetChanged();
    }
    public void setDataset(ArrayList<Receta> listaRecetas){
        if(listaRecetas == null)
            dataset = new ArrayList<>();
        else
            dataset = listaRecetas;
        notifyDataSetChanged();

    }
    public void clear() {
        dataset.clear();
        notifyDataSetChanged();
    }
}
