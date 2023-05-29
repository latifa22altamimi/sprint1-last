package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
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

public class ItemAdapter extends ArrayAdapter<Item> {
    Context context;
    int resource;

    public ItemAdapter(@NonNull Context context, int resource, @Nullable List<Item> objects ) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource, parent, false);
        TextView tvName = convertView.findViewById(R.id.nameTextView);
        TextView tvCost = convertView.findViewById(R.id.costTextView);
        ImageView imgUser = convertView.findViewById(R.id.ImageIcon);
        Item currentItem = getItem(position);
        tvName.setText(currentItem.getName());

        if (currentItem.getCost() == 0) {
            tvCost.setText("");
        } else {
            tvCost.setText(String.valueOf(currentItem.getCost()) + " SR");
        }

        Bitmap bitmap = BitmapFactory.decodeByteArray(currentItem.getImage(), 0, currentItem.getImage().length);
        imgUser.setImageBitmap(bitmap);


        return convertView;
    }



}

