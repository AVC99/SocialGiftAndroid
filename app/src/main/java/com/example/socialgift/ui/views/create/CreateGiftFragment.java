package com.example.socialgift.ui.views.create;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.VolleyError;
import com.example.socialgift.API.APIRequest;
import com.example.socialgift.API.VolleyCallback;
import com.example.socialgift.R;

public class CreateGiftFragment extends Fragment {
    private EditText giftNameEditText;
    private EditText giftDescriptionEditText;
    private EditText giftPriceEditText;
   private EditText imageLinkEditText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_create_gift, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Button createGiftButton = view.findViewById(R.id.create_gift_create_button);
        this.giftNameEditText = view.findViewById(R.id.create_gift_name_edit_text);
        this.giftDescriptionEditText = view.findViewById(R.id.create_gift_description_edit_text);
        this.giftPriceEditText = view.findViewById(R.id.create_gift_price_edit_text);
        this.imageLinkEditText = view.findViewById(R.id.create_gift_link_edit_text);

        createGiftButton.setOnClickListener(v -> uploadGift());

    }




    private void uploadGift() {
        if (giftNameEditText.getText().toString().isEmpty() || giftDescriptionEditText.getText().toString().isEmpty()
                || giftPriceEditText.getText().toString().isEmpty() || imageLinkEditText.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
        } else {
            APIRequest apiRequest = new APIRequest(requireContext());

            apiRequest.createGift(giftNameEditText.getText().toString(),
                    giftDescriptionEditText.getText().toString(), giftPriceEditText.getText().toString(),
                    imageLinkEditText.getText().toString(),
                    new VolleyCallback() {
                        @Override
                        public void onSuccessResponseString(String result) {
                            requireActivity().finish();
                        }

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getContext(), "Error while uploading the product", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }


}
