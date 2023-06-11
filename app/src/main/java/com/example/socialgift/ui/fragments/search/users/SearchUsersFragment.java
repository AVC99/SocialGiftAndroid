package com.example.socialgift.ui.fragments.search.users;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.example.socialgift.API.APIRequest;
import com.example.socialgift.API.VolleyCallbackUserArray;
import com.example.socialgift.R;
import com.example.socialgift.model.User;

import java.util.ArrayList;

public class SearchUsersFragment extends Fragment {

    private RecyclerView recyclerView;
    private UserListAdapter userListAdapter;
    private ArrayList<User> users = new ArrayList<>();
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_users, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.search_users_recycler_view);
    }

    public void search(String toString) {
        Toast.makeText(getContext(), "searching for " + toString, Toast.LENGTH_SHORT).show();
        APIRequest apiRequest = new APIRequest(getContext());
        apiRequest.searchUsers(toString, new VolleyCallbackUserArray() {
            @Override
            public void onSuccessResponse(ArrayList<User> result) {
                users = result;
                userListAdapter = new UserListAdapter(users, getContext());
                recyclerView.setAdapter(userListAdapter);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(linearLayoutManager);
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Error searching users", Toast.LENGTH_SHORT).show();
            }
        });

    }
}