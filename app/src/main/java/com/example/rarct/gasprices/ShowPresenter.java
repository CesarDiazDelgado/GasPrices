package com.example.rarct.gasprices;

import java.util.List;

public class ShowPresenter {

    private ShowActivity view;
    private MainModel model;

    List<StationPrice> stationPrices;

    public ShowPresenter (ShowActivity view, MainModel model){
        this.view = view;
        this.model = model;

        //stationPrices = model.getListStations();
        //ocultar barra progreso
        //mostrar lista
    }

}
