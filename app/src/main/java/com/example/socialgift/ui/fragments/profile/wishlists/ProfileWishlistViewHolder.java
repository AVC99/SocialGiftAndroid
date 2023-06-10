package com.example.socialgift.ui.fragments.profile.wishlists;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialgift.R;

public class ProfileWishlistViewHolder extends RecyclerView.ViewHolder{
    private TextView wishlistName;
    private TextView wishlistDescription;
    private TextView wishlistProductsCount;
    private TextView wishlistEndDate;

    public ProfileWishlistViewHolder(@NonNull View itemView) {
        super(itemView);
        wishlistName = itemView.findViewById(R.id.wishlist_card_name);
        wishlistDescription = itemView.findViewById(R.id.wishlist_card_description);
        wishlistProductsCount = itemView.findViewById(R.id.wishlist_card_number_of_products);
        wishlistEndDate = itemView.findViewById(R.id.wishlist_card_end_date);
    }

    public TextView getWishlistName() {
        return wishlistName;
    }

    public TextView getWishlistDescription() {
        return wishlistDescription;
    }

    public TextView getWishlistProductsCount() {
        return wishlistProductsCount;
    }

    public TextView getWishlistEndDate() {
        return wishlistEndDate;
    }
}
