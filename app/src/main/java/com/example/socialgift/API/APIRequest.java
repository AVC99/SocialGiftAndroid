package com.example.socialgift.API;

import android.content.Intent;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.socialgift.MainActivity;
import com.example.socialgift.ui.views.login.LoginActivity;


import org.json.JSONException;
import org.json.JSONObject;


public class APIRequest {

    public static JsonObjectRequest loginRequest(String email, String password) {
        try {
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("email", email);
            jsonBody.put("password", password);

            return new JsonObjectRequest(Request.Method.POST, Endpoints.LOGIN, jsonBody,
                    response -> {
                        // Handle successful response
                        try {
                            String token = response.getString("accessToken");
                            Log.d("LOGIN-TOKEN", token);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                        // Do something with the token
                    },
                    error -> {
                        // Handle error response
                        Log.e("LOGIN-ERROR", error.toString());

                    });

        } catch (JSONException e) {
            Log.e("LOGIN-ERROR", e.toString());
        }

        return null;

    }


}
