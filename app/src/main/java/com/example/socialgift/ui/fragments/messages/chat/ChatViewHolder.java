package com.example.socialgift.ui.fragments.messages.chat;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialgift.R;

public class ChatViewHolder extends RecyclerView.ViewHolder{
    private final TextView userMessage;
    private final TextView friendMessage;

    private final CardView userCard;
    private final CardView friendCard;
    public ChatViewHolder(@NonNull View itemView) {
        super(itemView);
        this.userMessage = itemView.findViewById(R.id.user_message);
        this.friendMessage= itemView.findViewById(R.id.friend_message);
        this.userCard = itemView.findViewById(R.id.user_message_card_view);
        this.friendCard = itemView.findViewById(R.id.friend_message_card_view);
        userCard.setVisibility(View.GONE);
        friendCard.setVisibility(View.GONE);
    }

    public CardView getUserCard() {
        return userCard;
    }

    public CardView getFriendCard() {
        return friendCard;
    }

    public TextView getUserMessage() {
        return userMessage;
    }

    public TextView getFriendMessage() {
        return friendMessage;
    }
}
