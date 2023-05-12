package com.example.socialgift.ui.views.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.socialgift.API.APIRequest;
import com.example.socialgift.API.VolleyCallback;
import com.example.socialgift.MainActivity;
import com.example.socialgift.R;
import com.example.socialgift.ui.views.login.LoginActivity;

public class RegisterActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText nameEditText;
    private EditText surnameEditText;
    private Button createAccountButton;
    private Button loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nameEditText = findViewById(R.id.register_name_edit_text);
        surnameEditText = findViewById(R.id.register_surname_edit_text);
        emailEditText = findViewById(R.id.register_email_edit_text);
        passwordEditText = findViewById(R.id.register_password_edit_text);
        createAccountButton = findViewById(R.id.register_create_account_button);
        loginButton = findViewById(R.id.register_login_button);

        createAccountButton.setOnClickListener(v -> {
            if(nameEditText.getText().toString().isEmpty() || surnameEditText.getText().toString().isEmpty()
                    || emailEditText.getText().toString().isEmpty() || passwordEditText.getText().toString().isEmpty()){
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
            }else {
                createAccount(nameEditText.getText().toString(), surnameEditText.getText().toString(),
                        emailEditText.getText().toString(), passwordEditText.getText().toString());
            }
        });

        loginButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void createAccount(String name, String lastName, String email, String password) {
        APIRequest.registerRequest(name, lastName, email, password, "", this, new VolleyCallback() {
            @Override
            public void onSuccessResponseString(String result) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RegisterActivity.this, "Error: " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}