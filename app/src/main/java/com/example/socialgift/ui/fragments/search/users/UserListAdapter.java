package com.example.socialgift.ui.fragments.search.users;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.socialgift.API.APIRequest;
import com.example.socialgift.API.VolleyCallback;
import com.example.socialgift.R;
import com.example.socialgift.model.User;

import java.util.ArrayList;

public class UserListAdapter extends RecyclerView.Adapter<UserViewHolder>{
    private ArrayList<User> users;
    private Context context;

    public UserListAdapter(ArrayList<User> users, Context context) {
        this.users = users;
        this.context = context;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.user_card, null);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = this.users.get(position);
        holder.getUserName().setText(user.getName());
        holder.getUserMail().setText(user.getEmail());

        //Put a default image if the user doesn't have one
        if (user.getImage().equals("")) {
            Glide.with(context).load(R.drawable.user_image).into(holder.getProfileImage());
        } else if (user.getImage().equals("null")) Glide.with(context).load(R.drawable.user_image).into(holder.getProfileImage());


        Glide.with(context).load(user.getImage()).into(holder.getProfileImage());
        RequestOptions requestOptions = new RequestOptions().placeholder(R.drawable.user_image).error(R.drawable.user_image);
        Glide.with(context)
                .load(user.getImage())
                .apply(requestOptions)
                .into(new BitmapImageViewTarget(holder.getProfileImage()){
                    @Override
                    public void onResourceReady(@NonNull Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
                        super.onResourceReady(bitmap, transition);
                        // Set the loaded bitmap as the image for the CircularImageView
                        holder.getProfileImage().setImageBitmap(bitmap);
                    }
                }.getView());

        holder.getAddFriendButton().setOnClickListener(v -> {
            APIRequest apiRequest = new APIRequest(context);
            apiRequest.addFriend(user.getId(), new VolleyCallback() {
                @Override
                public void onSuccessResponseString(String result) {
                    Toast.makeText(context, "Friend added", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context, "Error adding friend", Toast.LENGTH_SHORT).show();
                }
            });
        });

    }

    @Override
    public int getItemCount() {
        return this.users.size();
    }
}
