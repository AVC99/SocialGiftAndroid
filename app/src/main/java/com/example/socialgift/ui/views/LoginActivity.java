package com.example.socialgift.ui.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.socialgift.API.APIRequest;
import com.example.socialgift.API.VolleyCallback;
import com.example.socialgift.MainActivity;
import com.example.socialgift.R;

public class LoginActivity extends AppCompatActivity {

    private Button loginButton;
    private Button registerButton;
    private EditText emailEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = findViewById(R.id.login_login_button);
        registerButton = findViewById(R.id.login_create_account_button);
        emailEditText = findViewById(R.id.login_email);
        passwordEditText = findViewById(R.id.login_password);


        //check if the user has the access token stored in shared preferences
        checkAccessToken();


        loginButton.setOnClickListener(v -> {
            if (emailEditText.getText().toString().isEmpty() || passwordEditText.getText().toString().isEmpty()) {
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
            } else {
                loginToSocialGift(emailEditText.getText().toString(), passwordEditText.getText().toString());
            }
        });

        registerButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
            finish();
        });

    }

    private void checkAccessToken() {
        //get access token from shared preferences
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.shared_preferences), MODE_PRIVATE);
        String accessToken = sharedPreferences.getString(getString(R.string.saved_access_token_key), null);
        int userId = sharedPreferences.getInt(getString(R.string.saved_user_id_key), -1);

        if (accessToken != null || userId != -1) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void loginToSocialGift(String email, String password) {

        APIRequest.loginRequest(email, password,this, new VolleyCallback() {
            @Override
            public void onSuccessResponseString(String result) {
                //Save the access token in shared preferences
                SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.shared_preferences), MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(getString(R.string.saved_access_token_key), result);
                editor.apply();
                getUserID(email,result, editor);
            }
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getUserID(String email,String token,  SharedPreferences.Editor editor) {
        APIRequest apiRequest = new APIRequest(this);

        apiRequest.getUserId(email,token, new VolleyCallback() {
            @Override
            public void onSuccessResponseString(String result) {
                editor.putInt(getString(R.string.saved_user_id_key), Integer.parseInt(result));
                editor.apply();
                //start main activity
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}