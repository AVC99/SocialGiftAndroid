package com.example.socialgift.ui.fragments.profile.wishlists.details;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.example.socialgift.API.APIRequest;
import com.example.socialgift.API.Endpoints;
import com.example.socialgift.API.VolleyCallback;
import com.example.socialgift.R;
import com.example.socialgift.model.Gift;
import com.example.socialgift.model.Product;
import com.example.socialgift.model.Wishlist;

import java.util.ArrayList;
import java.util.Objects;

public class WishlistProductAdapter extends RecyclerView.Adapter<WishlistProductViewHolder> {
    private ArrayList<Product> products;
    private Context context;
    private Wishlist wishlist;

    public WishlistProductAdapter(ArrayList<Product> products, Context context, Wishlist wishlist) {
        this.products = products;
        this.context = context;
        this.wishlist = wishlist;
    }

    @NonNull
    @Override
    public WishlistProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_card, parent, false);
        return new WishlistProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WishlistProductViewHolder holder, int position) {
        position = holder.getAdapterPosition();
        Product product = products.get(position);
        holder.getProductName().setText(product.getId() + " " + product.getName());
        holder.getProductPrice().setText(product.getPrice() + "€");
        Glide.with(context).load(product.getImageURL()).into(holder.getProductImage());

        int finalPosition = position;
        holder.getDeleteProductButton().setOnClickListener(v -> {

            products.remove(finalPosition);
            notifyItemRemoved(finalPosition);
            notifyItemRangeChanged(finalPosition, products.size());
            APIRequest apiRequest = new APIRequest(context);
            int id = 0;
            for (Gift g : wishlist.getGifts()) {
                if (Objects.equals(g.getProductURL(), Endpoints.PRODUCTS + product.getId())) {
                    id = g.getId();
                    break;
                }
            }
            apiRequest.deleteProductFromWishlist(id, new VolleyCallback() {
                @Override
                public void onSuccessResponseString(String result) {
                    Toast.makeText(context, "Product deleted", Toast.LENGTH_SHORT).show();
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
        return this.products.size();
    }
}

