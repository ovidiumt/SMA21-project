package com.upt.cti.cinemabookingapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Booking extends AppCompatActivity {
    Button bookingButton;
    Button payButton;
    String id;
    String hour;
    ArrayList<String> checkedSeats = new ArrayList<>();
    Map<String, String> seatsStatus = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        bookingButton = findViewById(R.id.buttonBooking);
        payButton = findViewById(R.id.buttonPay);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        hour = intent.getStringExtra("hour");
        checkedSeats = intent.getStringArrayListExtra("checkedSeats");
        seatsStatus = (HashMap<String, String>)intent.getSerializableExtra("seatsStatus");

        bookingButton.setOnClickListener(v -> {
            Intent newIntent = new Intent(new Intent(getApplicationContext(), Confirm.class));
            putContentInIntent(newIntent);
            startActivity(newIntent);
        });
        payButton.setOnClickListener(v -> {
            Intent newIntent = new Intent(new Intent(getApplicationContext(), Payment.class));
            putContentInIntent(newIntent);
            startActivity(newIntent);
        });
    }

    private void putContentInIntent(Intent newIntent) {
        newIntent.putExtra("id", id);
        newIntent.putExtra("hour", hour);
        newIntent.putStringArrayListExtra("checkedSeats", checkedSeats);
        newIntent.putExtra("seatsStatus", (Serializable) seatsStatus);
    }
}