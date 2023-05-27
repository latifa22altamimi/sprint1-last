package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemViewAll extends AppCompatActivity {
    android.widget.ListView ListViewJava;
    ImageView imageView;
    TextView idTextView;
    TextView nameTextView;
    TextView descriptionTextView;
    TextView statusTextView;
    TextView userTextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_view_all);

        // Get references to the TextViews and ImageView in your layout
        idTextView = findViewById(R.id.tv1);
        nameTextView = findViewById(R.id.tv2);
        descriptionTextView = findViewById(R.id.descriptionTextView);
        statusTextView = findViewById(R.id.statusTextView);
        userTextView = findViewById(R.id.userTextView);
        imageView = findViewById(R.id.image);


        // Get the item ID from the Intent
        Intent intent = getIntent();
        int itemId = intent.getIntExtra("itemId", -1);

        // Retrieve the item data from the database
        DBHelper db = new DBHelper(this);
        Item item = db.getItemById(itemId);

        if (item != null) {
            // Display the item data in the UI
            idTextView.setText(String.valueOf(item.getId()));
            nameTextView.setText(item.getName());
            descriptionTextView.setText(item.getDescription());
            statusTextView.setText(item.getStatus());
            userTextView.setText(item.getUser());

            if (item.getImage() != null) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(item.getImage(), 0, item.getImage().length);
                imageView.setImageBitmap(bitmap);
            }
        }
    }
}

