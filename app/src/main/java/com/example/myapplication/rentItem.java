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

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class rentItem extends AppCompatActivity {
EditText ename, ephone , ecity, edistrict ;
    RadioButton radioButton;
    Button placeOrder;
    TextView tinfo;
    DBHelper dbHelper;
    Item item;
    int itemId;

    private MeowBottomNavigation bottomNavigation;
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

        bottomNavigation = findViewById(R.id.bottomNavigation);
        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.baseline_logout_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.baseline_add_circle_outline_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.baseline_view_headline_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(4, R.drawable.ic_home));
        bottomNavigation.add(new MeowBottomNavigation.Model(5, R.drawable.baseline_receipt_24));

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