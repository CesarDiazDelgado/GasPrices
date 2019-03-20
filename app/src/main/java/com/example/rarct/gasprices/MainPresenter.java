package com.example.rarct.gasprices;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.example.rarct.gasprices.Databases.CommunitiesEntity;
import com.example.rarct.gasprices.Databases.ProvincesEntity;
import com.example.rarct.gasprices.Databases.TownsEntity;
//import com.example.rarct.gasprices.Databases.ProvincesEntity;
//import com.example.rarct.gasprices.Databases.TownsEntity;

import java.util.List;

public class MainPresenter{

    private MainActivity view;
    private MainModel model;

    public MainPresenter (MainActivity view, MainModel model){
        this.view = view;
        this.model = model;
    }


    public void showPricesClick() {
       // model.Show-PricesClick();   //Lo que realmente debe hacerse al clickar el boton
    }

    public void updateView(){

    }

    void getCommunitiesEntityList() {
        model.getCommunitiesEntityList(new Listener<List<CommunitiesEntity>>() {
            @Override
            public void onResponse(List<CommunitiesEntity> response) {
                view.FillSpinnerCommunities(response);

            }
        });
    }

    void getProvincesEntityList(int position) {
        model.getProvincesEntityList(position,new Listener<List<ProvincesEntity>>() {
            @Override
            public void onResponse(List<ProvincesEntity> response) {
                view.FillSpinnerProvinces(response);
            }
        });
    }

    void getTownsEntityList(int position) {
        model.getTownsEntityList(position,new Listener<List<TownsEntity>>() {
            @Override
            public void onResponse(List<TownsEntity> response) {
                view.FillAutocompleteTextView(response);
            }
        });
    }
}
