package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
Button rentButton;
TextView cost;
ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_view_all);
        back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }
        });



        // Get references to the TextViews and ImageView in your layout
        idTextView = findViewById(R.id.tv1);
        nameTextView = findViewById(R.id.tv2);
        descriptionTextView = findViewById(R.id.descriptionTextView);
        statusTextView = findViewById(R.id.statusTextView);
        userTextView = findViewById(R.id.userTextView);
        imageView = findViewById(R.id.image);
        rentButton=findViewById(R.id.buttonRent);
cost=findViewById(R.id.cost);
        // Get the item ID from the Intent
        Intent intent = getIntent();
        int itemId = intent.getIntExtra("itemId", -1);

        // Retrieve the item data from the database
        DBHelper db = new DBHelper(this);
        Item item = db.getItemById(itemId);

        if (item != null) {
            // Display the item data in the UI
            nameTextView.setText("                             "+item.getName());
            descriptionTextView.setText("Description:\n \n"+item.getDescription());

            cost.setText(String.valueOf(item.getCost())+" SR");

            if (item.getImage() != null) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(item.getImage(), 0, item.getImage().length);
                imageView.setImageBitmap(bitmap);
            }
        }
rentButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent i=new Intent(ItemViewAll.this,rentItem.class);
        i.putExtra("id",itemId);
        startActivity(i);
    }
});
    }
}

