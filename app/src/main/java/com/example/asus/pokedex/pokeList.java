package com.example.asus.pokedex;


import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class pokeList extends Fragment implements PokemonInfo.Kobe, PokemonAdapter.off{

    private RecyclerView recyclerView;
    private PokemonAdapter pokeAdapter;
    QueryUtils queryUtils;
    ArrayList<Pokemon1> array;
    boolean kobe = true;
    int offset=0;
    Context cont;


    public pokeList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_poke_list, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);

        array = new ArrayList<>();

        queryUtils = new QueryUtils();
        cont = view.getContext();


       piste();

            array = queryUtils.getPokemons(getContext(), offset, pokeAdapter,new QueryUtils.onCallBack() {
                @Override
                public void onSuccsess(ArrayList<Pokemon1> pokemon) {
                    setView(pokemon);
                    System.out.println("array size sud sa interface: "+pokemon.size());
                }
            });

        kobe = true;
        System.out.println("Array size sud sa fragment"+array.size());
      //  setView();

        return view;
    }

    private void piste() {
        PokemonInfo pok = new PokemonInfo();
        pok.setTargetFragment(this,0);

    }

    private void setView(ArrayList<Pokemon1>pokemon) {
        pokeAdapter = new PokemonAdapter(getContext(),pokemon,this);
   //     RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        final GridLayoutManager layoutManaager = new GridLayoutManager(getContext(),3);
        recyclerView.setLayoutManager(layoutManaager);
        recyclerView.setAdapter(pokeAdapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(dy>0){

                    int visibleItemCount = layoutManaager.getChildCount();
                    int totalItemCount = layoutManaager.getItemCount();
                    int pastVisibleItems = layoutManaager.findFirstVisibleItemPosition();
                    if(kobe ){
                        if((visibleItemCount + pastVisibleItems)>=totalItemCount){
                            kobe = false;
                            offset+=20;

                            array = queryUtils.getPokemons(getContext(), offset, pokeAdapter, new QueryUtils.onCallBack() {
                                @Override
                                public void onSuccsess(ArrayList<Pokemon1> pokemon) {
                                    kobe = true;
                                  //  setView(pokemon);

                                    System.out.println("array size sud sa interface: "+pokemon.size());
                                }
                            });
                            Toast.makeText(getContext(), "naas last", Toast.LENGTH_SHORT).show();


                        }
                    }

                }
            }
        });

    }
    public void setOffset(){
        offset = 0;
    }


    @Override
    public void offsetOff() {
        offset = 0;
    }

    @Override
    public void iOff() {
        offset = 0;
    }
}
