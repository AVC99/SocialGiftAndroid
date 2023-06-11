package com.example.socialgift.ui.fragments.search.gifts;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.socialgift.API.APIRequest;
import com.example.socialgift.R;
import com.example.socialgift.model.Product;
import com.example.socialgift.ui.views.ProductActivity;
import com.example.socialgift.ui.views.wishlistselection.WishlistSelectionActivity;

import java.util.ArrayList;

public class ProductListAdapter extends RecyclerView.Adapter<ProductViewHolder> {

    private ArrayList<Product> products;
    private Context context;

    public ProductListAdapter(ArrayList<Product> products, Context context) {
        this.products = products;
        this.context = context;
    }


    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_card, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        position = holder.getAdapterPosition();
        Product product = products.get(position);
        holder.getProductName().setText(product.getName());
        holder.getProductPrice().setText(product.getPrice() + "€");

        Glide.with(context)
                .load(product.getImageURL())
                .placeholder(R.drawable.image_not_found)
                .error(R.drawable.image_not_found)
                .into(holder.getProductImage());

        holder.getProductName().setOnClickListener(v -> goToProductPage(product));
        holder.getProductPrice().setOnClickListener(v -> goToProductPage(product));
        holder.getProductImage().setOnClickListener(v -> goToProductPage(product));

        holder.getAddProductButton().setOnClickListener(v -> {
            Intent intent = new Intent(context, WishlistSelectionActivity.class);
            intent.putExtra("product", product);
            context.startActivity(intent);
        });

    }

    private void goToProductPage(Product product) {
        Intent intent = new Intent(context, ProductActivity.class);
        intent.putExtra("searchProduct", product);
        context.startActivity(intent);

    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
