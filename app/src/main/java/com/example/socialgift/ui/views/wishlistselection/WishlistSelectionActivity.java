package com.example.socialgift.ui.views.wishlistselection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.VolleyError;
import com.example.socialgift.API.APIRequest;
import com.example.socialgift.API.Endpoints;
import com.example.socialgift.API.VolleyCallbackWishlistArray;
import com.example.socialgift.R;
import com.example.socialgift.model.Post;
import com.example.socialgift.model.Product;
import com.example.socialgift.model.Wishlist;

import java.util.ArrayList;

public class WishlistSelectionActivity extends AppCompatActivity {

    private RecyclerView wishlistRecyclerView;
    private ImageView backButton;
    private WishlistSelectionAdapter wishlistSelectionAdapter;
    private ArrayList<Wishlist> wishlists = new ArrayList<>();
    private Post post;
    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist_selection);
        setUpViews();
        getUserWishlists();
    }

    private void getUserWishlists() {
        APIRequest apiRequest = new APIRequest(this);
        apiRequest.getUserWishlists(this.getSharedPreferences(this.getString(R.string.shared_preferences), Context.MODE_PRIVATE).getInt(this.getString(R.string.saved_user_id_key), -1), new VolleyCallbackWishlistArray() {
            @Override
            public void onSuccessResponse(ArrayList<Wishlist> result) {
                wishlists = result;

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(WishlistSelectionActivity.this);
                wishlistSelectionAdapter = new WishlistSelectionAdapter(wishlists, WishlistSelectionActivity.this, post);
                wishlistRecyclerView.setAdapter(wishlistSelectionAdapter);
                wishlistRecyclerView.setLayoutManager(linearLayoutManager);

            }

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }

    private void setUpViews() {
        wishlistRecyclerView = findViewById(R.id.wishlist_selection_recycler_view);
        backButton = findViewById(R.id.wishlist_selection_back_image_view);
        post = (Post) getIntent().getExtras().getSerializable("post");
        if (post == null) {
            product = (Product) getIntent().getExtras().getSerializable("product");
            post = new Post("username", "a", product.getName(), product.getPrice(),
                    "", "", product.getId(), Endpoints.PRODUCTS + product.getId());
        }


        backButton.setOnClickListener(v -> {
            finish();
        });

    }
}