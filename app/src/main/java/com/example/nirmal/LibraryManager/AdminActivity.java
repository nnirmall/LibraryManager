package com.example.nirmal.LibraryManager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AdminActivity extends AppCompatActivity {

    private EditText AdminUser;
    private EditText AdminPassword;
    private Button AdminLogin;
    private TextView NotAdmin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        AdminUser= findViewById(R.id.etAdminUser);
        AdminPassword= findViewById(R.id.etAdminPassword);
        AdminLogin= findViewById(R.id.btnAdminLogin);
        NotAdmin= findViewById(R.id.tvNotAdmin);


        AdminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String admin = AdminUser.getText().toString();
                String password = AdminPassword.getText().toString();
                if (admin.equals("aaa") && password.equals("aaa")) {
                    startActivity(new Intent(AdminActivity.this, AdminDataActivity.class));
                }
                else{
                    Toast.makeText(getApplicationContext(),"Invalid Login",Toast.LENGTH_SHORT).show();
                }
            }
        });

        NotAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminActivity.this,MainActivity.class));
            }
        });

    }
}
