package com.example.rarct.gasprices;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.app.Activity;
//import android.support.v7.app.AppCompatActivity;
import android.view.View;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

                    // recovering the instance state
        if (savedInstanceState != null) {
            mainPresenter = new MainPresenter(this, null);
            //mainPresenter.setState(savedInstanceState);
        }else {
            mainPresenter = new MainPresenter(this, new MainModel(getBaseContext()));
        }

        setContentView(R.layout.activity_gas_prices);

        spinnerCommunity = findViewById(R.id.spinnerCommunity);
        buttonShowPrices  = findViewById(R.id.buttonShowPrices);

        spinnerCommunity.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, readListCommunity()));

    }

    public void ShowPricesClick(View view) {

        mainPresenter.showPricesClick();

        //mainPresenter.updateView();
    }

    public List<CommunitiesEntity> readListCommunity() {

       return mainPresenter.getCommunities();

    }

}
