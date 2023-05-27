package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class UserOrders extends AppCompatActivity {
    ListView listView;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_orders);
        db=new DBHelper(this);
        listView=(ListView) findViewById(R.id.orderList);
        ArrayList<Rental> rentals = db.getUserOrders(Account.username);

        RentalAdapter rentalAdapter = new RentalAdapter(this, R.layout.activity_custom_list_view, rentals);

       listView.setAdapter(rentalAdapter);

    }
}