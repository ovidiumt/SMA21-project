package com.upt.cti.cinemabookingapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Confirm extends AppCompatActivity {
    Button confirmButton;
    Button cancelButton;
    EditText nameText;
    FirebaseFirestore db;
    String id;
    String hour;
    List<String> checkedSeats = new ArrayList<>();
    Map<String, String> seatsStatus = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        confirmButton = findViewById(R.id.buttonConfirm);
        cancelButton = findViewById(R.id.buttonCancel);
        nameText = findViewById(R.id.editTextName);

        db = FirebaseFirestore.getInstance();

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        hour = intent.getStringExtra("hour");
        checkedSeats = intent.getStringArrayListExtra("checkedSeats");
        seatsStatus = (HashMap<String, String>)intent.getSerializableExtra("seatsStatus");

        confirmButton.setOnClickListener(v -> {
            String name = nameText.getText().toString().trim();
            changeSeatStatus(name);
            db.collection("Movies").document(id).update(hour, seatsStatus);
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            Toast.makeText(Confirm.this,"Booking confirmed", Toast.LENGTH_SHORT).show();
        });

        cancelButton.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), MainActivity.class)));
    }

    private void changeSeatStatus(String name){
        for(String seat : checkedSeats){
            seatsStatus.put(seat, name);
        }
    }
}