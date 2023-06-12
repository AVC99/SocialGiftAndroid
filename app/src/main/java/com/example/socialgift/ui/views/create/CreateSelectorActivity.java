package com.example.socialgift.ui.views.create;

import androidx.appcompat.app.AppCompatActivity;

import androidx.constraintlayout.utils.widget.ImageFilterView;
import androidx.fragment.app.FragmentManager;

import androidx.viewpager2.widget.ViewPager2;


import android.os.Bundle;

import com.example.socialgift.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class CreateSelectorActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private ImageFilterView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_selector);

        backButton = findViewById(R.id.create_back_arrow);

        FragmentManager fm = this.getSupportFragmentManager();
        CreateViewPagerAdapter viewPagerAdapter = new CreateViewPagerAdapter(fm, getLifecycle());
        viewPager = findViewById(R.id.create_view_pager);
        tabLayout = findViewById(R.id.create_tab_layout);
        viewPager.setSaveEnabled(false);
        viewPager.setAdapter(viewPagerAdapter);

        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText(position == 0 ? "Wishlist" : "Gift")
        ).attach();

        backButton.setOnClickListener(v -> {
            finish();
        });

    }

}