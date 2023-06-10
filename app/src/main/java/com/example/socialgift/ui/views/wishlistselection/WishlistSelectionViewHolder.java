package com.example.socialgift.ui.views.wishlistselection;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialgift.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class WishlistSelectionViewHolder extends RecyclerView.ViewHolder{
    private TextView wishlistName;
    private FloatingActionButton addButton;

    public WishlistSelectionViewHolder(@NonNull View itemView) {
        super(itemView);
        this.wishlistName = itemView.findViewById(R.id.wishlist_selection_card_name);
        this.addButton = itemView.findViewById(R.id.wishlist_selection_card_add_fab);
    }

    public TextView getWishlistName() {
        return wishlistName;
    }

    public FloatingActionButton getAddButton() {
        return addButton;
    }
}
