package com.example.rarct.gasprices;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Parcelable;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public abstract class StationPrice implements Parcelable {

    private String rotulo;
    private static String direccion;
    private static String precioProducto;
    private String latitud;
    private String longitud;

    private String url;
    private Context context;
    public static String[] writerArray;

    public StationPrice(String url, Context context) {
        this.url = url;
        this.context = context;
    }

    public void GetStringUrl() {
        GetAsyncTaskUrl asyncTaskUrl = new GetAsyncTaskUrl();
        asyncTaskUrl.execute();
    }

    private class GetAsyncTaskUrl extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            try {
                return GetUrl();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public String GetUrl()throws IOException {
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONArray array;
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
        return null;
    }


}
