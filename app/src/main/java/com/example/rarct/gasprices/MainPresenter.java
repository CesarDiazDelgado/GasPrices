package com.example.rarct.gasprices;

import android.os.Bundle;

import com.example.rarct.gasprices.Databases.CommunitiesEntity;
import com.example.rarct.gasprices.Databases.ProvincesEntity;
import com.example.rarct.gasprices.Databases.TownsEntity;

import java.util.List;

public class MainPresenter {

    private MainActivity view;
    private MainModel model;

    public MainPresenter (MainActivity view, MainModel model){
        this.view = view;
        this.model = model;
    }

    public List<CommunitiesEntity> getCommunities() {
        return model.getCommunitiesEntityList();   //Lo que realmente debe hacerse al clickar el boton
    }

    public List<ProvincesEntity> getProvinces() {
        return model.getProvincesEntityList();   //Lo que realmente debe hacerse al clickar el boton
    }

    public List<TownsEntity> getTowns() {
        return model.getTownsEntityList();   //Lo que realmente debe hacerse al clickar el boton
    }


    public void showPricesClick() {
       // model.Show-PricesClick();   //Lo que realmente debe hacerse al clickar el boton
    }

    public void updateView(){

    }
}
