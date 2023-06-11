package com.example.socialgift.ui.views.notifications;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.socialgift.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import de.hdodenhof.circleimageview.CircleImageView;

public class NotificationViewHolder extends RecyclerView.ViewHolder{
    private CircleImageView notificationImage;
    private TextView notificationName;
    private TextView notificationMail;
    private FloatingActionButton notificationAcceptButton;
    private FloatingActionButton notificationDeclineButton;


    public NotificationViewHolder(@NonNull View itemView) {
        super(itemView);
        notificationImage = itemView.findViewById(R.id.notification_card_profile_image);
        notificationName = itemView.findViewById(R.id.notification_card_username);
        notificationMail = itemView.findViewById(R.id.notification_card_email);
        notificationAcceptButton = itemView.findViewById(R.id.notification_card_accept_button);
        notificationDeclineButton = itemView.findViewById(R.id.notification_card_decline_button);
    }

    public CircleImageView getNotificationImage() {
        return notificationImage;
    }

    public TextView getNotificationName() {
        return notificationName;
    }

    public TextView getNotificationMail() {
        return notificationMail;
    }

    public FloatingActionButton getNotificationAcceptButton() {
        return notificationAcceptButton;
    }

    public FloatingActionButton getNotificationDeclineButton() {
        return notificationDeclineButton;
    }
}
