package com.example.socialgift.ui.fragments.messages;


import android.content.Intent;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.android.volley.VolleyError;
import com.example.socialgift.API.APIRequest;
import com.example.socialgift.API.VolleyCallbackUserArray;
import com.example.socialgift.R;
import com.example.socialgift.model.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class MessagesFragment extends Fragment {
    private RecyclerView recyclerView;
    private FloatingActionButton searchButton;
    //TODO: IMPLEMENT SEARCH HERE

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_messages, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        this.recyclerView = view.findViewById(R.id.messages_recycler_view);
        this.recyclerView.setLayoutManager(new androidx.recyclerview.widget.LinearLayoutManager(requireContext()));
        this.searchButton = view.findViewById(R.id.messages_search_button);
        getUserFriends();
    }

    private void getUserFriends() {
        APIRequest apiRequest = new APIRequest(requireContext());
        apiRequest.getMessagedUsers(new VolleyCallbackUserArray() {
            @Override
            public void onSuccessResponse(ArrayList<User> result) {
                if (result != null) {
                    recyclerView.setAdapter(new MessagesAdapter(result, requireContext()));
                } else {
                    Toast.makeText(requireContext(), "No messages found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(requireContext(), "Error getting messages", Toast.LENGTH_SHORT).show();
            }
        });
    }
}