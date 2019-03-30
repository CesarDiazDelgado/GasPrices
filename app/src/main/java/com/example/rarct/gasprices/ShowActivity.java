package com.example.rarct.gasprices;

import android.os.Bundle;
import android.app.Activity;
import android.os.Parcel;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShowActivity extends Activity {

    private ShowPresenter showPresenter;
    private StationPrice stationPrice;
    private static ListView listView;
    public static TextView textView;

    private static ArrayList<String> arrayListGl = new ArrayList<>();

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

        setContentView(R.layout.show_gas_price);

        listView = findViewById(R.id.listView);
        textView = findViewById(R.id.textView);
        stationPrice.Final();

    }
    

    public static void FillListView(CustomAdapter customAdapter) {
        listView.setAdapter(customAdapter);
    }
}
