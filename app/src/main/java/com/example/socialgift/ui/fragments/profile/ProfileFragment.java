package com.example.socialgift.ui.fragments.profile;



import android.content.Intent;
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

import com.example.socialgift.ui.views.EditProfileActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class ProfileFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private FloatingActionButton editProfileButton;


    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //Get all the views
        FragmentManager fm = this.getChildFragmentManager();
        ProfileViewPagerAdapter viewPagerAdapter = new ProfileViewPagerAdapter(fm, getLifecycle());
        viewPager = requireActivity().findViewById(R.id.profile_view_pager);
        tabLayout = requireActivity().findViewById(R.id.profile_tab_layout);
        editProfileButton = requireActivity().findViewById(R.id.profile_edit_fab);

        //Set up the edit profile button
        editProfileButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), EditProfileActivity.class);
            startActivity(intent);
        });

        //Set up the tab bar and the tabs in the tab bar
        viewPager.setSaveEnabled(false);
        viewPager.setAdapter(viewPagerAdapter);

        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText(position == 0 ? "Wishlists" : "Friends")
        ).attach();

    }


}

