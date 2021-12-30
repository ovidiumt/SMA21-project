package com.upt.cti.cinemabookingapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Confirm extends AppCompatActivity {
    Button confirmButton;
    Button cancelButton;
    EditText nameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        confirmButton = findViewById(R.id.buttonConfirm);
        cancelButton = findViewById(R.id.buttonCancel);
        nameText = findViewById(R.id.editTextName);

        confirmButton.setOnClickListener(v -> {
            String name = nameText.getText().toString().trim();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            Toast.makeText(Confirm.this,"Booking confirmed", Toast.LENGTH_SHORT).show();
        });

        cancelButton.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), MainActivity.class)));
    }
}