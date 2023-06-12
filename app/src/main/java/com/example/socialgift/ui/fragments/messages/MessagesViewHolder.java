package com.example.socialgift.ui.fragments.messages;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialgift.R;

import org.w3c.dom.Text;

import de.hdodenhof.circleimageview.CircleImageView;

public class MessagesViewHolder extends RecyclerView.ViewHolder{
    private CircleImageView profileImage;
    private TextView username;

    public MessagesViewHolder(@NonNull View itemView) {
        super(itemView);
        this.profileImage = itemView.findViewById(R.id.messages_profile_image);
        this.username= itemView.findViewById(R.id.messages_username);
    }

    public CircleImageView getProfileImage() {
        return profileImage;
    }

    public TextView getUsername() {
        return username;
    }
}
