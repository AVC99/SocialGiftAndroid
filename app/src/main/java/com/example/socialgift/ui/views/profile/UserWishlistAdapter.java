package com.example.socialgift.ui.views.profile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialgift.R;
import com.example.socialgift.model.Wishlist;

import java.util.ArrayList;

public class UserWishlistAdapter extends RecyclerView.Adapter<UserWishlistViewHolder> {
    private ArrayList<Wishlist> wishlists;
    private Context context;

    public UserWishlistAdapter(ArrayList<Wishlist> wishlists, Context context) {
        this.wishlists = wishlists;
        this.context = context;
    }

    @NonNull
    @Override
    public UserWishlistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_wishlist_card, parent, false);
        return new UserWishlistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserWishlistViewHolder holder, int position) {
        Wishlist wishlist = wishlists.get(position);
        holder.getWishlistName().setText(wishlist.getName());
        holder.getWishlistDescription().setText(wishlist.getDescription());
        holder.getWishlistProductsCount().setText("Number of gifts: " + wishlist.getGifts().size());
        holder.getWishlistEndDate().setText("End date: " + wishlist.getEndDate());
    }

    @Override
    public int getItemCount() {
        if (this.wishlists == null) return 0;

        return this.wishlists.size();
    }
}
