package com.example.socialgift;

import android.content.Intent;
import android.os.Bundle;

import com.example.socialgift.ui.fragments.home.HomeFragment;
import com.example.socialgift.ui.fragments.messages.MessagesFragment;
import com.example.socialgift.ui.fragments.profile.ProfileFragment;
import com.example.socialgift.ui.fragments.search.SearchFragment;
import com.example.socialgift.ui.views.create.CreateSelectorActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {


    private String token;

    private BottomNavigationView bottomNavigationView;
    private HomeFragment homeFragment = new HomeFragment();
    private MessagesFragment messagesFragment = new MessagesFragment();
    private SearchFragment searchFragment = new SearchFragment();
    private ProfileFragment profileFragment = new ProfileFragment();

    private FloatingActionButton addFloatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.nav_view);
        addFloatingActionButton = findViewById(R.id.main_activity_fab);


        addFloatingActionButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CreateSelectorActivity.class);
            startActivity(intent);
        });

        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_activity_main, homeFragment).commit();
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_activity_main, homeFragment).commit();
                    return true;
                case R.id.navigation_messages:
                    getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_activity_main, messagesFragment).commit();
                    return true;
                case R.id.navigation_search:
                    getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_activity_main, searchFragment).commit();
                    return true;
                case R.id.navigation_profile:
                    getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_activity_main, profileFragment).commit();
                    return true;
            }
            return false;
        });


    }

}