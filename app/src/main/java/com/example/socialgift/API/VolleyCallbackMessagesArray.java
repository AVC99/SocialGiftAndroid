package com.example.socialgift.API;

import com.android.volley.VolleyError;
import com.example.socialgift.model.Message;

import java.util.ArrayList;

public interface VolleyCallbackMessagesArray {
    void onSuccessResponse(ArrayList<Message> result);
    void onErrorResponse(VolleyError error);
}
