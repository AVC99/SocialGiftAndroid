package com.example.socialgift.API;

import com.android.volley.VolleyError;
import com.example.socialgift.model.Product;

import java.util.ArrayList;

public interface VolleyCallbackProductArray {
    void onSuccessResponse(ArrayList<Product> result);
    void onErrorResponse(VolleyError error);
}
