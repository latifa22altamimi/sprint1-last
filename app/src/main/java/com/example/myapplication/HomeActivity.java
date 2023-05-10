package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

import java.util.ArrayList;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class HomeActivity extends AppCompatActivity {
    Button logot;
    private MeowBottomNavigation bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ImageSlider imageSlider ;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        imageSlider = findViewById(R.id.imageSlider);


        ArrayList<SlideModel> images = new ArrayList<>();
        images.add(new SlideModel(R.drawable.phto1 , null));
        images.add(new SlideModel(R.drawable.photo2 , null));

        imageSlider.setImageList(images , ScaleTypes.CENTER_CROP);



        // من هنا يبدأ الكود
        bottomNavigation = findViewById(R.id.bottomNavigation);
        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.baseline_logout_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.baseline_add_circle_outline_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.ic_baseline_subscriptions_24));



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

                }

                return null;
            }
        });}}













