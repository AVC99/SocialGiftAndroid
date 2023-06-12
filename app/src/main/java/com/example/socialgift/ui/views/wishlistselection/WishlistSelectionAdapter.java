package com.example.socialgift.ui.views.wishlistselection;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.example.socialgift.API.APIRequest;
import com.example.socialgift.API.VolleyCallback;
import com.example.socialgift.R;
import com.example.socialgift.model.Post;
import com.example.socialgift.model.Wishlist;

import java.util.ArrayList;

public class WishlistSelectionAdapter extends RecyclerView.Adapter<WishlistSelectionViewHolder> {

    private ArrayList<Wishlist> wishlists;
    private Context context;
    private Post post;

    public WishlistSelectionAdapter(ArrayList<Wishlist> wishlists, Context context, Post post) {
        this.wishlists = wishlists;
        this.context = context;
        this.post = post;
    }

    @NonNull
    @Override
    public WishlistSelectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wishlist_selection_card, parent, false);
        return new WishlistSelectionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WishlistSelectionViewHolder holder, int position) {
        position = holder.getAdapterPosition();
        Wishlist wishlist = wishlists.get(position);
        holder.getWishlistName().setText(wishlist.getName());

        holder.getAddButton().setOnClickListener(v -> {
            APIRequest apiRequest = new APIRequest(context);
            apiRequest.saveProductToWishlist(wishlist.getId(), post.getURL(), new VolleyCallback() {
                @Override
                public void onSuccessResponseString(String result) {
                    Toast.makeText(context, "Product added to wishlist", Toast.LENGTH_SHORT).show();
                    ((WishlistSelectionActivity) context).finish();
                }

                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context, "Error adding product to wishlist", Toast.LENGTH_SHORT).show();
                }
            });

        });


    }

    @Override
    public int getItemCount() {
        return this.wishlists.size();
    }
}
