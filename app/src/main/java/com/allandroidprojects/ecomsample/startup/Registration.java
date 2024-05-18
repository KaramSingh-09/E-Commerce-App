package com.allandroidprojects.ecomsample.startup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.allandroidprojects.ecomsample.R;

public class Registration extends AppCompatActivity {
    EditText username, Password, rePassword;
    Button singup, singin;
    dbManager db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        username = (EditText) findViewById(R.id.username);
        Password = (EditText) findViewById(R.id.Password);
        rePassword = (EditText) findViewById(R.id.rePassword);
        singup = (Button) findViewById(R.id.btnsingup);
        singin = (Button) findViewById(R.id.btnsingin);
        db = new dbManager(this);


        singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            String user = username.getText().toString();
            String Pass = Password.getText().toString();
            String rePass = rePassword.getText().toString();

            if (user.equals("")||Pass.equals("")||rePass.equals(""))
                Toast.makeText(Registration.this, "please enter all the fields", Toast.LENGTH_SHORT).show();
            else {
                if(Pass.equals(rePass)){
                    boolean checkuser = db.checkusername(user);
                    if(checkuser==false){
                        boolean insert =db.insertData(user,Pass);
                        if (insert==true){
                            Toast.makeText(Registration.this, "Registered successfully", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(getApplicationContext(),Home.class);
                            startActivity(intent);

                        }
                        else {
                            Toast.makeText(Registration.this, "Registrations failed", Toast.LENGTH_SHORT).show();
                        }

                    }
                    else{
                        Toast.makeText(Registration.this, "user already exists! please sign in", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(Registration.this, "Password not matching", Toast.LENGTH_SHORT).show();

                }
            }
            }
        });

        singin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),MyAccount.class);
                startActivity(intent);
            }
        });
    }
}