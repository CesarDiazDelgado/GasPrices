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


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    private List<String> listStr;
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
            mainPresenter = new MainPresenter(this, new MainModel(this.getBaseContext()));
        }

        setContentView(R.layout.activity_gas_prices);

        spinnerCommunity = findViewById(R.id.spinnerCommunity);
        buttonShowPrices  = findViewById(R.id.buttonShowPrices);

        spinnerCommunity.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, readLine()));


    }

    public void ShowPricesClick(View view) {

        mainPresenter.showPricesClick();

        //mainPresenter.updateView();
    }

    public List<String> readLine() {
        List<String> mLines = new ArrayList<>();

        try {
            InputStream is = getResources().openRawResource(R.raw.communities);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            String[] s = new String[1];
            CommunitiesEntity c;

            while ((line = reader.readLine()) != null) {
                s = line.split("#");
                c = new CommunitiesEntity(Integer.parseInt(s[0]), s[1]);
                mLines.add(s[1]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return mLines;
    }

}
