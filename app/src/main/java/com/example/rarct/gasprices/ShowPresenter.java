package com.example.rarct.gasprices;

import java.util.List;

public class ShowPresenter {

    private ShowActivity showActivity;
    private MainModel mainModel;
    private StationPrice stationPrice;

    List<StationPrice> stationPrices;

    public ShowPresenter (ShowActivity view, MainModel model){
        this.showActivity = view;
        this.mainModel = model;

        //stationPrices = mainModel.getListStations();
        //ocultar barra progreso
        //mostrar lista
    }
/*
    void getCommunitiesEntityList() {
        StationPrice.GetStringUrl(new Listener<String>() {
            @Override
            public void onResponse(String response) {
                showActivity.FillListView(response);

            }
        });
    }*/

}
