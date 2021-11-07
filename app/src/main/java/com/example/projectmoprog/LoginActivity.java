package com.example.projectmoprog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.projectmoprog.MainActivity;
import com.google.android.material.snackbar.Snackbar;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);

        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    Intent i = new Intent(LoginActivity.this, MainActivity.class );
                    i.putExtra(MainActivity.EXTRA_USERNAME, etUsername.getText().toString());
                    i.putExtra(MainActivity.EXTRA_PASSWORD, etPassword.getText().toString());
                    startActivity(i);
                }
                else{
                    Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Input Credential First!!!", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            }
        });
    }

    private boolean validate(){
        if(etUsername.getText().toString().equals("")) return false;
        if(etPassword.getText().toString().equals("")) return false;
        else return true;
    }


}