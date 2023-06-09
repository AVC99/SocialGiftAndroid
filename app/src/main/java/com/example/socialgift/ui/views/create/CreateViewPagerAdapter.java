package com.example.socialgift.ui.views.create;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;


public class CreateViewPagerAdapter extends FragmentStateAdapter {
    public CreateViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if( position == 0) {
            return new CreateWishlistFragment();
        }else {
            return new CreateGiftFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
