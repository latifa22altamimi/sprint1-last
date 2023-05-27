package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class RentalAdapter extends ArrayAdapter<Rental> {
    Context context;
    int resource;
    DBHelper db;

    public RentalAdapter(@NonNull Context context, int resource, @Nullable List<Rental> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource, parent, false  );
        TextView tvName = (TextView)convertView.findViewById(R.id.nameTextView);
        TextView tvCost = (TextView)convertView.findViewById(R.id.costTextView);
        ImageView itemImage = (ImageView) convertView.findViewById(R.id.ImageIcon);
       Rental currentRental = getItem(position);
        tvName.setText(currentRental.getItemname());




            tvCost.setText(String.valueOf(currentRental.getTotalPrice())+" SR");


        Bitmap bitmap = BitmapFactory.decodeByteArray(currentRental.getPhoto(), 0,currentRental.getPhoto().length);
     itemImage.setImageBitmap(bitmap);


        return convertView;
    }



}
