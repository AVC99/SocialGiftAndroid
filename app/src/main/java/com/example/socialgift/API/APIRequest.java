package com.example.socialgift.API;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.socialgift.MainActivity;
import com.example.socialgift.ui.views.login.LoginActivity;


import org.json.JSONException;
import org.json.JSONObject;


public class APIRequest {

    public static void loginRequest(String email, String password, Context context,  VolleyCallback callback) {
        try {
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("email", email);
            jsonBody.put("password", password);

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, Endpoints.LOGIN, jsonBody,
                    response -> {
                        // Handle successful response
                        try {
                            String token = response.getString("accessToken");
                            Log.d("LOGIN-TOKEN", token);
                            callback.onSuccessResponseString(token);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                        // Do something with the token
                    },
                    error -> {
                        // Handle error response
                        Log.e("LOGIN-ERROR", error.toString());
                        callback.onErrorResponse(error);

                    });

            RequestQueue queue = Volley.newRequestQueue(context);
            queue.add(request);

        } catch (JSONException e) {
            Log.e("LOGIN-ERROR", e.toString());
        }
    }

    public static void registerRequest(String name, String lastName,String email, String password, String image, Context context, VolleyCallback callback ) {
        try {
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("name", name);
            jsonBody.put("last_name", lastName);
            jsonBody.put("email", email);
            jsonBody.put("password", password);
            jsonBody.put("image", image);

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, Endpoints.REGISTER, jsonBody,
                    response -> {
                        // Handle successful response
                        try {
                            if(response.get("success").equals("false")){
                                //handle error
                                Log.d("REGISTER-RESPONSE-ERROR", response.toString());
                                callback.onErrorResponse(new VolleyError(response.get("message").toString()));
                            }else {
                                //handle success
                                Log.d("REGISTER-SUCCESS", response.toString());
                                callback.onSuccessResponseString(response.toString());
                            }

                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    },
                    error -> {
                        // Handle error response
                        Log.e("REGISTER-ERROR", error.toString());
                        callback.onErrorResponse(error);
                    });

            RequestQueue queue = Volley.newRequestQueue(context);
            queue.add(request);

        } catch (JSONException e) {
            Log.e("REGISTER-ERROR", e.toString());
        }
    }
}
