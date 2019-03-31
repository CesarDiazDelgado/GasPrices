package com.example.rarct.gasprices;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

public class ShowActivity extends Activity {

    private ShowPresenter showPresenter;
    private StationPrice stationPrice;
    private static ListView listView;
    public static ProgressBar progressBar;
    private static String[] aux;
    private static String[] auxMaps;
    private static String[] direction;
    private static String[] rotulo;
    private static String[] precios;

    private static Context context;

    CharSequence text = "Maps application not found";
    int duration = Toast.LENGTH_SHORT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // recovering the instance state

        stationPrice = new StationPrice(MainActivity.url, this) {
                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {

                }
            };
            showPresenter = new ShowPresenter(this, stationPrice);

        context = this;
        setContentView(R.layout.show_gas_price);

        listView = findViewById(R.id.listView);
        progressBar = findViewById(R.id.progressBar);
        showPresenter.StartQuery();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CreateSimpleDialog(position).show();
            }
        });

        Toast toast = Toast.makeText(context, text, duration);
        /////toast.show();///////

    }

    private AlertDialog CreateSimpleDialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Address:\n" + direction[position] + "\n")
                .setMessage("Price:\n" + precios[position])
                .setNegativeButton("MAP",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                double n1 = showPresenter.parseDouble(auxMaps[position]);
                                double n2= showPresenter.parseDouble(auxMaps[position + 1]);
                                String name = direction[position];
                                Maps(n1 +"", n2 +"", name);
                            }
                        })
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
        return builder.create();
    }

    public static void FillListView(String[] s) {
        aux = new String[s.length/5];
        direction = new String[s.length/5];
        rotulo = new String[s.length/5];
        precios = new String[s.length/5];
        auxMaps = new String[aux.length*2];
        for (int i = 0, j = 0, k = 0; i < aux.length; i++, j+=5, k+=2) {
            aux[i] = s[j] + "  " + s[j +1];
            direction[i] = s[j +1];
            auxMaps[k] = s[j +3];
            auxMaps[k +1] = s[j +4];
            rotulo[i] = s[j + 2];
            precios[i] = s[j];
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context,android.R.layout.simple_list_item_1,aux);
        listView.setAdapter(adapter);
        progressBar.setVisibility(View.INVISIBLE);
    }

    private void Maps(String s1, String s2, String name) {
        // Create a Uri from an intent string. Use the result to create an Intent.
        Uri gmmIntentUri = Uri.parse("geo:" + s1 + "," + s2 + "?q=" + Uri.encode(name));

        // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        // Make the Intent explicit by setting the Google Maps package
        mapIntent.setPackage("com.google.android.apps.maps");

        // Attempt to start an activity that can handle the Intent
        startActivity(mapIntent);
    }

}
