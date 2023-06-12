package com.example.socialgift.ui.fragments.profile.wishlists.details;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.socialgift.API.APIRequest;
import com.example.socialgift.API.VolleyCallbackProduct;
import com.example.socialgift.R;
import com.example.socialgift.model.Gift;
import com.example.socialgift.model.Product;
import com.example.socialgift.model.Wishlist;

import java.util.ArrayList;

public class WishlistDetailsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Wishlist wishlist;
    private ArrayList<Product> products = new ArrayList<>();
    private WishlistProductAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist_details);

        setUpViews();
        getWishlistProducts();

    }

    private void getWishlistProducts() {
        APIRequest apiRequest = new APIRequest(this);
        for(Gift g: wishlist.getGifts()){
            apiRequest.getGiftInformation(g.getProductURL(), new VolleyCallbackProduct() {
                @Override
                public void onSuccessResponse(Product result) {
                   products.add(result);
                    adapter = new WishlistProductAdapter(products, WishlistDetailsActivity.this, wishlist);
                    recyclerView.setAdapter(adapter);
                }

                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(WishlistDetailsActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    private void setUpViews() {
        ImageView backArrow = findViewById(R.id.wishlist_details_back_button);
        this.recyclerView = findViewById(R.id.wishlist_details_recycler_view);
        this.wishlist = (Wishlist) getIntent().getSerializableExtra("wishlist");
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        TextView wishlistName = findViewById(R.id.wishlist_details_title);
        wishlistName.setText(wishlist.getName());


        backArrow.setOnClickListener(v -> finish());
    }
}