package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.widget.DatePicker;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;


public class UserOrders extends AppCompatActivity {
    ListView listView;
    DBHelper db;

    private MeowBottomNavigation bottomNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_orders);
        db=new DBHelper(this);
        listView=(ListView) findViewById(R.id.orderList);
        ArrayList<Rental> rentals = db.getUserOrders(Account.username);

        RentalAdapter rentalAdapter = new RentalAdapter(this, R.layout.activity_custom_list_view, rentals);

       listView.setAdapter(rentalAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int in, long l) {
                Rental clickedRental = (Rental) adapterView.getItemAtPosition(in);
                new AlertDialog.Builder(UserOrders.this)
                        .setTitle("Do you want to return this item?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                DatePickerDialog datePickerDialog = new DatePickerDialog(UserOrders.this, androidx.appcompat.R.style.Base_Theme_AppCompat , new DatePickerDialog.OnDateSetListener() {
                                    @Override
                                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                                        String selectDate = day+"/"+(month + 1)+"/"+year;

                                        boolean your_date_is_outdated;

                                        //Try out
                                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                                        Date strDate = null;
                                        try {
                                            strDate = sdf.parse(selectDate);
                                        } catch (ParseException e) {
                                            throw new RuntimeException(e);
                                        }
                                        if (new Date().after(strDate)) {
                                            your_date_is_outdated = true;
                                        }
                                        else{
                                            your_date_is_outdated = false;
                                        }

                                        if (your_date_is_outdated){
                                            Toast.makeText(UserOrders.this, "Pick another date", Toast.LENGTH_SHORT).show();
                                        }else{

                                            db.deleteRent(clickedRental);
                                            rentals.remove(in);
                                            rentalAdapter.notifyDataSetChanged();
                                            Toast.makeText(UserOrders.this, clickedRental.getItemname() +" Returned successfully" , Toast.LENGTH_SHORT).show();

                                        }
                                    }
                                }, 2023, 01, 20);

                                datePickerDialog.show();

                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }).create().show();
            }


        });

        bottomNavigation = findViewById(R.id.bottomNavigation);
        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.baseline_logout_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.baseline_add_circle_outline_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.ic_baseline_subscriptions_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(4, R.drawable.ic_home));
        bottomNavigation.add(new MeowBottomNavigation.Model(5, R.drawable.ic_baseline_whatshot_24));


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