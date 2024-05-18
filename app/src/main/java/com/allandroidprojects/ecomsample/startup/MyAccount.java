package com.allandroidprojects.ecomsample.startup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.allandroidprojects.ecomsample.R;

public class MyAccount extends AppCompatActivity
   {
     EditText username,Password;
     Button btnlogin;
     dbManager db;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        username = (EditText) findViewById(R.id.username1);
        Password = (EditText) findViewById(R.id.Password1);
        btnlogin = (Button) findViewById(R.id.btnsingin1);
        db = new dbManager (this);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String user = username.getText().toString();
                String Pass = Password.getText().toString();

                if(user.equals("")||Pass.equals(""))
                Toast.makeText(MyAccount.this, "Please enter the fields", Toast.LENGTH_SHORT).show();
                 else {
                 boolean checkuserPass = db.checkusernamePassword(user,Pass);
                    if(checkuserPass==true){
                        Toast.makeText(MyAccount.this, "sing in successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),Home.class);
                    startActivity(intent);
                    }else
                        Toast.makeText(MyAccount.this, "Invalid Credentials ", Toast.LENGTH_SHORT).show();


                }
            }
        });
      }

   }
