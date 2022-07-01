package com.example.mp_sem_6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

@SuppressWarnings("ALL")
public class signup extends AppCompatActivity {
    TextInputLayout username_var,email_var,phone_var,password_var;

    public static final String MSG = "com.example.mp_sem_6.USERNAME";

    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.signup);

        username_var = findViewById(R.id.p_username_design);
        email_var = findViewById(R.id.p_email_design);
        phone_var = findViewById(R.id.p_phone_design);
        password_var = findViewById(R.id.p_password_design);
    }

    public void LoginBtnClick(View view) {
        Intent intent = new Intent(getApplicationContext(),login.class);
        startActivity(intent);
        finish();
    }

    public void registerbtnclick(View view)
    {
        String username_ = username_var.getEditText().getText().toString();
        String email_ = email_var.getEditText().getText().toString();
        String phone_ = phone_var.getEditText().getText().toString();
        String password_ = password_var.getEditText().getText().toString();



        if(!username_.isEmpty())
        {
            username_var.setError(null);
            username_var.setErrorEnabled(false);
            if(!email_.isEmpty())
            {
                email_var.setError(null);
                email_var.setErrorEnabled(false);
                if(!phone_.isEmpty())
                {
                    phone_var.setError(null);
                    phone_var.setErrorEnabled(false);
                    if(!password_.isEmpty())
                    {
                        password_var.setError(null);
                        password_var.setErrorEnabled(false);
                        if(email_.matches("^(.+)@(\\S+)$"))
                        {
                            firebaseDatabase = FirebaseDatabase.getInstance();
                            reference = firebaseDatabase.getReference("mp_sem-6");

                            String username_s = username_var.getEditText().getText().toString();
                            String email_s= email_var.getEditText().getText().toString();
                            String phone_s = phone_var.getEditText().getText().toString();
                            String password_s = password_var.getEditText().getText().toString();

                            storingdata storingdatass = new storingdata(username_s,email_s,phone_s,password_s);
                            reference.child(username_s).setValue(storingdatass);

                            Toast.makeText(getApplicationContext(),"Registered Successfully",Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(getApplicationContext(),navigate.class);
                            startActivity(intent);
                            finish();

//                            Intent intent = new Intent(getApplicationContext(),dashboard.class);
//                            intent.putExtra(MSG," WELCOME , " + username_s);
//                            startActivity(intent);
//                            finish();

                        }else
                            {
                                email_var.setError("InValid Email !");
                            }

                    }else
                        {
                            password_var.setError("Please Enter Your Password");
                        }

                }else
                    {
                        phone_var.setError("Please Enter Your Phone Number");
                    }

            }else
                {
                    email_var.setError("Please Enter Your Email Id");
                }

        }else
            {
                username_var.setError("Please Enter Your Username");
            }

    }
}