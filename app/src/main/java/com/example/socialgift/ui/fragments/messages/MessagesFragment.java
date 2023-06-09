package com.example.socialgift.ui.fragments.messages;


import android.content.Intent;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.socialgift.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MessagesFragment extends Fragment {
    private FloatingActionButton newChatButton;

    public View onCreateView( LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_messages, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        newChatButton = getActivity().findViewById(R.id.messages_new_chat_button);
        newChatButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ChatActivity.class);
            startActivity(intent);
        });
    }
}