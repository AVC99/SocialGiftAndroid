package com.example.socialgift.ui.fragments.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.socialgift.API.APIRequest;
import com.example.socialgift.API.VolleyCallback;
import com.example.socialgift.R;
import com.example.socialgift.ui.views.LoginActivity;

public class OptionsActivity extends AppCompatActivity {
    private ImageView backButton;
    private Button logoutButton;
    private Button deleteAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        backButton = findViewById(R.id.options_back_button);
        logoutButton = findViewById(R.id.options_logout_button);
        deleteAccountButton = findViewById(R.id.options_delete_account_button);

        backButton.setOnClickListener(v -> finish());

        logoutButton.setOnClickListener(v -> {
            removeSharedPreferences();
            goToLoginActivity();
        });
        deleteAccountButton.setOnClickListener(v -> {
            removeSharedPreferences();
            deleteAccount();
            goToLoginActivity();
        });
    }

    private void deleteAccount() {
        APIRequest apiRequest = new APIRequest(this);
        apiRequest.deleteAccount(this, new VolleyCallback() {
            @Override
            public void onSuccessResponseString(String result) {
                Toast.makeText(OptionsActivity.this, "Account deleted", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(OptionsActivity.this, "Error while deleting the account", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void goToLoginActivity() {
        // Go to login activity
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void removeSharedPreferences() {
        // remove access token and id from shared preferences
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.shared_preferences), MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(getString(R.string.saved_access_token_key));
        editor.remove(getString(R.string.saved_user_id_key));
        editor.apply();
    }
}