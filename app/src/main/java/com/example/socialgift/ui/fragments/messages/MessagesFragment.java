package com.example.socialgift.ui.fragments.messages;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.socialgift.R;
import com.example.socialgift.databinding.FragmentHomeBinding;
import com.example.socialgift.databinding.FragmentMessagesBinding;
import com.example.socialgift.ui.fragments.home.HomeViewModel;

public class MessagesFragment extends Fragment {

    public View onCreateView( LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_messages, container, false);
    }

}