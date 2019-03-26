package com.example.rarct.gasprices;

import android.os.AsyncTask;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.JsonReader;
import android.widget.ListView;

import com.android.volley.Response;
import com.example.rarct.gasprices.Databases.TownsEntity;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.PrivateKey;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.lang.reflect.Type;

import androidx.room.Query;
import org.json.*;

public abstract class StationPrice implements Parcelable {

    private String rotulo;
    private String direccion;
    private String precioProducto;
    private String latitud;
    private String longitud;

    private String url;

    public StationPrice(String url) {
        this.url = url;

    }

    private void ReturnStringUrl(String s) {
        ShowActivity.textView.setText(s);
    }

    public void Final(){
        GetStringUrl(url);
    }

    private void GetStringUrl(final String s) {
        GetAsyncTaskUrl asyncTaskUrl = new GetAsyncTaskUrl(s);
        asyncTaskUrl.execute();
    }

    private class GetAsyncTaskUrl extends AsyncTask<Void, Void, String> {

        private String mAsyncTaskUrl;

        GetAsyncTaskUrl(String myUrl){
            mAsyncTaskUrl = myUrl;
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                return GetUrl(mAsyncTaskUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String myUrl) {
            ReturnStringUrl(myUrl);
        }
    }

    public String GetUrl(String myUrl) throws IOException {
        InputStream inputStream = null;
        int len = 5000;
        try {
            URL url = new URL(myUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(15000);
            connection.setRequestMethod("GET");
            connection.setDoInput(true);

            //Iniciamos la query
            connection.connect();
            inputStream = connection.getInputStream();
            return ReadIt(inputStream, len);
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (inputStream != null) inputStream.close();
        }

        return null;
    }

    private String ReadIt(InputStream inputStream, int len) throws IOException {
        Reader reader = new InputStreamReader(inputStream, "UTF-8");
        char[] buffer = new char[len];
        reader.read(buffer);
        //readJsonStream(inputStream);
        try {
            JSONObject obj = new JSONObject(new String(buffer));
            if (obj.getJSONArray("ListaEESSPrecio").length() != 0) {
                return obj.getJSONArray("ListaEESSPrecio").get(1) + "";
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new String(buffer);
    }

    private String readJsonStream(InputStream in) throws IOException {
        // Nueva instancia JsonReader
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        try {
            // Leer Array
            return LeerArrayStationPrices(reader);
        } finally {
            reader.close();
        }

    }

    private String LeerArrayStationPrices(JsonReader reader) throws IOException {
        // Lista temporal
        String stationPrices = "";

        reader.beginArray();
        while (reader.hasNext()) {
            // Leer objeto
            stationPrices += LeerStationPrice(reader);
        }
        reader.endArray();
        return stationPrices;
    }

    public String LeerStationPrice(JsonReader reader) throws IOException {
        /*String rotulo = null;
        String direccion = null;
        String precioProducto = null;
        String latitud = null;
        String longitud = null;*/

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

        return rotulo + direccion + precioProducto + latitud + longitud;

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
