package com.example.socialgift.ui.fragments.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.example.socialgift.API.APIRequest;
import com.example.socialgift.API.VolleyCallbackProduct;
import com.example.socialgift.API.VolleyCallbackUserArray;
import com.example.socialgift.API.VolleyCallbackWishlistArray;
import com.example.socialgift.R;
import com.example.socialgift.model.Gift;
import com.example.socialgift.model.Post;
import com.example.socialgift.model.Product;
import com.example.socialgift.model.User;
import com.example.socialgift.model.Wishlist;
import com.example.socialgift.ui.views.NotificationsActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private FloatingActionButton notificationsButton;
    private RecyclerView recyclerView;
    private ArrayList<Post> posts = new ArrayList<>();
    private PostAdapter postAdapter;

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        setUpViews();
        getPosts();
    }

    private void getPosts() {
        APIRequest apiRequest = new APIRequest(getContext());
        apiRequest.getFriends(new VolleyCallbackUserArray() {
            @Override
            public void onSuccessResponse(ArrayList<User> result) {
                for (User u : result) {
                    apiRequest.getUserWishlists(u.getId(), new VolleyCallbackWishlistArray() {
                        @Override
                        public void onSuccessResponse(ArrayList<Wishlist> result) {
                            if (result == null) {
                                return;
                            }
                            for (Wishlist w : result) {
                                for (Gift g : w.getGifts()) {
                                    apiRequest.getGiftInformation(g.getProductURL(), new VolleyCallbackProduct() {
                                        @Override
                                        public void onSuccessResponse(Product result) {

                                            posts.add(new Post(u.getName(), u.getImage(),
                                                    result.getName(), result.getPrice(), result.getImageURL(),
                                                    result.getDescription(), result.getId(), g.getProductURL()));
                                            postAdapter.notifyItemInserted(posts.size() - 1);
                                        }

                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            }

                        }

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setUpViews() {
        notificationsButton = getActivity().findViewById(R.id.home_notifications_button);
        recyclerView = getActivity().findViewById(R.id.home_recycler_view);

        postAdapter = new PostAdapter(posts, getContext());
        recyclerView.setAdapter(postAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        linearLayoutManager.canScrollVertically();
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(postAdapter);

        notificationsButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), NotificationsActivity.class);
            startActivity(intent);
        });
    }
}
