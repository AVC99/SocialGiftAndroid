package com.example.socialgift.ui.fragments.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.socialgift.API.APIRequest;
import com.example.socialgift.API.VolleyCallback;
import com.example.socialgift.API.VolleyCallbackUser;
import com.example.socialgift.R;
import com.example.socialgift.model.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfileActivity extends AppCompatActivity {

    private ImageView backButton;
    private CircleImageView profileImage;
    private FloatingActionButton editProfileImageButton;
    private EditText nameEditText;
    private EditText lastNameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        setUpViews();

    }

    private void setUpViews() {
        backButton = findViewById(R.id.edit_profile_back_button);
        profileImage = findViewById(R.id.edit_profile_profile_image_circle_image);
        editProfileImageButton = findViewById(R.id.edit_profile_add_profile_image_button);
        nameEditText = findViewById(R.id.edit_profile_name_edit_text);
        lastNameEditText = findViewById(R.id.edit_profile_last_name_edit_text);
        emailEditText = findViewById(R.id.edit_profile_email_edit_text);
        passwordEditText = findViewById(R.id.edit_profile_password_edit_text);
        sendButton = findViewById(R.id.edit_profile_send_button);

        backButton.setOnClickListener(v -> {
            finish();
        });

        sendButton.setOnClickListener(v -> {
            updateProfile();
        });
    }

    private void updateProfile() {
        //TODO: PROFILE PICTURE SHENANIGANS


        APIRequest apiRequest = new APIRequest(this);

        String name = nameEditText.getText().toString();
        String lastName = lastNameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if (name.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
        }else{
            apiRequest.updateProfileRequest(name, lastName, email, password, new VolleyCallbackUser() {
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