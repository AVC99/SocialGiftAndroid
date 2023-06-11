package com.example.socialgift.ui.fragments.profile.friends;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialgift.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
;

import de.hdodenhof.circleimageview.CircleImageView;

public class FriendViewHolder extends RecyclerView.ViewHolder {
    private CircleImageView image;
    private TextView name;
    private TextView email;
    private FloatingActionButton removeFriendButton;

    public FriendViewHolder(@NonNull View itemView) {
        super(itemView);
        this.image = itemView.findViewById(R.id.friend_card_profile_image);
        this.name = itemView.findViewById(R.id.friend_card_username);
        this.email = itemView.findViewById(R.id.friend_card_email);
        this.removeFriendButton = itemView.findViewById(R.id.friend_card_remove_friend);
    }

    public CircleImageView getImage() {
        return image;
    }

    public void setImage(CircleImageView image) {
        this.image = image;
    }

    public TextView getName() {
        return name;
    }

    public void setName(TextView name) {
        this.name = name;
    }

    public TextView getEmail() {
        return email;
    }

    public void setEmail(TextView email) {
        this.email = email;
    }

    public FloatingActionButton getRemoveFriendButton() {
        return removeFriendButton;
    }

    public void setRemoveFriendButton(FloatingActionButton removeFriendButton) {
        this.removeFriendButton = removeFriendButton;
    }
}
