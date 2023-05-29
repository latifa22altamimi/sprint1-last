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

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class additem extends AppCompatActivity {

    EditText EditTextname, Edescription, Ecost;
    Button btn_add, btnupload;

    ImageView imageView;
    DBHelper db;
    byte[] image = null;

    private MeowBottomNavigation bottomNavigation;

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
                    Bundle extras = getIntent().getExtras();

                        //The key argument here must match that used in the other activity



                    item = new Item(name, description, Double.parseDouble(price), image,"avaliable",Account.username);
                 boolean added=  db.addItem(item);
if(added==true) {
    Toast.makeText(additem.this, "added", Toast.LENGTH_LONG).show();
    Intent intent = new Intent(getApplicationContext(), UserItems.class);

    startActivity(intent);
}
else
                    Toast.makeText(additem.this, "item is already added", Toast.LENGTH_LONG).show();

                }

                catch (Exception ex) {
                    Toast.makeText(additem.this, "Enter valid input and upload an image", Toast.LENGTH_LONG).show();
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