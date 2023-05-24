package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class UserItems extends AppCompatActivity {
ListView listView;
    ArrayAdapter ItemArrayAdapter;

    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_items);

        listView=(ListView) findViewById(R.id.lv1);
        db = new DBHelper(this);
        Bundle extras = getIntent().getExtras();
        String value="";
        if (extras != null) {
            value = extras.getString("username");
        }

        ArrayList<Item> items = db.getUserItems(value);

        ItemAdapter itemAdapter = new ItemAdapter(this, R.layout.activity_custom_list_view, items);

        listView.setAdapter(itemAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int in, long l) {
                Item clickeditem = (Item)adapterView.getItemAtPosition(in);
                new AlertDialog.Builder(UserItems.this)
                        .setTitle("Do you want to delete this item?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                db.deleteItem(clickeditem);
                                items.remove(in);
                                itemAdapter.notifyDataSetChanged();
                                Toast.makeText(UserItems.this, "Deleted Item: " +  clickeditem.getName(), Toast.LENGTH_SHORT).show();

                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }).create().show();
            }


        });

    }
}