package com.example.socialgift.ui.fragments.profile.wishlists;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.VolleyError;
import com.example.socialgift.API.APIRequest;
import com.example.socialgift.API.VolleyCallbackWishlistArray;
import com.example.socialgift.R;
import com.example.socialgift.model.Wishlist;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class ProfileWishlistFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProfileWishlistAdapter adapter;
    private ArrayList<Wishlist> wishlists = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile_wishlist, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.profile_wishlist_recycler_view);
        getWishlists();

    }

    private void getWishlists() {
        APIRequest apiRequest = new APIRequest(getContext());
        apiRequest.getUserWishlists(getContext().getSharedPreferences(getContext().getString(R.string.shared_preferences), Context.MODE_PRIVATE).getInt(getContext().getString(R.string.saved_user_id_key), -1),
                new VolleyCallbackWishlistArray() {
                    @Override
                    public void onSuccessResponse(ArrayList<Wishlist> result) {
                        wishlists = result;
                        adapter = new ProfileWishlistAdapter(wishlists, getContext());
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                        recyclerView.setLayoutManager(linearLayoutManager);
                        recyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
    }
}