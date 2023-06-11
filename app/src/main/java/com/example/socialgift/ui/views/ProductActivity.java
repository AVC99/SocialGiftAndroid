package com.example.socialgift.ui.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.socialgift.R;
import com.example.socialgift.model.Post;
import com.example.socialgift.model.Product;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ProductActivity extends AppCompatActivity {

    private Post product;
    private FloatingActionButton backActionButton;
    private FloatingActionButton addToWishlistActionButton;
    private ImageView productImage;
    private TextView productName;
    private TextView productPrice;
    private TextView productDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        setUpViews();

    }

    private void setUpViews() {
        product = (Post) getIntent().getSerializableExtra("product");
        backActionButton = findViewById(R.id.product_back_fab);
        addToWishlistActionButton = findViewById(R.id.product_add_to_wishlist_fab);
        productImage = findViewById(R.id.product_product_image_view);
        productName = findViewById(R.id.product_product_name);
        productPrice = findViewById(R.id.product_product_price);
        productDescription = findViewById(R.id.product_product_description);

        productName.setText(product.getGiftName());
        productPrice.setText(product.getGiftPrice().toString()+" €");
        productDescription.setText(product.getDescription());

        Glide.with(this).load(product.getPostImage()).into(productImage);



        addToWishlistActionButton.setOnClickListener(v -> {

        });
        backActionButton.setOnClickListener(v -> {
            finish();
        });
    }
}