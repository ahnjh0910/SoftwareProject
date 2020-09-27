package com.example.exer;

import androidx.appcompat.app.AppCompatActivity;
import android.*;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txtSlogan;
    Button btnSignIn, btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSignIn = (Button)findViewById(R.id.btnSignIn);
        btnSignUp = (Button)findViewById(R.id.btnSignUp);

        txtSlogan = (TextView)findViewById(R.id.txtSlogan);

        btnSignIn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent signUp = new Intent(MainActivity.this, signup.class);
                startActivity(signUp);
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view){
                Intent signIn = new Intent(MainActivity.this, signin.class);
                startActivity(signIn);
            }
        });
    }
}