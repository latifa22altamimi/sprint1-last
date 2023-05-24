package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent_item);
        ename=findViewById(R.id.Name);
        ephone=findViewById(R.id.Phone);
        ecity=findViewById(R.id.city);
edistrict=findViewById(R.id.district);
radioButton=findViewById(R.id.payment);
tinfo=findViewById(R.id.shipcost);
        placeOrder=findViewById(R.id.place);

        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Rental rental=null;
                String itemuser="";//value will be sent
                int itemId;// value will be sent

                String status="PLACED";
                String name=ename.getText().toString();
                String city =ecity.getText().toString();
                String district=edistrict.getText().toString();
                String phone=ephone.getText().toString();

                double price=0;//value will be sent
                try{
                    if(!radioButton.isChecked())
                    {
                        Toast.makeText(getApplicationContext(), "Please payment method", Toast.LENGTH_SHORT).show();
                    }
                    else{


                    }

                }catch(Exception e){


                }



            }
        });
    }
}