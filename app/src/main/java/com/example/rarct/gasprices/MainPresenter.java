package com.example.rarct.gasprices;

import android.os.Bundle;

import com.example.rarct.gasprices.Databases.CommunitiesEntity;

public class MainPresenter {

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
}
