package com.example.socialgift.ui.views.notifications;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;

import com.android.volley.VolleyError;
import com.example.socialgift.API.APIRequest;
import com.example.socialgift.API.VolleyCallbackUserArray;
import com.example.socialgift.R;
import com.example.socialgift.model.User;

import java.util.ArrayList;

public class NotificationsActivity extends AppCompatActivity {
    private ArrayList<User> users;
    private RecyclerView notificationsRecyclerView;
    private NotificationsAdapter notificationsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        setUpView();
        getNotifications();
    }

    private void getNotifications() {
        APIRequest apiRequest = new APIRequest(this);
        Context context = this;
        apiRequest.getNotifications(new VolleyCallbackUserArray() {
            @Override
            public void onSuccessResponse(ArrayList<User> result) {
                users = result;
                notificationsAdapter = new NotificationsAdapter(users, context);
                notificationsRecyclerView.setAdapter(notificationsAdapter);
            }

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

    }

    private void setUpView() {
        ImageView backButton = findViewById(R.id.notifications_back_button);
        notificationsRecyclerView = findViewById(R.id.notifications_recycler_view);
        notificationsRecyclerView.setLayoutManager(new androidx.recyclerview.widget.LinearLayoutManager(this));

        backButton.setOnClickListener(v -> {
            finish();
        });

    }
}