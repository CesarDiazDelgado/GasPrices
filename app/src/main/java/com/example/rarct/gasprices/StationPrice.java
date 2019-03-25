package com.example.rarct.gasprices;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.JsonReader;

import com.android.volley.Response;
import com.example.rarct.gasprices.Databases.TownsEntity;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import androidx.room.Query;

public abstract class StationPrice implements Parcelable {

    private String rotulo;
    private String direccion;
    private String precioProducto;
    private String latitud;
    private String longitud;

    public StationPrice(String rotulo, String direccion, String precioProducto, String latitud, String longitud) {
        this.rotulo = rotulo;
        this.direccion = direccion;
        this.precioProducto = precioProducto;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    @Query("SELECT * FROM url")

    private List<StationPrice> readJsonStream(InputStream in) throws IOException {
        // Nueva instancia JsonReader
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        try {
            // Leer Array
            return LeerArrayStationPrices(reader);
        } finally {
            reader.close();
        }

    }

    private List LeerArrayStationPrices(JsonReader reader) throws IOException {
        // Lista temporal
        ArrayList stationPrices = new ArrayList();

        reader.beginArray();
        while (reader.hasNext()) {
            // Leer objeto
            stationPrices.add(LeerStationPrice(reader));
        }
        reader.endArray();
        return stationPrices;
    }

    public StationPrice LeerStationPrice(JsonReader reader) throws IOException {
        String rotulo = null;
        String direccion = null;
        String precioProducto = null;
        String latitud = null;
        String longitud = null;

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            switch (name) {
                case "Rotulo":
                    rotulo = reader.nextString();
                    break;
                case "Direccion":
                    direccion = reader.nextString();
                    break;
                case "PrecioProducto":
                    precioProducto = reader.nextString();
                    break;
                case "Latitud":
                    latitud = reader.nextString();
                    break;
                case "Longitud":
                    longitud = reader.nextString();
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();

        return new StationPrice(rotulo, direccion, precioProducto, latitud, longitud) {
            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {

            }
        };
    }

    public List<StationPrice> GetPrice(TownsEntity town, GasType gasType, Listener listener, Response.ErrorListener errorListener) {
        return null;
    }

    private static final Locale spanish = new Locale("es", "ES");
    private static final NumberFormat doubleFormat = NumberFormat.getInstance(spanish);
    private static double parseDouble(String s) {
        try {
            return doubleFormat.parse(s).doubleValue();
        } catch (ParseException e) {
            return 0;
        }
    }
}
