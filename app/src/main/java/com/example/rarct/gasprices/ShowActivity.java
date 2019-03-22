package com.example.rarct.gasprices;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.List;

public class ShowActivity extends Activity {

    private ShowPresenter showPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // recovering the instance state
        if (savedInstanceState != null) {
            showPresenter = new ShowPresenter(this, null);
            //mainPresenter.setState(savedInstanceState);
        }else {
            showPresenter = new ShowPresenter(this, MainModel.getInstance(getBaseContext()));
        }
    }
}
