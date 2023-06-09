package com.example.socialgift.ui.fragments.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.socialgift.R;

public class EditProfileActivity extends AppCompatActivity {

    private ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        backButton = findViewById(R.id.edit_profile_back_button);


        backButton.setOnClickListener(v -> {
            finish();
        });
    }
}