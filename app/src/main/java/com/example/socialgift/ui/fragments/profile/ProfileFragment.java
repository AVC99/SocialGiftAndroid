package com.example.socialgift.ui.fragments.profile;



import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.socialgift.API.APIRequest;
import com.example.socialgift.API.VolleyCallbackUser;
import com.example.socialgift.R;

import com.example.socialgift.model.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import de.hdodenhof.circleimageview.CircleImageView;


public class ProfileFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private FloatingActionButton editProfileButton;
    private FloatingActionButton optionsProfileButton;
    private CircleImageView profileImage;
    private TextView userName;
    private TextView userMail;
    private User user;


    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        setUpViews();
        fetchUserFromAPI();

    }

    private void setUpViews() {
        FragmentManager fm = this.getChildFragmentManager();
        ProfileViewPagerAdapter viewPagerAdapter = new ProfileViewPagerAdapter(fm, getLifecycle());
        viewPager = requireActivity().findViewById(R.id.profile_view_pager);
        tabLayout = requireActivity().findViewById(R.id.profile_tab_layout);
        editProfileButton = requireActivity().findViewById(R.id.profile_edit_fab);
        optionsProfileButton = requireActivity().findViewById(R.id.profile_settings_fab);
        profileImage = requireActivity().findViewById(R.id.profile_profile_image);
        userName = requireActivity().findViewById(R.id.profile_username);
        userMail = requireActivity().findViewById(R.id.profile_mail);

        //Set up the edit profile button
        editProfileButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), EditProfileActivity.class);
            startActivity(intent);
        });
        //Set up the options button
        optionsProfileButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), OptionsActivity.class);
            startActivity(intent);
        });

        //Set up the tab bar and the tabs in the tab bar
        viewPager.setSaveEnabled(false);
        viewPager.setAdapter(viewPagerAdapter);

        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText(position == 0 ? "Wishlists" : "Friends")
        ).attach();

    }

    private void fetchUserFromAPI() {
        APIRequest apiRequest = new APIRequest(getContext());

        apiRequest.getUser(new VolleyCallbackUser() {
            @Override
            public void onSuccessResponse(User result) {
                user = result;
                //Set up the user in the view pager adapter
                userName.setText(user.getName());
                userMail.setText(user.getEmail());
                Log.d("LOAD-USER-IMAGE-URL",user.getImage());
                loadProfileImage(user.getImage());

            }

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Error fetching user", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void loadProfileImage(String imageURL) {
        RequestOptions requestOptions = new RequestOptions().placeholder(R.drawable.user_image).error(R.drawable.user_image);

        Glide.with(this)
                .asBitmap()
                .load(imageURL)
                .apply(requestOptions)
                .into(new BitmapImageViewTarget(profileImage){
                    @Override
                    public void onResourceReady(@NonNull Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
                        super.onResourceReady(bitmap, transition);
                        // Set the loaded bitmap as the image for the CircularImageView
                       profileImage.setImageBitmap(bitmap);
                    }
                }.getView());
    }


}

