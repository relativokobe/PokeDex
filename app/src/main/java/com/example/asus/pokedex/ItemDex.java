package com.example.asus.pokedex;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Console;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ItemDex extends Fragment {
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    RecyclerView listView;
    String name, url, imgUrl, effect;
    int num;
    boolean kobe = true;
    int offset = 0;
    ArrayList<ItemModel> itemModels;


    public ItemDex() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_item_dex, container, false);

           listView = (RecyclerView)view.findViewById(R.id.recyclerView);

        itemModels = new ArrayList<>();

        getItem(view,offset);
        setView(view);

        kobe = true;

        return view;
    }

    private void getItem(final View view, int offset) {
        final RequestQueue request;
        request = Volley.newRequestQueue(getContext());




        JsonObjectRequest itemRequest = new JsonObjectRequest(Request.Method.GET, "http://pokeapi.co/api/v2/item/?offset="+offset, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    JSONArray jsonArray = response.getJSONArray("results");

                    for(int i=0; i < jsonArray.length();i++){
                         JSONObject item = jsonArray.getJSONObject(i);
                         name = item.getString("name");
                          url = item.getString("url");
                        String []urlParties = url.split("/");
                          num = Integer.parseInt(urlParties[urlParties.length - 1]);
                          imgUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/items/"+name+".png";
                        final ItemModel itemModel = new ItemModel(name,imgUrl,url);

                        final String[] effects = new String[1];
                            JsonObjectRequest itemInfoRequest = new JsonObjectRequest(Request.Method.GET, "http://pokeapi.co/api/v2/item/" + num + "/", new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    try {

                                        JSONArray effect_entries = response.getJSONArray("effect_entries");
                                        for(int i = 0; i < effect_entries.length(); i++){
                                            JSONObject effectVar = effect_entries.getJSONObject(i);

                                             effects[0] = effectVar.getString("effect");
                                            System.out.println("effect = "+effects[0]);
                                            getEffect(effect);
                                            itemModel.setEffect(effects[0]);

                                        }


                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {

                                }
                            }
                            );

                       // ItemModel itemModel = new ItemModel(name,imgUrl,url);
                        itemModels.add(itemModel);
                        Log.e("kobe","IMAGE URL"+itemModel.getImageUrl());
                        Log.e("kobe"," effect sa nig add"+itemModel.getEffect());
                        request.add(itemInfoRequest);
                        setView(view);

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );

        request.add(itemRequest);



    }

    public String getEffect(String effect){
        return effect;
    }



    public void setView(final View view){
        Log.e("kobe","size sa setView = "+itemModels.size());
        //layoutManager = new LinearLayoutManager(view.getContext());
        final LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        listView.setLayoutManager(layoutManager);
        adapter = new ItemAdapter(itemModels,view.getContext());
        listView.setAdapter(adapter);

        listView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (kobe) {
                    if (dy > 0) {
                        int visibleItemCount = layoutManager.getChildCount();
                        int totalitemCount = layoutManager.getItemCount();
                        int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

                        if (visibleItemCount +pastVisibleItems >= totalitemCount) {
                            kobe = false;
                            offset += 20;

                            getItem(view, offset);
                            kobe = true;
                            Toast.makeText(getContext(), "Last", Toast.LENGTH_SHORT).show();

                        }
                    }
                }
            }
        });

    }

}
