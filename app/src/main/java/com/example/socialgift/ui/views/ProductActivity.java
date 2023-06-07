package com.example.socialgift.ui.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.example.socialgift.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ProductActivity extends AppCompatActivity {

    private FloatingActionButton backActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        backActionButton = findViewById(R.id.product_back_fab);

        backActionButton.setOnClickListener(v -> {
            finish();
        });
    }
}