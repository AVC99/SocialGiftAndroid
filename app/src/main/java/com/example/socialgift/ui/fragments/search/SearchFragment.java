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
import android.widget.Button;
import android.widget.EditText;


import com.example.socialgift.R;
import com.example.socialgift.ui.fragments.search.gifts.SearchGiftsFragment;
import com.example.socialgift.ui.fragments.search.users.SearchUsersFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class SearchFragment extends Fragment {


    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private FloatingActionButton searchButton;
    private EditText searchEditText;
    private SearchUsersFragment searchUsersFragment;
    private SearchGiftsFragment searchGiftsFragment;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        setUpViews();

    }

    private void setUpViews() {
        FragmentManager fm = this.getChildFragmentManager();
        searchGiftsFragment = new SearchGiftsFragment();
        searchUsersFragment = new SearchUsersFragment();
        SearchViewPagerAdapter viewPagerAdapter = new SearchViewPagerAdapter(fm, getLifecycle(), searchUsersFragment, searchGiftsFragment);
        viewPager = requireActivity().findViewById(R.id.search_view_pager);
        tabLayout = requireActivity().findViewById(R.id.search_tab_layout);
        searchButton = requireActivity().findViewById(R.id.search_search_button);
        searchEditText = requireActivity().findViewById(R.id.search_search_edit_text);

        viewPager.setSaveEnabled(false);
        viewPager.setAdapter(viewPagerAdapter);
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText(position == 0 ? "Users" : "Gifts")
        ).attach();

        searchButton.setOnClickListener(v -> {
            if (viewPager.getCurrentItem() == 0) {
                searchUsersFragment.search(searchEditText.getText().toString());
            } else {
                searchGiftsFragment.search(searchEditText.getText().toString());
            }
        });
    }
}