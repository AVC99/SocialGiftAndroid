package com.example.socialgift.API;

import com.android.volley.VolleyError;

public interface VolleyCallback {
    void onSuccessResponseString(String result);
    void onErrorResponse(VolleyError error);
}
