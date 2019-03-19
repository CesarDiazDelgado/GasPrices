package com.example.rarct.gasprices;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.app.Activity;
//import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.rarct.gasprices.Databases.CommunitiesEntity;
//import com.example.rarct.gasprices.Databases.ProvincesEntity;
//import com.example.rarct.gasprices.Databases.TownsEntity;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    private MainPresenter mainPresenter;

    TextView buttonShowPrices;
    Spinner spinnerCommunity;
    Spinner spinnerTypeFuel;

    GasType[] gasType = GasType.values();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // recovering the instance state
        if (savedInstanceState != null) {
            mainPresenter = new MainPresenter(this, null);
            //mainPresenter.setState(savedInstanceState);
        }else {
            mainPresenter = new MainPresenter(this, MainModel.getInstance(getBaseContext()));
        }

        setContentView(R.layout.activity_gas_prices);

        spinnerCommunity = findViewById(R.id.spinnerCommunity);
        buttonShowPrices  = findViewById(R.id.buttonShowPrices);
        spinnerTypeFuel = findViewById(R.id.spinnerTypeOfFuel);

        mainPresenter.getCommunitiesEntityList();

        spinnerCommunity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerTypeFuel.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Labels()));

    }

    public void ShowPricesClick(View view) {

        mainPresenter.showPricesClick();

        //mainPresenter.updateView();
    }

    public String[] Labels() {
        String[] labels = new String[gasType.length];
        for (int i = 0; i < gasType.length; i++) {
            labels[i] = (gasType[i].label());
        }

        return  labels;
    }

}
