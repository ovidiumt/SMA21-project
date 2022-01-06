package com.upt.cti.cinemabookingapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fevziomurtekin.payview.Payview;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Payment extends AppCompatActivity {
    Payview payview;
    FirebaseFirestore db;
    String id;
    String hour;
    List<String> checkedSeats = new ArrayList<>();
    Map<String, String> seatsStatus = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        payview = findViewById(R.id.payview);

        db = FirebaseFirestore.getInstance();

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        hour = intent.getStringExtra("hour");
        checkedSeats = intent.getStringArrayListExtra("checkedSeats");
        seatsStatus = (HashMap<String, String>)intent.getSerializableExtra("seatsStatus");

        payview.setPayOnclickListener(v -> {
            changeSeatStatus();
            db.collection("Movies").document(id).update(hour, seatsStatus);
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            Toast.makeText(Payment.this, "Payment completed. ", Toast.LENGTH_LONG).show();
        });
    }

    private void changeSeatStatus(){
        for(String seat : checkedSeats){
            seatsStatus.put(seat, "occupied");
        }
    }
}