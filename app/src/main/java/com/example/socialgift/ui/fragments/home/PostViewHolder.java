package com.example.socialgift.ui.fragments.home;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialgift.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class PostViewHolder extends RecyclerView.ViewHolder {
    private CircleImageView userImage;
    private TextView username;
    private TextView giftName;
    private TextView giftPrice;
    private ImageView postImage;
    private Button giftButton;
    private Button addToWishlistButton;

    public PostViewHolder(@NonNull View itemView) {
        super(itemView);
        this.userImage = itemView.findViewById(R.id.post_user_image);
        this.username = itemView.findViewById(R.id.post_user_name);
        this.giftName = itemView.findViewById(R.id.post_item_name);
        this.giftPrice = itemView.findViewById(R.id.post_item_price);
        this.postImage = itemView.findViewById(R.id.post_item_image);
        this.giftButton = itemView.findViewById(R.id.post_gift_button);
        this.addToWishlistButton = itemView.findViewById(R.id.post_wishlist_button);
    }

    public CircleImageView getUserImage() {
        return userImage;
    }
    public TextView getUsername() {
        return username;
    }

    public TextView getGiftName() {
        return giftName;
    }

    public TextView getGiftPrice() {
        return giftPrice;
    }

    public ImageView getPostImage() {
        return postImage;
    }

    public Button getGiftButton() {
        return giftButton;
    }

    public Button getAddToWishlistButton() {
        return addToWishlistButton;
    }

    public void setAddToWishlistButton(Button addToWishlistButton) {
        this.addToWishlistButton = addToWishlistButton;
    }
}
