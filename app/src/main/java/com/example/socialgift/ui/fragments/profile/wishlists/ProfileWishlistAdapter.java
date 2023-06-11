package com.example.socialgift.ui.fragments.profile.wishlists;

import android.content.Context;
import android.content.Intent;
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
import com.example.socialgift.model.Wishlist;
import com.example.socialgift.ui.fragments.profile.wishlists.details.WishlistDetailsActivity;

import java.util.ArrayList;

public class ProfileWishlistAdapter extends RecyclerView.Adapter<ProfileWishlistViewHolder> {

    private ArrayList<Wishlist> wishlists;
    private Context context;

    public ProfileWishlistAdapter(ArrayList<Wishlist> wishlists, Context context) {
        this.wishlists = wishlists;
        this.context = context;
    }

    @NonNull
    @Override
    public ProfileWishlistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wishlist_card, parent, false);
        return new ProfileWishlistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileWishlistViewHolder holder, int position) {
        position = holder.getAdapterPosition();
        Wishlist wishlist = wishlists.get(position);
        holder.getWishlistName().setText(wishlist.getName());
        holder.getWishlistDescription().setText(wishlist.getDescription());
        holder.getWishlistProductsCount().setText("Number of gifts: " + wishlist.getGifts().size());
        holder.getWishlistEndDate().setText("End date: " + wishlist.getEndDate());

        int finalPosition = position;
        holder.getWishlistDeleteButton().setOnClickListener(v -> {
            wishlists.remove(finalPosition);
            notifyItemRemoved(finalPosition);
            notifyItemRangeChanged(finalPosition, wishlists.size());
            APIRequest apiRequest = new APIRequest(context);
            apiRequest.deleteWishlist(wishlist.getId(), new VolleyCallback() {
                @Override
                public void onSuccessResponseString(String result) {
                    Toast.makeText(context, "Wishlist deleted", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context, "Error deleting wishlist", Toast.LENGTH_SHORT).show();
                }
            });

        });

        holder.getWishlistName().setOnClickListener(v -> goToWishlistDetails(wishlist));
        holder.getWishlistEndDate().setOnClickListener(v -> goToWishlistDetails(wishlist));
        holder.getWishlistProductsCount().setOnClickListener(v -> goToWishlistDetails(wishlist));

    }

    private void goToWishlistDetails(Wishlist wishlist) {
        Intent intent = new Intent(context, WishlistDetailsActivity.class);
        intent.putExtra("wishlist", wishlist);
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return this.wishlists.size();
    }
}
