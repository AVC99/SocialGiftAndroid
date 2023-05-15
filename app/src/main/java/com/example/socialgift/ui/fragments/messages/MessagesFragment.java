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

    private FragmentMessagesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MessagesViewModel homeViewModel =
                new ViewModelProvider(this).get(MessagesViewModel.class);

        binding = FragmentMessagesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        final TextView textView = new TextView(getContext());
        textView.setText("Messages Fragment");
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}