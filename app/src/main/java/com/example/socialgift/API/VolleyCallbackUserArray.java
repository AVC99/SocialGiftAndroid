package com.example.socialgift.API;

import com.android.volley.VolleyError;
import com.example.socialgift.model.User;

import java.util.ArrayList;

public interface VolleyCallbackUserArray {
    void onSuccessResponse(ArrayList<User> result);
    void onErrorResponse(VolleyError error);
}
