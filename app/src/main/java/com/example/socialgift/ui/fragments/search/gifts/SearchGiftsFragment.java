package com.example.socialgift.ui.fragments.search.gifts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;


import com.android.volley.VolleyError;
import com.example.socialgift.API.APIRequest;
import com.example.socialgift.API.VolleyCallbackProductArray;
import com.example.socialgift.R;
import com.example.socialgift.model.Product;

import java.util.ArrayList;

public class SearchGiftsFragment extends Fragment {
    private RecyclerView recyclerView;
    private ProductListAdapter adapter;

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_gifts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        this.recyclerView = view.findViewById(R.id.search_gifts_recycler_view);
        this.recyclerView.setLayoutManager(new androidx.recyclerview.widget.LinearLayoutManager(this.getContext()));
    }

    public void search(String toString) {
        APIRequest apiRequest = new APIRequest(this.getContext());
        apiRequest.searchProducts(toString, new VolleyCallbackProductArray() {
            @Override
            public void onSuccessResponse(ArrayList<Product> result) {
                adapter = new ProductListAdapter(result, getContext());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        });



    }
}