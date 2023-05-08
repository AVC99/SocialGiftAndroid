package com.example.socialgift.ui.views.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.socialgift.Endpoints;
import com.example.socialgift.R;
import com.example.socialgift.ui.views.register.RegisterActivity;

import org.json.JSONException;
import org.json.JSONObject;

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

    private void loginToSocialGift(String email, String password) {
        try {
            //TODO: Call API and login to social gift

            JSONObject postData = new JSONObject();
            postData.put("email", email);
            postData.put("password", password);


            //Trust the SSL certificate

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.POST, Endpoints.LOGIN, postData,
                            response -> {
                                try {
                                    Log.d("LOGIN-TOKEN", response.getJSONObject("accessToken").toString());
                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }
                            },
                            error -> {
                                // TODO: Handle error
                                Log.d("LOGIN-ERROR", error.toString());
                                Toast.makeText(this, error.toString(), Toast.LENGTH_SHORT).show();
                            });

            // Access the RequestQueue through your singleton class.
            Volley.newRequestQueue(this).add(jsonObjectRequest);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}