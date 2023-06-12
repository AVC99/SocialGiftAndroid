package com.example.socialgift.API;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.socialgift.R;
import com.example.socialgift.model.Product;
import com.example.socialgift.model.User;
import com.example.socialgift.model.Wishlist;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
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

    public static void loginRequest(String email, String password, Context context, VolleyCallback callback) {
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

    public void addFriend(int id, VolleyCallback callback) {
        RequestQueue queue = Volley.newRequestQueue(context);
        try {
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, Endpoints.FRIENDS + id, null,
                    response -> {
                        // Handle successful response

                        Log.d("ADD-FRIEND-SUCCESS", response.toString());

                        callback.onSuccessResponseString(String.valueOf(userId));
                    },
                    error -> {
                        // Handle error response
                        Log.e("ADD-FRIEND-ERROR", error.toString());
                        callback.onErrorResponse(error);
                    }) {
                @Override
                public Map<String, String> getHeaders() {
                    Map<String, String> headers = new HashMap<>();
                    headers.put("Authorization", "Bearer " + token);
                    return headers;
                }
            };
            queue.add(request);
        } catch (Exception e) {
            Log.e("ADD-FRIEND-ERROR", e.toString());
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

    public void getUserId(String email, String token, VolleyCallback volleyCallback) {
        RequestQueue queue = Volley.newRequestQueue(context);
        try {
            JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, Endpoints.SEARCH_USER + email, null,
                    response -> {
                        // Handle successful response
                        try {
                            if (response.length() > 0) {
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
                    }) {
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
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, Endpoints.SEARCH_USER_BY_ID + this.userId, null,
                    response -> {
                        // Handle successful response
                        try {
                            int id = response.getInt("id");
                            String name = response.getString("name");
                            String lastName = response.getString("last_name");
                            String email = response.getString("email");
                            String image = response.getString("image");

                            Log.d("GET-USER-SUCCESS", response.toString());
                            volleyCallback.onSuccessResponse(new User(id, name, lastName, email, image));

                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    },
                    error -> {
                        // Handle error response
                        Log.e("GET-USER-ERROR", error.toString());
                        volleyCallback.onErrorResponse(error);
                    }) {
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
                            volleyCallback.onSuccessResponse(new User(id, name1, lastName1, email1, image));

                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    },
                    error -> {
                        // Handle error response
                        Log.e("UPDATE-PROFILE-ERROR", error.toString());
                        volleyCallback.onErrorResponse(error);
                    }) {
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

        } catch (Exception e) {
            Log.e("UPDATE-PROFILE-ERROR", e.toString());
        }
    }

    public void removeFriend(int id, VolleyCallback callback) {
        RequestQueue queue = Volley.newRequestQueue(context);
        try {
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.DELETE, Endpoints.FRIENDS + id, null,
                    response -> {
                        // Handle successful response
                        Log.d("REMOVE-FRIEND-SUCCESS", response.toString());
                        callback.onSuccessResponseString(response.toString());
                    },
                    error -> {
                        // Handle error response
                        Log.e("REMOVE-FRIEND-ERROR", error.toString());
                        callback.onErrorResponse(error);
                    }) {
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
            Log.e("REMOVE-FRIEND-ERROR", e.toString());
        }
    }

    public void getFriends(VolleyCallbackUserArray callback) {
        RequestQueue queue = Volley.newRequestQueue(context);
        try {
            JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, Endpoints.FRIENDS, null,
                    response -> {
                        // Handle successful response
                        if (response.length() > 0) {
                            Log.d("GET-FRIEND-SUCCESS", response.toString());
                            ArrayList<User>friends = User.parseJsonArray(response);
                            callback.onSuccessResponse(friends);

                        } else {
                            Log.d("GET-FRIEND-SUCCESS", "No friends");
                            callback.onSuccessResponse(null);
                        }

                    },
                    error -> {
                        // Handle error response
                        Log.e("GET-FRIEND-ERROR", error.toString());
                        callback.onErrorResponse(error);
                    }) {
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
            Log.e("REMOVE-FRIEND-ERROR", e.toString());
        }
    }

    public void getUserWishlists(int id, VolleyCallbackWishlistArray callback) {
        RequestQueue queue = Volley.newRequestQueue(context);
        try {
            JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, Endpoints.USERS + id + "/wishlists", null,
                    response -> {
                        // Handle successful response
                        if (response.length() > 0) {
                            Log.d("GET-WISHLIST-SUCCESS", response.toString());
                            ArrayList<Wishlist> wishlists = Wishlist.parseJSONArray(response);
                            callback.onSuccessResponse(wishlists);

                        } else {
                            Log.d("GET-WISHLIST-SUCCESS", "No wishlists");
                            callback.onSuccessResponse(null);
                        }

                    },
                    error -> {
                        // Handle error response
                        Log.e("GET-WISHLIST-ERROR", error.toString());
                        callback.onErrorResponse(error);
                    }) {
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
            Log.e("GET-WISHLIST-ERROR", e.toString());
        }
    }

    public void getGiftInformation(String url, VolleyCallbackProduct volleyCallbackProduct) {
        RequestQueue queue = Volley.newRequestQueue(context);
        try {
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                    response -> {
                        try {
                            // Handle successful response
                            Log.d("GET-GIFT-SUCCESS", response.toString());
                            Product product = new Product(
                                    response.getInt("id"),
                                    response.getString("name"),
                                    response.getString("description"),
                                    response.getString("photo"),
                                    response.getDouble("price")
                            );
                            volleyCallbackProduct.onSuccessResponse(product);
                        } catch (JSONException e) {
                            Log.e("GET-GIFT-ERROR", e.toString());
                        }
                    },
                    error -> {
                        // Handle error response
                        Log.e("GET-GIFT-ERROR", error.toString());
                        volleyCallbackProduct.onErrorResponse(error);
                    }) {
                @Override
                public Map<String, String> getHeaders() {
                    Map<String, String> params = new HashMap<>();
                    params.put("Content-Type", "application/json");
                    return params;
                }
            };
            queue.add(request);
        } catch (Exception e) {
            Log.e("GET-GIFT-ERROR", e.toString());
        }
    }

    public void giftProduct(int id, VolleyCallback volleyCallback) {
        RequestQueue queue = Volley.newRequestQueue(context);
        try {
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, Endpoints.GIFT + id + "/book", null,
                    response -> {
                        // Handle successful response
                        Log.d("GIFT-SUCCESS", response.toString());
                        volleyCallback.onSuccessResponseString(response.toString());
                    },
                    error -> {
                        // Handle error response
                        Log.e("GIFT-ERROR", error.toString());
                        volleyCallback.onErrorResponse(error);
                    }) {
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
            Log.e("GIFT-ERROR", e.toString());
        }

    }

    public void saveProductToWishlist(int wishlistId, String productUrl, VolleyCallback callback) {
        RequestQueue queue = Volley.newRequestQueue(context);
        try {
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, Endpoints.GIFT, null,
                    response -> {
                        // Handle successful response
                        Log.d("SAVED-TO-WISHLIST-SUCCESS", response.toString());
                        callback.onSuccessResponseString(response.toString());
                    },
                    error -> {
                        // Handle error response
                        Log.e("SAVED-TO-WISHLIST-ERROR", error.toString());
                        callback.onErrorResponse(error);
                    }) {
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
                        jsonBody.put("wishlist_id", wishlistId);
                        jsonBody.put("product_url", productUrl);
                        jsonBody.put("priority", 1);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    return jsonBody.toString().getBytes();
                }
            };
            queue.add(request);
        } catch (Exception e) {
            Log.e("SAVE-PRODUCT-ERROR", e.toString());
        }
    }

    public void deleteProductFromWishlist(int id, VolleyCallback callback) {
        RequestQueue queue = Volley.newRequestQueue(context);
        try {
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.DELETE, Endpoints.GIFT + id, null,
                    response -> {
                        // Handle successful response
                        Log.d("DELETE-PRODUCT-SUCCESS", response.toString());
                        Log.d("DELETE-PRODUCT-SUCCESS", response.toString());
                        callback.onSuccessResponseString(response.toString());
                    },
                    error -> {
                        // Handle error response
                        Log.e("DELETE-PRODUCT-ERROR", error.toString());
                        callback.onErrorResponse(error);
                    }) {
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
            Log.e("DELETE-PRODUCT-ERROR", e.toString());
        }
    }

    public void deleteWishlist(int id, VolleyCallback callback) {
        RequestQueue queue = Volley.newRequestQueue(context);
        try {
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.DELETE, Endpoints.WISHLIST + id, null,
                    response -> {
                        // Handle successful response
                        Log.d("DELETE-WISHLIST-SUCCESS", response.toString());
                        callback.onSuccessResponseString(response.toString());
                    },
                    error -> {
                        // Handle error response
                        Log.e("DELETE-WISHLIST-ERROR", error.toString());
                        callback.onErrorResponse(error);
                    }) {
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
            Log.e("DELETE-WISHLIST-ERROR", e.toString());
        }
    }

    public void searchUsers(String toString, VolleyCallbackUserArray volleyCallback) {
        RequestQueue queue = Volley.newRequestQueue(context);
        try {
            JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, Endpoints.SEARCH_USER + toString, null,
                    response -> {
                        // Handle successful response
                        Log.d("SEARCH-USER-SUCCESS", response.toString());
                        ArrayList<User> users = new ArrayList<>();
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject user = response.getJSONObject(i);
                                users.add(new User(
                                        user.getInt("id"),
                                        user.getString("name"),
                                        user.getString("last_name"),
                                        user.getString("email"),
                                        user.getString("image")
                                ));
                            } catch (JSONException e) {
                                e.printStackTrace();

                            }
                        }
                        volleyCallback.onSuccessResponse(users);
                    },
                    error -> {
                        // Handle error response
                        Log.e("SEARCH-USER-ERROR", error.toString());
                        volleyCallback.onErrorResponse(error);
                    }) {
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
            Log.e("SEARCH-USER-ERROR", e.toString());
        }
    }

    public void searchProducts(String toString, VolleyCallbackProductArray callback) {
        RequestQueue queue = Volley.newRequestQueue(context);
        try {
            JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, Endpoints.SEARCH_PRODUCT + toString, null,
                    response -> {
                        // Handle successful response
                        Log.d("SEARCH-PRODUCT-SUCCESS", response.toString());
                        ArrayList<Product> products = new ArrayList<>();
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject product = response.getJSONObject(i);
                                products.add(new Product(
                                        product.getInt("id"),
                                        product.getString("name"),
                                        product.getString("description"),
                                        product.getString("photo"),
                                        product.getDouble("price")
                                ));
                            } catch (JSONException e) {
                                e.printStackTrace();

                            }
                        }
                        callback.onSuccessResponse(products);
                    },
                    error -> {
                        // Handle error response
                        Log.e("SEARCH-PRODUCT-ERROR", error.toString());
                        callback.onErrorResponse(error);
                    }) {
                @Override
                public Map<String, String> getHeaders() {
                    Map<String, String> params = new HashMap<>();
                    params.put("Content-Type", "application/json");
                    return params;
                }
            };
            queue.add(request);
        } catch (Exception e) {
            Log.e("SEARCH-PRODUCT-ERROR", e.toString());
        }
    }

    public void getNotifications(VolleyCallbackUserArray callback) {
        RequestQueue queue = Volley.newRequestQueue(context);
        try {
            JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, Endpoints.FRIENDS + "requests", null,
                    response -> {
                        // Handle successful response
                        ArrayList<User> users = new ArrayList<>();
                        if (response.length() > 0) {
                            Log.d("GET-NOTIFICATIONS-SUCCESS", response.toString());
                            users = User.parseJsonArray(response);
                            callback.onSuccessResponse(users);
                        }
                        callback.onSuccessResponse(users);
                    },
                    error -> {
                        // Handle error response
                        Log.e("GET-NOTIFICATIONS-ERROR", error.toString());
                        callback.onErrorResponse(error);
                    }) {
                @Override
                public Map<String, String> getHeaders() {
                    Map<String, String> params = new HashMap<>();
                    params.put("Content-Type", "application/json");
                    params.put("Authorization", "Bearer " + token);
                    return params;
                }
            };
            queue.add(request);
        } catch (Exception e) {
            Log.e("GET-NOTIFICATIONS-ERROR", e.toString());
        }

    }

    public void acceptFriendRequest(int id, VolleyCallback callback) {
        RequestQueue queue = Volley.newRequestQueue(context);
        try {
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, Endpoints.FRIENDS + id, null,
                    response -> {
                        // Handle successful response
                        Log.d("ACCEPT-FRIEND-SUCCESS", response.toString());
                        callback.onSuccessResponseString(response.toString());

                    },
                    error -> {
                        // Handle error response
                        Log.e("ACCEPT-FRIEND-ERROR", error.toString());
                        callback.onErrorResponse(error);
                    }) {
                @Override
                public Map<String, String> getHeaders() {
                    Map<String, String> params = new HashMap<>();
                    params.put("Content-Type", "application/json");
                    params.put("Authorization", "Bearer " + token);
                    return params;
                }
            };
            queue.add(request);

        } catch (Exception e) {
            Log.e("ACCEPT-FRIEND-ERROR", e.toString());
        }
    }

    public void createWishlist(String name, String description, String date, VolleyCallback callback) {
        RequestQueue queue = Volley.newRequestQueue(context);
        try {
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, Endpoints.WISHLIST, null,
                    response -> {
                        // Handle successful response
                        Log.d("CREATE-WISHLIST-SUCCESS", response.toString());
                        callback.onSuccessResponseString(response.toString());

                    },
                    error -> {
                        // Handle error response
                        Log.e("CREATE-WISHLIST-ERROR", error.toString());
                        callback.onErrorResponse(error);
                    }) {
                @Override
                public Map<String, String> getHeaders() {
                    Map<String, String> params = new HashMap<>();
                    params.put("Content-Type", "application/json");
                    params.put("Authorization", "Bearer " + token);
                    return params;
                }

                @Override
                public byte[] getBody() {
                    JSONObject body = new JSONObject();
                    try {
                        body.put("name", name);
                        body.put("description", description);
                        body.put("end_date", date);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    return body.toString().getBytes();
                }
            };
            queue.add(request);
        } catch (Exception e) {
            Log.e("CREATE-WISHLIST-ERROR", e.toString());
        }

    }

    public void createGift(String name, String description, String price, String link, VolleyCallback callback) {
        RequestQueue queue = Volley.newRequestQueue(context);
        try {
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, Endpoints.PRODUCTS, null,
                    response -> {
                        // Handle successful response

                        Log.d("ADD-FRIEND-SUCCESS", response.toString());

                        callback.onSuccessResponseString(String.valueOf(userId));
                    },
                    error -> {
                        // Handle error response
                        Log.e("ADD-FRIEND-ERROR", error.toString());
                        callback.onErrorResponse(error);
                    }) {
                @Override
                public Map<String, String> getHeaders() {
                    Map<String, String> headers = new HashMap<>();
                    headers.put("Content-Type", "application/json");
                    return headers;
                }

                @Override
                public byte[] getBody() {
                    JSONObject body = new JSONObject();
                    try {
                        body.put("name", name);
                        body.put("description", description);
                        body.put("link", link);
                        body.put("photo",link);
                        body.put("price", Double.valueOf(price));
                        body.put("categoryIds",1);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    return body.toString().getBytes();
                }
            };
            queue.add(request);

        } catch (Exception e) {
            Log.d("CREATE-GIFT-ERROR", e.toString());
        }

    }


}
