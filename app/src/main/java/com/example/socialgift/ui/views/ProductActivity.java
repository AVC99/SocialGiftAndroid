package com.example.socialgift.ui.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.socialgift.R;
import com.example.socialgift.model.Post;
import com.example.socialgift.model.Product;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ProductActivity extends AppCompatActivity {

    private Post post;
    private Product product;
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
        post = (Post) getIntent().getSerializableExtra("product");
        backActionButton = findViewById(R.id.product_back_fab);
        addToWishlistActionButton = findViewById(R.id.product_add_to_wishlist_fab);
        addToWishlistActionButton.hide();
        productImage = findViewById(R.id.product_product_image_view);
        productName = findViewById(R.id.product_product_name);
        productPrice = findViewById(R.id.product_product_price);
        productDescription = findViewById(R.id.product_product_description);
        product = (Product) getIntent().getSerializableExtra("searchProduct");


        if (post != null) {
            productName.setText(post.getGiftName());
            productPrice.setText(post.getGiftPrice().toString() + " €");
            productDescription.setText(post.getDescription());

            Glide.with(this).load(post.getPostImage()).placeholder(R.drawable.image_not_found)
                    .error(R.drawable.image_not_found).into(productImage);
        }
        if (product != null) {
            productName.setText(product.getName());
            productPrice.setText(product.getPrice().toString() + " €");
            productDescription.setText(product.getDescription());

            Glide.with(this).load(product.getImageURL()).placeholder(R.drawable.image_not_found)
                    .error(R.drawable.image_not_found).into(productImage);
        }

        backActionButton.setOnClickListener(v -> {
            finish();
        });
    }
}