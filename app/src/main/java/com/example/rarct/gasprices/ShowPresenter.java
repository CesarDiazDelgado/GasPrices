package com.example.rarct.gasprices;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class ShowPresenter {

    private ShowActivity showActivity;
    private MainModel mainModel;
    private StationPrice stationPrice;

    List<StationPrice> stationPrices;

    public ShowPresenter (ShowActivity view, MainModel model ){
        this.showActivity = view;
        this.mainModel = model;

        //stationPrices = mainModel.getListStations();
        //ocultar barra progreso
        //mostrar lista
    }


}
