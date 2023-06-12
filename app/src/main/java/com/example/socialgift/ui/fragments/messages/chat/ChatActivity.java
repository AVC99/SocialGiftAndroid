package com.example.socialgift.ui.fragments.messages.chat;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.example.socialgift.API.APIRequest;
import com.example.socialgift.API.VolleyCallback;
import com.example.socialgift.API.VolleyCallbackMessagesArray;
import com.example.socialgift.R;
import com.example.socialgift.model.Message;
import com.example.socialgift.model.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ChatAdapter chatAdapter;
    private EditText messageEditText;
    private User user;
    ArrayList<Message> messages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        setUpViews();
        loadMessages();

    }

    private void loadMessages() {
        APIRequest apiRequest = new APIRequest(this);

        apiRequest.getMessages(user.getId(), new VolleyCallbackMessagesArray() {
            @Override
            public void onSuccessResponse(ArrayList<Message> result) {
                chatAdapter = new ChatAdapter(result, ChatActivity.this);
                messages = result;
                recyclerView.setAdapter(chatAdapter);
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ChatActivity.this, "Error loading messages", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setUpViews() {
        this.user = (User) getIntent().getSerializableExtra("user");
        ImageView backButton = findViewById(R.id.chat_back_button_image_view);
        ImageView profileImage = findViewById(R.id.chat_profile_image);
        TextView username = findViewById(R.id.chat_username);
        FloatingActionButton sendButton = findViewById(R.id.chat_send_button);
        messageEditText = findViewById(R.id.chat_message_edit_text);
        recyclerView = findViewById(R.id.chat_recycler_view);


        recyclerView.setLayoutManager(new androidx.recyclerview.widget.LinearLayoutManager(this));
        username.setText(user.getName());

        Glide.with(this)
                .load(user.getImage())
                .placeholder(R.drawable.image_not_found)
                .error(R.drawable.image_not_found)
                .into(profileImage);

        backButton.setOnClickListener(v -> finish());

        sendButton.setOnClickListener(v -> sendMessage());
    }

    private void sendMessage() {
        APIRequest apiRequest = new APIRequest(this);
        if (messageEditText.getText().toString().isEmpty()) {
            Toast.makeText(this, "Message cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }
        apiRequest.sendMessage(user.getId(), messageEditText.getText().toString(), new VolleyCallback() {
            @Override
            public void onSuccessResponseString(String result) {
                loadMessages();
                messageEditText.setText("");
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ChatActivity.this, "Error sending message", Toast.LENGTH_SHORT).show();
            }
        });

    }
}