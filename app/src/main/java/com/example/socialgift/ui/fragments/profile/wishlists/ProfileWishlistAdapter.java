package com.example.socialgift.ui.fragments.profile.wishlists;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialgift.R;
import com.example.socialgift.model.Wishlist;

import java.util.ArrayList;

public class ProfileWishlistAdapter extends RecyclerView.Adapter<ProfileWishlistViewHolder>{

    private ArrayList<Wishlist> wishlists;
    private Context context;

    public ProfileWishlistAdapter(ArrayList<Wishlist> wishlists, Context context) {
        this.wishlists = wishlists;
        this.context = context;
    }

    @NonNull
    @Override
    public ProfileWishlistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wishlist_card, parent, false);
        return new ProfileWishlistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileWishlistViewHolder holder, int position) {
        position = holder.getAdapterPosition();
        Wishlist wishlist = wishlists.get(position);
        holder.getWishlistName().setText(wishlist.getName());
        holder.getWishlistDescription().setText(wishlist.getDescription());
        holder.getWishlistProductsCount().setText("Number of gifts: "+wishlist.getGifts().size());
        holder.getWishlistEndDate().setText("End date: "+wishlist.getEndDate());

    }

    @Override
    public int getItemCount() {
        return this.wishlists.size();
    }
}
