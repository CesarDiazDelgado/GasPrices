package com.example.rarct.gasprices;

import com.example.rarct.gasprices.Databases.CommunitiesEntity;

public class MainPresenter {

    private MainView view;
    private MainModel model;

    //private AppDatabase dataBase;

    public MainPresenter (MainView view, MainModel model){
        this.view = view;
        this.model = model;
    }

    public void  onComunitiesAvailable(CommunitiesEntity[] communities){

    }
}
