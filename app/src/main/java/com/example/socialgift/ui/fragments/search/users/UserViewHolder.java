package com.example.socialgift.ui.fragments.search.users;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.socialgift.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserViewHolder extends RecyclerView.ViewHolder{
    private CircleImageView profileImage;
    private TextView userName;
    private TextView userMail;
    private FloatingActionButton addFriendButton;

    public UserViewHolder(View itemView) {
        super(itemView);
        profileImage = itemView.findViewById(R.id.user_card_profile_image);
        userName = itemView.findViewById(R.id.user_card_username);
        userMail = itemView.findViewById(R.id.user_card_email);
        addFriendButton = itemView.findViewById(R.id.user_card_fab);
    }

    public CircleImageView getProfileImage() {
        return profileImage;
    }

    public TextView getUserName() {
        return userName;
    }

    public TextView getUserMail() {
        return userMail;
    }

    public FloatingActionButton getAddFriendButton() {
        return addFriendButton;
    }
}
