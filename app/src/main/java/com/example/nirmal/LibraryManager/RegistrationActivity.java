package com.example.nirmal.LibraryManager;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class RegistrationActivity extends AppCompatActivity {

    private EditText UserName, UserPassword, UserEmail;
    private Button RegButton;
    private TextView AlreadyUser;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        Details();

        firebaseAuth = FirebaseAuth.getInstance();

        RegButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    //Upload data to the database
                    String user_email = UserEmail.getText().toString().trim();
                    String user_password = UserPassword.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                //sendEmailVerification();

                                Toast.makeText(RegistrationActivity.this, "Successfully Registered!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
                            } else {
                                Toast.makeText(RegistrationActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            }

        });

        AlreadyUser.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationActivity.this, StudentActivity.class));

            }
        });


    }


    private void Details() {
        UserName = findViewById(R.id.etUser);
        UserPassword = findViewById(R.id.etUserPassword);
        UserEmail = findViewById(R.id.etUserEmail);
        RegButton = findViewById(R.id.btnRegister);
        AlreadyUser = findViewById(R.id.tvNewUser);
    }

    private Boolean validate() {
        Boolean result = false;

        String name = UserName.getText().toString();
        String password = UserPassword.getText().toString();
        String email = UserEmail.getText().toString();

        if (name.isEmpty() || password.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Please enter all the details", Toast.LENGTH_SHORT).show();
        } else {
            result = true;
        }

        return result;
    }
}