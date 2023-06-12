package com.example.socialgift.ui.fragments.search;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.socialgift.ui.fragments.search.gifts.SearchGiftsFragment;
import com.example.socialgift.ui.fragments.search.users.SearchUsersFragment;


public class SearchViewPagerAdapter extends FragmentStateAdapter {

    private SearchUsersFragment searchUsersFragment;
    private SearchGiftsFragment searchGiftsFragment;

    public SearchViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, SearchUsersFragment searchUsersFragment, SearchGiftsFragment searchGiftsFragment) {
        super(fragmentManager, lifecycle);
        this.searchUsersFragment = searchUsersFragment;
        this.searchGiftsFragment = searchGiftsFragment;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if( position == 0){
            return searchUsersFragment;
        }else {
            return searchGiftsFragment;
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }

}
