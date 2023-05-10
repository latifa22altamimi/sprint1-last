package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class additem extends AppCompatActivity {

    EditText EditTextname, Edescription, Ecost;
    Button btn_add, btnupload;

    ImageView imageView;
    DBHelper db;
    byte[] image = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additem);
        db = new DBHelper(this);
        EditTextname = (EditText) findViewById(R.id.itemname);
        Edescription = (EditText) findViewById(R.id.description);
        Ecost = (EditText) findViewById(R.id.cost);
        btn_add = findViewById(R.id.add);
        btnupload = (Button) findViewById(R.id.upload);

        imageView = (ImageView) findViewById(R.id.imageView);



        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Item item=null;
                String name = EditTextname.getText().toString();
                String description=Edescription.getText().toString();
//Double cost =Double.parseDouble(Ecost.getText().toString());
                String price=Ecost.getText().toString();
                try {
                    BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
                    Bitmap bitmap = drawable.getBitmap();

                    image = getBytes(bitmap);

                    if (name.equals("") || price.equals("")||  description.equals("")||image.length==0 )
                        throw new Exception("invalid input");
                    if(!price.equals("") && Double.parseDouble(price)<=0)
                        throw new Exception("invalid input");

                    if (imageView.getDrawable() == null)
                        throw new Exception("invalid input");
                    item = new Item(name, description, Double.parseDouble(price), image);
                    db.addItem(item);
                    Toast.makeText(addItem.this, "added", Toast.LENGTH_LONG).show();
                    Intent intent  =new Intent(getApplicationContext(),ListView.class);

                    startActivity(intent);

                }

                catch (Exception ex) {
                    Toast.makeText(addItem.this, "Enter valid input and upload an image", Toast.LENGTH_LONG).show();
                }
            }
        });
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
            Uri uri=data.getData();
//imageView.setImageURI(uri);

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap decodeStream=BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(decodeStream);
            } catch (Exception e) {
                Log.e("ex",e.getMessage());
            }



        }
    }
    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }
}