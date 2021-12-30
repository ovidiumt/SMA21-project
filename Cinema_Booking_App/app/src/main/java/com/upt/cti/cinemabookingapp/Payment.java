package com.upt.cti.cinemabookingapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fevziomurtekin.payview.Payview;

public class Payment extends AppCompatActivity {
    Payview payview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        payview = findViewById(R.id.payview);

        payview.setPayOnclickListener(v -> {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            Toast.makeText(Payment.this, "Payment completed. ", Toast.LENGTH_LONG).show();
        });
    }
}