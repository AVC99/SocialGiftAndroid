package com.example.socialgift.ui.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.socialgift.R;

public class NotificationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        ImageView backButton = findViewById(R.id.notifications_back_button);

        backButton.setOnClickListener(v -> {
            finish();
        });
    }
}