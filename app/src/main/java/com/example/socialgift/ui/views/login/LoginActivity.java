package com.example.socialgift.ui.views.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.socialgift.R;
import com.example.socialgift.ui.views.register.RegisterActivity;

public class LoginActivity extends AppCompatActivity {

    private Button loginButton;
    private Button forgotPasswordButton;
    private Button registerButton;
    private EditText emailEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = findViewById(R.id.login_login_button);
        forgotPasswordButton = findViewById(R.id.login_forgot_password);
        registerButton = findViewById(R.id.login_create_account_button);
        emailEditText = findViewById(R.id.login_email);
        passwordEditText = findViewById(R.id.login_password);

        loginButton.setOnClickListener(v -> {
            if(emailEditText.getText().toString().isEmpty() || passwordEditText.getText().toString().isEmpty()){
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
            }else {
                loginToSocialGift();
            }
        });

        registerButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
            finish();
        });



    }

    private void loginToSocialGift() {
        //TODO: Call API and login to social gift
    }
}