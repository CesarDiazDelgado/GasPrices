package com.example.rarct.gasprices;

import android.content.Context;
import android.content.res.Resources;

import com.android.volley.Response;
import com.example.rarct.gasprices.Databases.CommunitiesEntity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainModel{

    private final Resources resources;

    private void recoverCommunities(Response.Listener listener) {

    }

    public MainModel(Context context) {
        resources = context.getResources();
    }
}
