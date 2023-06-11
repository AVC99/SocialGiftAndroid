package com.example.socialgift.ui.views.create;

import android.app.DatePickerDialog;
import android.icu.util.Calendar;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.socialgift.API.APIRequest;
import com.example.socialgift.API.VolleyCallback;
import com.example.socialgift.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;


public class CreateWishlistFragment extends Fragment {
    private Button createWishlistButton;
    private EditText wishlistNameEditText;
    private EditText wishlistDescriptionEditText;
    private EditText wishlistDateEditText;


    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy", Locale.ENGLISH);
    private SimpleDateFormat sendDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
    private Date date;
    private Calendar myCalendar = Calendar.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_create_wishlist, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        this.createWishlistButton = view.findViewById(R.id.create_wishlist_create_button);
        this.wishlistDateEditText = view.findViewById(R.id.create_wishlist_date_edit_text);
        this.wishlistDescriptionEditText = view.findViewById(R.id.create_wishlist_description_edit_text);
        this.wishlistNameEditText = view.findViewById(R.id.create_wishlist_name_edit_text);
        setUpDateEditText();

        this.createWishlistButton.setOnClickListener(v -> {
            if (wishlistDateEditText.getText().toString().isEmpty() || wishlistDescriptionEditText.getText().toString().isEmpty() || wishlistNameEditText.getText().toString().isEmpty()) {
                Toast.makeText(getContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                APIRequest apiRequest = new APIRequest(requireContext());
                apiRequest.createWishlist(wishlistNameEditText.getText().toString(),
                        wishlistDescriptionEditText.getText().toString(), sendDateFormat.format(date),
                        new VolleyCallback() {

                            @Override
                            public void onSuccessResponseString(String result) {
                                requireActivity().finish();
                            }

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getContext(), "Error creating wishlist", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });


    }

    private void setUpDateEditText() {
        wishlistDateEditText.setFocusable(false);
        wishlistDateEditText.setClickable(true);

        DatePickerDialog.OnDateSetListener date = (view, year, month, dayOfMonth) -> {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, month);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        };
        wishlistDateEditText.setOnClickListener(v -> {
            new DatePickerDialog(getContext(), date, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        });

    }

    private void updateLabel() {
        date = myCalendar.getTime();
        this.wishlistDateEditText.setText(dateFormat.format(myCalendar.getTime()));
    }
}