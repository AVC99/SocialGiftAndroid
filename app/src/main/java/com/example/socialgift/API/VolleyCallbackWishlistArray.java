package com.example.socialgift.API;

import com.android.volley.VolleyError;
import com.example.socialgift.model.Wishlist;

import java.util.ArrayList;

public interface VolleyCallbackWishlistArray {
    void onSuccessResponse(ArrayList<Wishlist> result);
    void onErrorResponse(VolleyError error);
}
