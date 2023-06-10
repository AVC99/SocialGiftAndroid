package com.example.socialgift.API;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.socialgift.R;
import com.example.socialgift.model.User;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;


public class APIRequest {
    private String token;
    private int userId;
    private Context context;

    public APIRequest(Context context) {
        this.token = context.getSharedPreferences(context.getString(R.string.shared_preferences), Context.MODE_PRIVATE).getString(context.getString(R.string.saved_access_token_key), null);
        this.userId = context.getSharedPreferences(context.getString(R.string.shared_preferences), Context.MODE_PRIVATE).getInt(context.getString(R.string.saved_user_id_key), -1);
        this.context = context;
    }

    public static void loginRequest(String email, String password,Context context,  VolleyCallback callback) {
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

    public static void registerRequest(String name, String lastName, String email, String password, String image, Context context, VolleyCallback callback) {
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
                        Log.d("REGISTER-SUCCESS", response.toString());
                        callback.onSuccessResponseString(response.toString());
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

    public void deleteAccount(Context context, VolleyCallback callback) {
        try {
            JSONObject body = new JSONObject();
            body.put("Authorization", "Bearer " + this.token);


            JsonObjectRequest request = new JsonObjectRequest(Request.Method.DELETE, Endpoints.DELETE_ACCOUNT, body,
                    response -> {
                        // Handle successful response
                        Log.d("DELETE-ACCOUNT-SUCCESS", response.toString());
                        callback.onSuccessResponseString(response.toString());
                    },
                    error -> {
                        // Handle error response
                        Log.e("DELETE-ACCOUNT-ERROR", error.toString());
                        callback.onErrorResponse(error);
                    });
            RequestQueue queue = Volley.newRequestQueue(context);
            queue.add(request);
        } catch (Exception e) {
            Log.e("DELETE-ACCOUNT-ERROR", e.toString());
        }
    }

    public void getUserId(String email,String token, VolleyCallback volleyCallback) {
        RequestQueue queue = Volley.newRequestQueue(context);
        try {
            JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, Endpoints.SEARCH_USER + email, null,
                    response -> {
                        // Handle successful response
                        try {
                            if (response.length()>0){
                                int userId = response.getJSONObject(0).getInt("id");
                                Log.d("GET-USER-ID-SUCCESS", String.valueOf(userId));
                                volleyCallback.onSuccessResponseString(String.valueOf(userId));
                            }
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    },
                    error -> {
                        // Handle error response
                        Log.e("GET-USER-ID-ERROR", error.toString());
                        volleyCallback.onErrorResponse(error);
                    }){
                @Override
                public Map<String, String> getHeaders() {
                    Map<String, String> params = new HashMap<>();
                    params.put("Authorization", "Bearer " + token);
                    params.put("Content-Type", "application/json");
                    return params;
                }
            };

            queue.add(request);
        } catch (Exception e) {
            Log.e("GET-USER-ID-ERROR", e.toString());
        }

    }
    public void getUser(VolleyCallbackUser volleyCallback) {
        RequestQueue queue = Volley.newRequestQueue(context);
        try {
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, Endpoints.SEARCH_USER_BY_ID +this.userId, null,
                    response -> {
                        // Handle successful response
                        try {
                            int id = response.getInt("id");
                            String name = response.getString("name");
                            String lastName = response.getString("last_name");
                            String email = response.getString("email");
                            String image = response.getString("image");

                            Log.d("GET-USER-SUCCESS", response.toString());
                           volleyCallback.onSuccessResponse(new User(id,name,lastName,email,image));

                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    },
                    error -> {
                        // Handle error response
                        Log.e("GET-USER-ERROR", error.toString());
                        volleyCallback.onErrorResponse(error);
                    }){
                @Override
                public Map<String, String> getHeaders() {
                    Map<String, String> params = new HashMap<>();
                    params.put("Authorization", "Bearer " + token);
                    params.put("Content-Type", "application/json");
                    return params;
                }
            };

            queue.add(request);
        } catch (Exception e) {
            Log.e("GET-USER-ID-ERROR", e.toString());
        }
    }

    public static void uploadImageRequest(Uri imageUri, Context context, VolleyCallback callback) {
        byte[] imageBytes = null;
        try {
            InputStream inputStream = context.getContentResolver().openInputStream(imageUri);
            imageBytes = new byte[inputStream.available()];
        } catch (IOException e) {
            Log.e("UPLOAD-IMAGE-ERROR", e.toString());
        }
        //TODO: Upload image to server
    }


    public void updateProfileRequest(String name, String lastName, String email, String password, VolleyCallbackUser volleyCallback) {
        RequestQueue queue = Volley.newRequestQueue(context);
        try {
          JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, Endpoints.USERS, null,
                    response -> {
                        // Handle successful response
                        try {
                            int id = response.getInt("id");
                            String name1 = response.getString("name");
                            String lastName1 = response.getString("last_name");
                            String email1 = response.getString("email");
                            String image = response.getString("image");

                            Log.d("UPDATE-PROFILE-SUCCESS", response.toString());
                            volleyCallback.onSuccessResponse(new User(id,name1,lastName1,email1,image));

                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    },
                    error -> {
                        // Handle error response
                        Log.e("UPDATE-PROFILE-ERROR", error.toString());
                        volleyCallback.onErrorResponse(error);
                    }){
                @Override
                public Map<String, String> getHeaders() {
                    Map<String, String> params = new HashMap<>();
                    params.put("Authorization", "Bearer " + token);
                    params.put("Content-Type", "application/json");
                    return params;
                }
                @Override
                public byte[] getBody() {
                    JSONObject jsonBody = new JSONObject();
                    try {
                        jsonBody.put("name", name);
                        jsonBody.put("last_name", lastName);
                        jsonBody.put("email", email);
                        jsonBody.put("password", password);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    return jsonBody.toString().getBytes();
                }
            };

            queue.add(request);

        }catch (Exception e) {
            Log.e("UPDATE-PROFILE-ERROR", e.toString());
        }
    }
}
