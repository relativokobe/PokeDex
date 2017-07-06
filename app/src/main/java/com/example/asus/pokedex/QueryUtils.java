package com.example.asus.pokedex;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by asus on 05/07/2017.
 */

public class QueryUtils {

    ArrayList<Pokemon1> array = new ArrayList<>();
    onCallBack onSuccess;

    public QueryUtils() {
    }

    public ArrayList<Pokemon1> getPokemons(Context context, int offset, final PokemonAdapter pokemonAdapter, final onCallBack help ){
        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(context);
        //final ArrayList<String> array = new ArrayList<>();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "http://pokeapi.co/api/v2/pokemon/?offset="+offset,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("results");

                            for(int i = 0 ; i < jsonArray.length() ; i++){

                                JSONObject name = jsonArray.getJSONObject(i);

                                String kobe = name.getString("name");
                                String url = name.getString("url");
                                System.out.println(" pokemon name: "+kobe);
                                Pokemon1 poke = new Pokemon1(kobe,url);
                                array.add(poke);
                                System.out.println("After array: "+ array.get(i).getName());
                            }

                            System.out.println("Query utils array size after try: "+array.size());
                            help.onSuccsess(array);
                            if(pokemonAdapter!=null)
                            pokemonAdapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                            System.out.println("Fuck wala ni gana");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }

        });

        System.out.println("Size before return: "+ array.size());
        requestQueue.add(jsonObjectRequest);


        return array;

    }
    public interface onCallBack{
        void onSuccsess(ArrayList<Pokemon1> pokemon);

    }


   /* public ArrayList<String> getPuke(Context context){
        ArrayList<String> puke = getPokemons(context, );

        return puke;
    }*/

}
