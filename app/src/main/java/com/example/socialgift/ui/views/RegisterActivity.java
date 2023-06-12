package com.example.socialgift.ui.views;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.socialgift.API.APIRequest;
import com.example.socialgift.API.VolleyCallback;
import com.example.socialgift.MainActivity;
import com.example.socialgift.R;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegisterActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText nameEditText;
    private EditText surnameEditText;
    private ActivityResultLauncher<Intent> mGetContent;

    private CircleImageView profileImageView;

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
        ImageButton addProfileImageButton = findViewById(R.id.register_add_profile_image_button);
        profileImageView = findViewById(R.id.register_add_profile_image_circle_image);

        //Set the activity result launcher
        mGetContent = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            Uri imageUri = data.getData();
                            // Handle the image
                            try {
                                profileImageView.setImageBitmap(MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri));
                                uploadImage(imageUri);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
        //Set the listeners
        createAccountButton.setOnClickListener(v -> {
            if (nameEditText.getText().toString().isEmpty() || surnameEditText.getText().toString().isEmpty()
                    || emailEditText.getText().toString().isEmpty() || passwordEditText.getText().toString().isEmpty()) {
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
            } else {
                createAccount(nameEditText.getText().toString(), surnameEditText.getText().toString(),
                        emailEditText.getText().toString(), passwordEditText.getText().toString());
            }
        });

        loginButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        });

        addProfileImageButton.setOnClickListener(v -> {
            Toast.makeText(this, "Not implemented yet", Toast.LENGTH_SHORT).show();

            Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            mGetContent.launch(i);

        });
    }

    private void uploadImage(Uri imageUri) {
       /* APIRequest.uploadImageRequest(imageUri, this, new VolleyCallback() {
            @Override
            public void onSuccessResponseString(String result) {
                Toast.makeText(RegisterActivity.this, "Image uploaded", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RegisterActivity.this, "Error: " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });*/
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
                if (error.networkResponse.statusCode == 409) {
                    Toast.makeText(RegisterActivity.this, "User already exists", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RegisterActivity.this, "Error: " + error, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}