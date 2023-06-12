package com.example.socialgift.ui.fragments.messages;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.socialgift.R;
import com.example.socialgift.model.User;
import com.example.socialgift.ui.fragments.messages.chat.ChatActivity;

import java.util.ArrayList;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesViewHolder> {
    private ArrayList<User> users;
    private Context context;

    public MessagesAdapter(ArrayList<User> users, Context context) {
        this.users = users;
        this.context = context;
    }

    @NonNull
    @Override
    public MessagesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.messages_user_card, parent, false);
        return new MessagesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessagesViewHolder holder, int position) {
        User user = this.users.get(position);
        holder.getUsername().setText(user.getName());
        Glide.with(context)
                .load(user.getImage())
                .placeholder(R.drawable.image_not_found)
                .error(R.drawable.image_not_found)
                .into(holder.getProfileImage());

        holder.getUsername().setOnClickListener(v -> goToChat(user));
        holder.getProfileImage().setOnClickListener(v -> goToChat(user));

    }

    private void goToChat(User user) {
        Intent intent = new Intent(context, ChatActivity.class);
        intent.putExtra("user", user);
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        if (this.users == null) return 0;
        return this.users.size();
    }
}
