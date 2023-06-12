package com.example.socialgift.ui.views.profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.example.socialgift.API.APIRequest;
import com.example.socialgift.API.VolleyCallbackWishlistArray;
import com.example.socialgift.R;
import com.example.socialgift.model.User;
import com.example.socialgift.model.Wishlist;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class UserProfile extends AppCompatActivity {

    private RecyclerView recyclerView;
    private UserWishlistAdapter adapter;
    private FloatingActionButton backButton;
    private TextView userName;
    private TextView userEmail;
    private ImageView userImage;
    private ArrayList<Wishlist> wishlists = new ArrayList<>();
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        setUpViews();
        getUserWishlists();
    }

    private void getUserWishlists() {
        APIRequest apiRequest = new APIRequest(this);

        apiRequest.getUserWishlists(user.getId(), new VolleyCallbackWishlistArray() {
            @Override
            public void onSuccessResponse(ArrayList<Wishlist> result) {
                wishlists = result;
                adapter = new UserWishlistAdapter(wishlists, UserProfile.this);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

    }

    private void setUpViews() {
        this.recyclerView = findViewById(R.id.user_profile_recycler_view);
        this.backButton = findViewById(R.id.user_profile_back_fab);
        this.userName = findViewById(R.id.user_profile_name);
        this.userEmail = findViewById(R.id.user_profile_mail);
        this.userImage = findViewById(R.id.user_profile_image);
        this.user = (User) getIntent().getSerializableExtra("user");


        Glide.with(this)
                .load(user.getImage())
                .placeholder(R.drawable.image_not_found)
                .error(R.drawable.image_not_found)
                .into(this.userImage);


        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.userName.setText(user.getName());
        this.userEmail.setText(user.getEmail());

        this.backButton.setOnClickListener(v -> finish());

    }
}