package com.example.rarct.gasprices;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    Context mContext;
    ArrayList<String> arrayListGl;
    public EditText editText;

    public CustomAdapter(Context context, ArrayList<String> arrayListGl) {
        this.mContext = context;
        this.arrayListGl = arrayListGl;
    }

    @Override
    public int getCount() {
        return arrayListGl.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayListGl.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(mContext).inflate(R.layout.listviewlayout, parent, false);

        editText = convertView.findViewById(R.id.editText);

        StationPrice.GetText();
        return convertView;
    }

    public void SetText(String precioProducto , String direccion) {
        editText.setText(precioProducto + "  " + direccion);
    }

}