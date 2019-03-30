package com.example.rarct.gasprices;

import android.content.Context;
import android.os.Bundle;
import android.app.Activity;
import android.os.Parcel;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShowActivity extends Activity {

    private ShowPresenter showPresenter;
    private StationPrice stationPrice;
    private static ListView listView;
    public static ProgressBar progressBar;

    private static Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // recovering the instance state
        if (savedInstanceState != null) {
            showPresenter = new ShowPresenter(this, null);
            //mainPresenter.setState(savedInstanceState);
        }else {
            showPresenter = new ShowPresenter(this, MainModel.getInstance(getBaseContext()));

            stationPrice = new StationPrice(MainActivity.url, this) {
                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {

                }
            };

        }
        context = this;
        setContentView(R.layout.show_gas_price);

        listView = findViewById(R.id.listView);
        progressBar = findViewById(R.id.progressBar);
        stationPrice.Final();

    }
    

    public static void FillListView(String[] s) {
        String[] aux = new String[s.length/5];
        for (int i = 0, j = 0; i < aux.length; i++, j+=5) {
            aux[i] = s[j] + "  " + s[j +1];
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context,android.R.layout.simple_list_item_1,aux);
        listView.setAdapter(adapter);
        progressBar.setVisibility(View.INVISIBLE);
    }
}
