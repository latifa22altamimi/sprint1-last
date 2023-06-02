package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

import java.util.ArrayList;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class UserItems extends AppCompatActivity {
    ListView listView;
    ArrayAdapter ItemArrayAdapter;

    DBHelper db;

    private MeowBottomNavigation bottomNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_items);

        listView =(ListView) findViewById(R.id.lv1);
        db = new DBHelper(this);


        ArrayList<Item> items = db.getUserItems(Account.username);

        ItemAdapter itemAdapter = new ItemAdapter(this, R.layout.activity_custom_list_view2, items);

        listView.setAdapter(itemAdapter);

        bottomNavigation = findViewById(R.id.bottomNavigation);
        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.baseline_logout_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.baseline_add_circle_outline_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.baseline_view_headline_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(4, R.drawable.ic_home));
        bottomNavigation.add(new MeowBottomNavigation.Model(5, R.drawable.baseline_receipt_24));




                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int in, long l) {
                        Item clickeditem = (Item) adapterView.getItemAtPosition(in);
                        new AlertDialog.Builder(UserItems.this)
                                .setTitle("Do you want to delete this item?")
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        db.deleteItem(clickeditem);
                                        items.remove(in);
                                        itemAdapter.notifyDataSetChanged();
                                        Toast.makeText(UserItems.this, "Deleted Item: " + clickeditem.getName(), Toast.LENGTH_SHORT).show();

                                    }
                                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                    }
                                }).create().show();
                    }


                });


        bottomNavigation.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                Intent intent;



                switch (model.getId()){

                    case 1 :
                        intent = new Intent(getApplicationContext() , LoginActivity.class);

                        startActivity(intent);

                        break;
                    case 2 :
                        intent = new Intent(getApplicationContext() , additem.class);




                        startActivity(intent);
                        break;

                    case 3:
                        intent = new Intent(getApplicationContext() , UserItems.class);




                        startActivity(intent);
                        break;
                    case 4:
                        intent=new Intent(getApplicationContext(),HomeActivity.class);


                        startActivity(intent);
                        break;

                    case 5:
                        intent=new Intent(getApplicationContext(),UserOrders.class);
                        startActivity(intent);
                        break;


                }

                return null;
            }
        });

    }
}