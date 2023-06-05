package com.example.socialgift.ui.fragments.search;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.socialgift.R;
import com.example.socialgift.databinding.FragmentSearchBinding;
import com.google.android.material.tabs.TabLayout;

public class SearchFragment extends Fragment {


    private TabLayout tabLayout;
    private ViewPager2 viewPager;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        tabLayout = getActivity().findViewById(R.id.search_tab_layout);
        viewPager = getActivity().findViewById(R.id.search_view_pager);

        FragmentManager fm = getChildFragmentManager();


        return inflater.inflate(R.layout.fragment_search, container, false);
    }

}