package com.example.rarct.gasprices;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class ShowPresenter {

    private static ShowActivity showActivity;
    private StationPrice stationPrice;

    public ShowPresenter (ShowActivity view, StationPrice stationPrice ){
        this.showActivity = view;
        this.stationPrice = stationPrice;
    }

    public void StartQuery() {
        stationPrice.GetStringUrl();
    }

    private static final Locale spanish = new Locale("es", "ES");
    private static final NumberFormat doubleFormat = NumberFormat.getInstance(spanish);
    public static double parseDouble(String s) {
        try {
            return doubleFormat.parse(s).doubleValue();
        } catch (ParseException e) {
            return 0;
        }
    }

}
