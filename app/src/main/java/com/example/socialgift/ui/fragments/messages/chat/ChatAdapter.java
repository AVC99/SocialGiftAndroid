package com.example.socialgift.ui.fragments.messages.chat;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.socialgift.R;
import com.example.socialgift.model.Message;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<ChatViewHolder> {
    private ArrayList<Message> messages;
    private Context context;
    private final int userId;

    public ChatAdapter(ArrayList<Message> messages, Context context) {
        this.messages = messages;
        this.context = context;
        this.userId = context.getSharedPreferences(context.getString(R.string.shared_preferences), Context.MODE_PRIVATE).getInt(context.getString(R.string.saved_user_id_key), -1);
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message, parent, false);
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        Message message = this.messages.get(position);
        if (message.getSenderId() == userId) {
            holder.getUserCard().setVisibility(View.VISIBLE);

            holder.getUserMessage().setText(message.getContent());
        } else {
            holder.getFriendCard().setVisibility(View.VISIBLE);

            holder.getFriendMessage().setText(message.getContent());
        }

    }

    @Override
    public int getItemCount() {
        if (this.messages == null) return 0;
        return this.messages.size();
    }
}
