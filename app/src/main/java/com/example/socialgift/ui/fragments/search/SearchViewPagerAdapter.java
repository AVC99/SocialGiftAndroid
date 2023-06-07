package com.example.socialgift.ui.fragments.search;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;


public class SearchViewPagerAdapter extends FragmentStateAdapter {

    public SearchViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        if( position == 0){
            return new SearchUsersFragment();
        }else {
            return new SearchGiftsFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }

}
