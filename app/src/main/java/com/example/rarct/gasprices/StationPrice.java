package com.example.rarct.gasprices;

import android.app.DownloadManager;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.JsonReader;
import android.util.JsonWriter;
import android.view.Display;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.rarct.gasprices.Databases.TownsEntity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
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
    private static String direccion;
    private static String precioProducto;
    private String latitud;
    private String longitud;

    private String url;
    private Context context;
    public static CustomAdapter adapter;
    public static String[] writerArray;

    public StationPrice(String url, Context context) {
        this.url = url;
        this.context = context;
    }

    private void ReturnStringUrl(String s) {
        return;

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
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONArray array;
                ArrayList<String> arrayListGl  = new ArrayList<>();
                try {
                    int size = response.getJSONArray("ListaEESSPrecio").length();
                    writerArray = new String[size*5];

                    if (size != 0) {
                        array = response.getJSONArray("ListaEESSPrecio");
                        for (int i = 0, j = 0; i < size; i++, j +=5) {
                            rotulo = array.getJSONObject(i).get("Rótulo").toString();
                            direccion = array.getJSONObject(i).get("Dirección").toString();
                            precioProducto = array.getJSONObject(i).get("PrecioProducto").toString();
                            latitud = array.getJSONObject(i).get("Latitud").toString();
                            longitud = array.getJSONObject(i).get("Longitud (WGS84)").toString();

                            writerArray[j] = precioProducto;
                            writerArray[j + 1] = direccion;
                            writerArray[j + 2] = rotulo;
                            writerArray[j + 3] = latitud;
                            writerArray[j + 4] = longitud;
                        }
                    }

                    ShowActivity.FillListView(writerArray);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        queue.add(request);

        /*final StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ShowActivity.textView.setText(response.substring(0,500));

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ShowActivity.textView.setText("Whops");
            }
        });*/


/*
        InputStream inputStream = null;
        int len = 50000;
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

            return  ReadIt(inputStream, len);
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
*/
        return null;
    }

    private String ReadIt(InputStream inputStream, int len) throws IOException {
        Reader reader = new InputStreamReader(inputStream, "UTF-8");
        char[] buffer = new char[len];
        reader.read(buffer);
        StringWriter stringWriter = new StringWriter();

        try {
            //Con esto se lee el json de la query, pero no funciona o funciona cuando le da la gana
            JSONObject obj = new JSONObject(new String(buffer));
            JSONArray array;

            JsonWriter jsonWriter = new JsonWriter(stringWriter);
            int size = obj.getJSONArray("ListaEESSPrecio").length();
            writerArray = new String[size*5];

            if (size != 0) {
                array = obj.getJSONArray("ListaEESSPrecio");
                for (int i = 0; i < size; i++) {
                    rotulo = array.getJSONObject(i).get("Rótulo").toString();
                    direccion = array.getJSONObject(i).get("Dirección").toString();
                    precioProducto = array.getJSONObject(i).get("PrecioProducto").toString();
                    latitud = array.getJSONObject(i).get("Latitud").toString();
                    longitud = array.getJSONObject(i).get("Longitud (WGS84)").toString();

                    writerArray[i] = rotulo;
                    writerArray[i + 1] = direccion;
                    writerArray[i + 2] = precioProducto;
                    writerArray[i + 3] = latitud;
                    writerArray[i + 4] = longitud;


                    /*jsonWriter.beginObject()
                            .name("Rótulo").value(rotulo)
                            .name("Dirección").value(direccion)
                            .name("PrecioProducto").value(precioProducto)
                            .name("Latitud").value(latitud)
                            .name("Longitud (WGS84)").value(longitud)
                            .endObject();
                    return jsonWriter.toString();*/
                }

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new String(buffer);
    }

    public List<StationPrice> GetPrice(TownsEntity town, GasType gasType, Response.Listener<List<StationPrice>> listListener, Response.ErrorListener errorListener) {
        return null;
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
