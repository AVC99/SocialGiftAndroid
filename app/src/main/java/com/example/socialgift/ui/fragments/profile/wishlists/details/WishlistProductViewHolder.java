package com.example.socialgift.ui.fragments.profile.wishlists.details;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialgift.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class WishlistProductViewHolder extends RecyclerView.ViewHolder {
    private ImageView productImage;
    private TextView productName;
    private TextView productPrice;
    private FloatingActionButton deleteProductButton;

    public WishlistProductViewHolder(@NonNull View itemView) {
        super(itemView);
        this.productImage = itemView.findViewById(R.id.product_card_image);
        this.productName = itemView.findViewById(R.id.product_card_name);
        this.productPrice = itemView.findViewById(R.id.product_card_price);
        this.deleteProductButton = itemView.findViewById(R.id.product_card_fab);
    }

    public ImageView getProductImage() {
        return productImage;
    }

    public TextView getProductName() {
        return productName;
    }

    public TextView getProductPrice() {
        return productPrice;
    }

    public FloatingActionButton getDeleteProductButton() {
        return deleteProductButton;
    }
}
