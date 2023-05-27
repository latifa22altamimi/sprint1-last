package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class rentItem extends AppCompatActivity {
EditText ename, ephone , ecity, edistrict ;
    RadioButton radioButton;
    Button placeOrder;
    TextView tinfo;
    DBHelper dbHelper;
    Item item;
    int itemId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent_item);
        ename=findViewById(R.id.Name);
        ephone=findViewById(R.id.Phone);
        ecity=findViewById(R.id.city);
edistrict=findViewById(R.id.district);
radioButton=findViewById(R.id.payment);
tinfo=(TextView) findViewById(R.id.shipcost);
        placeOrder=findViewById(R.id.place);
 dbHelper=new DBHelper(this);
        itemId=0;// value will be sent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            itemId = extras.getInt("id");

        }
         item = dbHelper.getItemById(itemId);
        tinfo.setText("shipping cost:19 SR \n\n item cost:"+item.getCost()+ " SR \n\n Total: "+(19+item.getCost())+" SR");
        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Rental rental;

                String itemuser=Account.username;





                String status="PLACED";
                String name=ename.getText().toString();
                String city =ecity.getText().toString();
                String district=edistrict.getText().toString();
                String phone=ephone.getText().toString();
byte[] photo=item.getImage();
                double price=19+item.getCost();//value will be sent
                try{


                   if(name.equals("") || city.equals("") || district.equals("") || phone.equals(""))
                    throw new Exception("inavalid input");
                    if(!radioButton.isChecked())
                    {
                        throw new RuntimeException();
                    }




                    rental = new Rental(itemuser,itemId,status,name,city,district,phone,price,photo,item.getName());
                    boolean added= dbHelper.addRental(rental);

if(added){
dbHelper.updateStatus(itemId);
    Toast.makeText(getApplicationContext(), "Your order placed ", Toast.LENGTH_LONG).show();
    Intent intent = new Intent(getApplicationContext(), UserOrders.class);

    startActivity(intent);

}

                }
                catch(RuntimeException e){
                    Toast.makeText(getApplicationContext(), "Please select the payment method", Toast.LENGTH_SHORT).show();

                }


                catch(Exception e){
                    Toast.makeText(getApplicationContext(), "Please fill all the fields", Toast.LENGTH_SHORT).show();

                }



            }
        });
    }
}