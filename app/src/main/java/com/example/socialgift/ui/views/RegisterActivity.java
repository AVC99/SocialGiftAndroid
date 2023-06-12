package com.example.socialgift.ui.views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.VolleyError;
import com.example.socialgift.API.APIRequest;
import com.example.socialgift.API.VolleyCallback;
import com.example.socialgift.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText nameEditText;
    private EditText surnameEditText;
    private EditText linkEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Find all the views
        nameEditText = findViewById(R.id.register_name_edit_text);
        surnameEditText = findViewById(R.id.register_surname_edit_text);
        emailEditText = findViewById(R.id.register_email_edit_text);
        passwordEditText = findViewById(R.id.register_password_edit_text);
        Button createAccountButton = findViewById(R.id.register_create_account_button);
        Button loginButton = findViewById(R.id.register_login_button);
        linkEditText = findViewById(R.id.register_profile_image_link_edit_text);


        //Set the activity result launcher

        createAccountButton.setOnClickListener(v -> {
            if (nameEditText.getText().toString().isEmpty() || surnameEditText.getText().toString().isEmpty()
                    || emailEditText.getText().toString().isEmpty() || passwordEditText.getText().toString().isEmpty()
                    || linkEditText.getText().toString().isEmpty()) {
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
            } else {
                createAccount();
            }
        });

        loginButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        });


    }

    private void createAccount() {
        APIRequest apiRequest = new APIRequest(this);
       apiRequest.register(nameEditText.getText().toString(),surnameEditText.getText().toString(),
               emailEditText.getText().toString(), passwordEditText.getText().toString(),
               linkEditText.getText().toString(), new VolleyCallback() {
           @Override
           public void onSuccessResponseString(String result) {
               Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
               startActivity(intent);
               finish();
           }

           @Override
           public void onErrorResponse(VolleyError error) {
               if (error.networkResponse.statusCode == 500) {
                   Toast.makeText(RegisterActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
               }else if (error.networkResponse.statusCode == 409) {
                   Toast.makeText(RegisterActivity.this, "Email already exists", Toast.LENGTH_SHORT).show();
               } else {
                   Toast.makeText(RegisterActivity.this, "Error", Toast.LENGTH_SHORT).show();
               }
           }
       });
    }


}