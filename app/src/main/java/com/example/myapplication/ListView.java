package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class ListView extends AppCompatActivity {
    android.widget.ListView ListViewJava;
    DBHelper db;

    ArrayAdapter ItemArrayAdapter;


    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        id = getIntent().getIntExtra("id", 0);
        db = new DBHelper(this);
        ListViewJava = (android.widget.ListView) findViewById(R.id.listView);

        ArrayList<Item> items = db.getAllItems();

        ItemAdapter itemAdapter = new ItemAdapter(this, R.layout.activity_custom_list_view, items);

        ListViewJava.setAdapter(itemAdapter);

        ListViewJava.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int in, long l) {
                Item clickeditem = (Item)adapterView.getItemAtPosition(in);
                new AlertDialog.Builder(ListView.this)
                        .setTitle("Do you want to delete this item?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                db.deleteItem(clickeditem);
                                items.remove(in);
                                itemAdapter.notifyDataSetChanged();
                                Toast.makeText(ListView.this, "Deleted Item: " +  clickeditem.getName(), Toast.LENGTH_SHORT).show();

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
