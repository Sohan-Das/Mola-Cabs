package com.example.mp_sem_6;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {
    Button signupbtn,loginbtn;
    TextInputLayout username_var,password_var;
    public static final String MSG = "com.example.mp_sem_6.USERNAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.login);

        signupbtn = findViewById(R.id.signup);
        loginbtn = findViewById(R.id.login);

        username_var = findViewById(R.id.l_username_design);
        password_var = findViewById(R.id.l_password_design);

        loginbtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String username_ = username_var.getEditText().getText().toString();
                String password_ = password_var.getEditText().getText().toString();

                if(!username_.isEmpty())
                {
                    username_var.setError(null);
                    username_var.setErrorEnabled(false);
                    if(!password_.isEmpty())
                    {
                        password_var.setError(null);
                        password_var.setErrorEnabled(false);

                        final String username_data = username_var.getEditText().getText().toString();
                        final String password_data = password_var.getEditText().getText().toString();

                        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                        DatabaseReference databaseReference = firebaseDatabase.getReference("mp_sem-6");

                        Query check_username = databaseReference.orderByChild("username").equalTo(username_data);

                        check_username.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                if(dataSnapshot.exists())
                                {
                                    username_var.setError(null);
                                    username_var.setErrorEnabled(false);
                                    String password_check = dataSnapshot.child(username_data).child("password").getValue(String.class);
                                    password_var.setError(null);
                                    password_var.setErrorEnabled(false);
                                    if(password_check.equals(password_data))
                                    {
                                        password_var.setError(null);
                                        password_var.setErrorEnabled(false);
                                        Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(),navigate.class);
                                        startActivity(intent);
                                        finish();


//                                        Intent intent = new Intent(getApplicationContext(),dashboard.class);
//                                        intent.putExtra(MSG," WELCOME , " + username_);
//                                        startActivity(intent);
//                                        finish();
                                    }else
                                        {
                                            password_var.setError("Wrong Password !");
                                        }

                                }else
                                    {
                                        username_var.setError("Username Does Not Exists");
                                    }


                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                Log.w(TAG, "Failed to read value.", databaseError.toException());
                            }
                        });

                    }else
                        {
                            password_var.setError("Please Enter Your Password");
                        }
                }else
                    {
                        username_var.setError("Please Enter Your Username");
                    }

            }
        });






        signupbtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(),signup.class);
                startActivity(intent);

            }
        });



    }

    public void navbutton(View view)
    {
        Intent intent = new Intent(getApplicationContext(),navigate.class);
        startActivity(intent);
        finish();
    }
}