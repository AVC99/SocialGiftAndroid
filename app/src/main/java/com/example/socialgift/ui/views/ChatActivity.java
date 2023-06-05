package com.example.socialgift.ui.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.socialgift.R;

public class ChatActivity extends AppCompatActivity {

    private ImageView backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        backButton = findViewById(R.id.chat_back_button_image_view);

        backButton.setOnClickListener(v -> {
            finish();
        });
    }
}