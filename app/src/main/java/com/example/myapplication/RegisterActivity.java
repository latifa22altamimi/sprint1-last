package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    EditText username, password, email, phone;
    Button btnreg;

    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        btnreg = findViewById(R.id.btnreg);
        DB = new DBHelper(this);

        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String em= email.getText().toString();
                String ph= phone.getText().toString();

                if(user.equals("")||pass.equals("")||em.equals("")||ph.equals(""))
                    Toast.makeText(RegisterActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkUser = DB.checkUsername(user);
                    if(!checkUser){
                        Boolean checkEmail=DB.checkEmail(em);
                        Boolean checkPhone=DB.checkPhone(ph);
                        if (!checkEmail && !checkPhone) {
                          Boolean insert = DB.insertData(user, pass, em, ph);

                            if(insert){
                                Toast.makeText(RegisterActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(RegisterActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }

                        }else{
                            Toast.makeText(RegisterActivity.this, "Email or phone is exist try to change it", Toast.LENGTH_SHORT).show();
                        }

                    }
                    else{
                        Toast.makeText(RegisterActivity.this,"Already exists! please sign in",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }
}
