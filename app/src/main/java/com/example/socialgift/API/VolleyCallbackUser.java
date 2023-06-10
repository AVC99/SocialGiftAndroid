package com.example.socialgift.API;

import com.android.volley.VolleyError;
import com.example.socialgift.model.User;

public interface VolleyCallbackUser {
    void onSuccessResponse(User result);
    void onErrorResponse(VolleyError error);
}
