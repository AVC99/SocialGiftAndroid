package com.example.socialgift.ui.fragments.profile.friends;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.socialgift.API.APIRequest;
import com.example.socialgift.API.VolleyCallbackUserArray;
import com.example.socialgift.R;
import com.example.socialgift.model.User;

import java.util.ArrayList;

public class ProfileFriendsFragment extends Fragment {

    private ArrayList<User> friends = new ArrayList<>();
    private RecyclerView friendsRecyclerView;
    private FriendAdapter friendAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_friends, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        friendsRecyclerView = view.findViewById(R.id.profile_friends_recycler_view);


        getFriends();



    }

    private void getFriends() {
        APIRequest apiRequest = new APIRequest(getContext());
        apiRequest.getFriends(new VolleyCallbackUserArray() {
            @Override
            public void onSuccessResponse(ArrayList<User> result) {
                friends = result;
                Log.d("friends", friends.toString());
                friendAdapter = new FriendAdapter(friends, getContext());
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                friendsRecyclerView.setLayoutManager(linearLayoutManager);
                friendsRecyclerView.setAdapter(friendAdapter);
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
                Log.d("friends", error.toString());
            }
        });
    }
}