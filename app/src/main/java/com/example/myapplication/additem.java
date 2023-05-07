package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class additem extends AppCompatActivity {
    EditText name, description, cost;
    Button btn_add, btnupload;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additem);

        name = (EditText) findViewById(R.id.itemname);
        description = (EditText) findViewById(R.id.description);
        cost = (EditText) findViewById(R.id.cost);
        btn_add = findViewById(R.id.add);
        btnupload = (Button) findViewById(R.id.upload);
        imageView = (ImageView) findViewById(R.id.imageView);
        btnupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 3);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK && data != null){
            Uri selectedImage=data.getData();
            imageView.setImageURI(selectedImage);


        }
    }
}