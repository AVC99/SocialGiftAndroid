package com.example.socialgift.ui.fragments.home;

import android.content.Context;
import android.content.Intent;
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
import com.example.socialgift.model.Post;
import com.example.socialgift.ui.views.ProductActivity;
import com.example.socialgift.ui.views.wishlistselection.WishlistSelectionActivity;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostViewHolder> {

    private ArrayList<Post> posts;
    private Context context;

    public PostAdapter(ArrayList<Post> posts, Context context) {
        this.posts = posts;
        this.context = context;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_card, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        position = holder.getAdapterPosition();
        Post post = posts.get(position);
        holder.getUsername().setText(post.getUsername());
        holder.getGiftName().setText(post.getGiftName());
        holder.getGiftPrice().setText(post.getGiftPrice().toString()+"€");

        //Set image
        RequestOptions requestOptions = new RequestOptions().placeholder(R.drawable.user_image).error(R.drawable.user_image);

        Glide.with(context)
                .asBitmap()
                .load(post.getUserImage())
                .apply(requestOptions)
                .into(new BitmapImageViewTarget(holder.getUserImage()) {
                    @Override
                    public void onResourceReady(@NonNull Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
                        super.onResourceReady(bitmap, transition);
                        // Set the loaded bitmap as the image for the CircularImageView
                        holder.getUserImage().setImageBitmap(bitmap);
                    }
                }.getView());

        //Set Post Image
        Glide.with(context)
                .asBitmap()
                .load(post.getPostImage())
                .apply(requestOptions)
                .into(new BitmapImageViewTarget(holder.getPostImage()) {
                    @Override
                    public void onResourceReady(@NonNull Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
                        super.onResourceReady(bitmap, transition);
                        // Set the loaded bitmap as the image for the CircularImageView
                        holder.getPostImage().setImageBitmap(bitmap);
                    }
                }.getView());


        holder.getPostImage().setOnClickListener(v -> {
            Intent intent = new Intent(context, ProductActivity.class);
            intent.putExtra("product", post);
            context.startActivity(intent);
        });

        APIRequest apiRequest = new APIRequest(context);

        holder.getGiftButton().setOnClickListener(v -> {
            apiRequest.giftProduct(post.getId(), new VolleyCallback() {
                @Override
                public void onSuccessResponseString(String result) {
                    holder.getGiftButton().setText("Gifted");
                }

                @Override
                public void onErrorResponse(VolleyError error) {
                    if (error.networkResponse.statusCode == 410) {
                        Toast.makeText(context, "Gift not found", Toast.LENGTH_SHORT).show();
                    } else if (error.networkResponse.statusCode == 500) {
                        Toast.makeText(context, "Already Gifted ", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        });
        holder.getAddToWishlistButton().setOnClickListener(v -> {
            Intent intent = new Intent(context, WishlistSelectionActivity.class);
            intent.putExtra("post", post);
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return this.posts.size();
    }
}
