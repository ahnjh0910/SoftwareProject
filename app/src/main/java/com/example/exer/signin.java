package com.example.exer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.exer.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class signin extends AppCompatActivity {
    EditText edtPhone, edtPassword;
    Button btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        edtPassword = (MaterialEditText)findViewById(R.id.edtPassword);
        edtPhone = (MaterialEditText)findViewById(R.id.edtPhone);
        btnSignIn = (Button)findViewById(R.id.btnSignIn);

        //Init Firebase
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        btnSignIn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view){

                final ProgressDialog mDialog = new ProgressDialog(signin.this);
                mDialog.setMessage("Please Waiting...");
                mDialog.show();

                table_user.addListenerForSingleValueEvent(new ValueEventListener(){
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        //Check if user not exist in database
                        if (dataSnapshot.child(edtPhone.getText().toString()).exists()) {
                        //Get user information
                            mDialog.dismiss();
                            User user = dataSnapshot.child(edtPhone.getText().toString()).getValue(User.class);
                            if (user.getPassword().equals(edtPassword.getText().toString())) {
                                Toast.makeText(signin.this, "Sign in successfully !", Toast.LENGTH_SHORT).show();
                            }
                            else if (user.getPassword().isEmpty())
                            {
                                Toast.makeText(signin.this, "Fuxk !!!", Toast.LENGTH_SHORT).show();
                            }
                                else {
                                Toast.makeText(signin.this, "Wrong password !!!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            mDialog.dismiss();
                           Toast.makeText(signin.this, "User not exist in Databae", Toast.LENGTH_SHORT).show();
                        }

                        }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }

        });
    }
}