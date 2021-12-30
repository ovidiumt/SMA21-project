package com.upt.cti.cinemabookingapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Booking extends AppCompatActivity {
    Button bookingButton;
    Button payButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        bookingButton = findViewById(R.id.buttonBooking);
        payButton = findViewById(R.id.buttonPay);

        bookingButton.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), Confirm.class)));
        payButton.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), Payment.class)));
    }
}