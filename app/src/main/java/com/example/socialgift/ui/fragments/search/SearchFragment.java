package com.example.socialgift.ui.fragments.search;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.socialgift.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class SearchFragment extends Fragment {


    private TabLayout tabLayout;
    private ViewPager2 viewPager;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        FragmentManager fm = this.getChildFragmentManager();
        SearchViewPagerAdapter viewPagerAdapter = new SearchViewPagerAdapter(fm, getLifecycle());
        viewPager = requireActivity().findViewById(R.id.search_view_pager);
        tabLayout = requireActivity().findViewById(R.id.search_tab_layout);
        viewPager.setSaveEnabled(false);
         viewPager.setAdapter(viewPagerAdapter);

        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText(position == 0 ? "Users" : "Gifts")
        ).attach();
    }
}