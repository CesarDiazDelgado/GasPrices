package com.example.rarct.gasprices;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class StationPrice {
    private static final Locale spanish = new Locale("es", "ES");
    private static final NumberFormat doubleFormat = NumberFormat.getInstance(spanish);
    private static double parseDouble(String s) {
        try {
            return doubleFormat.parse(s).doubleValue();
        } catch (ParseException e) {
            return 0;
        }
    }
    /*Crear:
    List<JSONArray> ListaEESSPrecio = new ArrayList<JSONArray>;
    */
}
