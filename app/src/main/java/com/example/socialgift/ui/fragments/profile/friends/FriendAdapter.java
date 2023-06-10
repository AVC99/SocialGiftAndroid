package com.example.socialgift.ui.fragments.profile.friends;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
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

public class FriendAdapter extends RecyclerView.Adapter<FriendViewHolder> {

    private ArrayList<User> friends;
    private Context context;

    public FriendAdapter(ArrayList<User> friends, Context context) {
        this.friends = friends;
        this.context = context;
    }

    @NonNull
    @Override
    public FriendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.friend_card, parent, false);
        return new FriendViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendViewHolder holder, int position) {
         position = holder.getAdapterPosition();

        User friend = friends.get(position);
        holder.getName().setText(friend.getName());
        holder.getEmail().setText(friend.getEmail());
        //Set image
        RequestOptions requestOptions = new RequestOptions().placeholder(R.drawable.user_image).error(R.drawable.user_image);

        Glide.with(context)
                .asBitmap()
                .load( friend.getImage())
                .apply(requestOptions)
                .into(new BitmapImageViewTarget( holder.getImage()){
                    @Override
                    public void onResourceReady(@NonNull Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
                        super.onResourceReady(bitmap, transition);
                        // Set the loaded bitmap as the image for the CircularImageView
                        holder.getImage().setImageBitmap(bitmap);
                    }
                }.getView());


        int finalPosition = position;
        holder.getRemoveFriendButton().setOnClickListener(v -> {
            APIRequest apiRequest = new APIRequest(context);
            apiRequest.removeFriend(friend.getId(), new VolleyCallback() {
                @Override
                public void onSuccessResponseString(String result) {
                    friends.remove(finalPosition);
                    notifyItemRemoved(finalPosition);
                }

                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context, "Error removing friend", Toast.LENGTH_SHORT).show();
                }
            });
        });

    }

    @Override
    public int getItemCount() {
        return this.friends.size();
    }
}
