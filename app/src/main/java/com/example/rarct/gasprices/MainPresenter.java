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

    public void saveState(Bundle outState) {
    }
}
