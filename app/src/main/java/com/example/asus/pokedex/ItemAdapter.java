package com.example.asus.pokedex;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by asus on 07/07/2017.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder>{
    ArrayList<ItemModel> itemLsit;
    Context context;

    public ItemAdapter(ArrayList<ItemModel> itemLsit, Context context) {
        this.itemLsit = itemLsit;
        this.context = context;

        Log.e("kobe","itemlist sa adapter = "+itemLsit.size());

    }

    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        ViewHolder vh = new ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.effect.setText(itemLsit.get(position).getEffect());
        holder.name.setText(itemLsit.get(position).getName());
        Glide.with(context).load(itemLsit.get(position).getImageUrl()).into(holder.image);
        Log.e("kobe"," eFFECT "+itemLsit.get(position).getEffect());


    }



    @Override
    public int getItemCount() {
        return itemLsit.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView name;
        TextView effect;

        public ViewHolder(View itemView) {
            super(itemView);

            image = (ImageView)itemView.findViewById(R.id.image);
            name = (TextView)itemView.findViewById(R.id.name);
            effect = (TextView)itemView.findViewById(R.id.effect);


        }
    }
}
