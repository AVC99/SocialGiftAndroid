package com.example.socialgift.ui.fragments.profile;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.VolleyError;
import com.example.socialgift.API.APIRequest;
import com.example.socialgift.API.VolleyCallbackUser;
import com.example.socialgift.R;
import com.example.socialgift.model.User;

public class EditProfileActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText lastNameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText profilePictureEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        setUpViews();

    }

    private void setUpViews() {
        ImageView backButton = findViewById(R.id.edit_profile_back_button);
        nameEditText = findViewById(R.id.edit_profile_name_edit_text);
        lastNameEditText = findViewById(R.id.edit_profile_last_name_edit_text);
        emailEditText = findViewById(R.id.edit_profile_email_edit_text);
        passwordEditText = findViewById(R.id.edit_profile_password_edit_text);
        profilePictureEditText = findViewById(R.id.edit_profile_image_link_edit_text);
        Button sendButton = findViewById(R.id.edit_profile_send_button);

        backButton.setOnClickListener(v -> finish());

        sendButton.setOnClickListener(v -> updateProfile());
    }

    private void updateProfile() {

        APIRequest apiRequest = new APIRequest(this);

        String name = nameEditText.getText().toString();
        String lastName = lastNameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String link = profilePictureEditText.getText().toString();


        if (name.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty() ||link.isEmpty()){
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
        }else{
            apiRequest.updateProfileRequest(name, lastName, email, password, link,  new VolleyCallbackUser() {
                @Override
                public void onSuccessResponse(User result) {
                    Toast.makeText(EditProfileActivity.this, "Profile updated", Toast.LENGTH_SHORT).show();
                    finish();
                }

                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(EditProfileActivity.this, "Error updating profile", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}