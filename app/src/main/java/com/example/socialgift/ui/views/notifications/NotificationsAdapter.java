package com.example.socialgift.ui.views.notifications;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.example.socialgift.API.APIRequest;
import com.example.socialgift.API.VolleyCallback;
import com.example.socialgift.R;
import com.example.socialgift.model.User;

import java.util.ArrayList;

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationViewHolder> {

    private ArrayList<User> users;
    private Context context;

    public NotificationsAdapter(ArrayList<User> users, Context context) {
        this.users = users;
        this.context = context;
    }

    @Override
    public NotificationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.notification_card, null);
        return new NotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NotificationViewHolder holder, int position) {
        position = holder.getAdapterPosition();
        User user = users.get(position);
        holder.getNotificationName().setText(user.getName());
        holder.getNotificationMail().setText(user.getEmail());
        Glide.with(context)
                .load(user.getImage())
                .placeholder(R.drawable.image_not_found)
                .error(R.drawable.image_not_found)
                .into(holder.getNotificationImage());



        APIRequest apiRequest = new APIRequest(context);

        int finalPosition = position;

        holder.getNotificationAcceptButton().setOnClickListener(v -> {

            apiRequest.acceptFriendRequest(user.getId(), new VolleyCallback() {
                @Override
                public void onSuccessResponseString(String result) {
                    Toast.makeText(context, "Friend request accepted", Toast.LENGTH_SHORT).show();
                    users.remove(finalPosition);
                    notifyDataSetChanged();
                }

                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        });


        holder.getNotificationDeclineButton().setOnClickListener(v -> {
            apiRequest.removeFriend(user.getId(), new VolleyCallback() {
                @Override
                public void onSuccessResponseString(String result) {
                    users.remove(finalPosition);
                    notifyDataSetChanged();
                    Toast.makeText(context, "Friend request declined", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}
