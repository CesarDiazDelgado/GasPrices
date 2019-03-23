package com.example.rarct.gasprices;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.app.Activity;
//import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.rarct.gasprices.Databases.CommunitiesEntity;
import com.example.rarct.gasprices.Databases.ProvincesEntity;
import com.example.rarct.gasprices.Databases.TownsEntity;
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

    Button buttonShowPrices;

    Spinner spinnerCommunity;
    Spinner spinnerProvince;
    Spinner spinnerTypeFuel;
    AutoCompleteTextView autoCompleteTextView;

    GasType[] gasType = GasType.values();

    private List<ProvincesEntity> provincesEntityList;
    private List<TownsEntity> townsEntityList;

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
        spinnerProvince = findViewById(R.id.spinnerProvince);
        autoCompleteTextView = findViewById(R.id.autoCompleteTextView);

        spinnerTypeFuel = findViewById(R.id.spinnerTypeOfFuel);
        buttonShowPrices  = findViewById(R.id.buttonShowPrices);


        spinnerProvince.setEnabled(false);
        //spinnerProvince.setClickable(false);
        //autoCompleteTextView.setEnabled(false);
        buttonShowPrices.setEnabled(false);


        autoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //buttonShowPrices.setText("S " + start + "B " + before + "C " + count);
                for(int i = 0 ; i < townsEntityList.size(); i++) {
                    if(townsEntityList.get(i).getName().equals(s.toString()))
                        buttonShowPrices.setEnabled(true);
                    //else
                        //buttonShowPrices.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //Get communities
        mainPresenter.getCommunitiesEntityList();


        spinnerCommunity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerProvince.setEnabled(true);

                //Get provinces
                mainPresenter.getProvincesEntityList(position +1);
                if (provincesEntityList != null){
                    FillSpinnerProvinces(provincesEntityList);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                autoCompleteTextView.setEnabled(true);

                //Get towns
                mainPresenter.getTownsEntityList(provincesEntityList.get(position).getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerTypeFuel.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Labels()));

    }

    public void FillSpinnerCommunities (List<CommunitiesEntity> list) {
        ArrayAdapter<CommunitiesEntity> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCommunity.setAdapter(adapter);
    }

    public void FillSpinnerProvinces (List<ProvincesEntity> list) {
        ArrayAdapter<ProvincesEntity> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        provincesEntityList = list;
        spinnerProvince.setAdapter(adapter);
    }

    public void FillAutocompleteTextView(List<TownsEntity> list) {
        ArrayAdapter<TownsEntity> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, list);
        autoCompleteTextView.setAdapter(adapter);
        townsEntityList = list;

    }

    public void ShowPricesClick(View view) {
        mainPresenter.showPricesClick();
    }

    public String[] Labels() {
        String[] labels = new String[gasType.length];
        for (int i = 0; i < gasType.length; i++) {
            labels[i] = (gasType[i].label());
        }

        return  labels;
    }

}
