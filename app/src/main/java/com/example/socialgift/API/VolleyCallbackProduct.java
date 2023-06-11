package com.example.socialgift.API;

import com.android.volley.VolleyError;
import com.example.socialgift.model.Product;

public interface VolleyCallbackProduct {
    void onSuccessResponse(Product result);
    void onErrorResponse(VolleyError error);
}
