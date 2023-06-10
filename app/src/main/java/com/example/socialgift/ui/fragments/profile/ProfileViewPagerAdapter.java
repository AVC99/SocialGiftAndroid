package com.example.socialgift.ui.fragments.profile;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.socialgift.ui.fragments.profile.friends.ProfileFriendsFragment;

public class ProfileViewPagerAdapter extends FragmentStateAdapter {

    public ProfileViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if( position == 0) {
            return new ProfileWishlistFragment();
        }else {
            return new ProfileFriendsFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
