package com.upt.cti.cinemabookingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Seats extends AppCompatActivity {
    Button confirmSeats;
    ToggleButton[] seats = new ToggleButton[9];
    Map<String, String> seatsStatus = new HashMap<>();
    List<String> checkedSeats = new ArrayList<>();
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seats);

        confirmSeats = findViewById(R.id.buttonConfirmSeats);
        seats[0] = findViewById(R.id.A1);
        seats[1] = findViewById(R.id.B1);
        seats[2] = findViewById(R.id.C1);
        seats[3] = findViewById(R.id.A2);
        seats[4] = findViewById(R.id.B2);
        seats[5] = findViewById(R.id.C2);
        seats[6] = findViewById(R.id.A3);
        seats[7] = findViewById(R.id.B3);
        seats[8] = findViewById(R.id.C3);

        db = FirebaseFirestore.getInstance();

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String hour = intent.getStringExtra("hour");

        loadData(id, hour);

        confirmSeats.setOnClickListener(v -> {
            for(ToggleButton t : seats){
                if(t.isChecked())checkedSeats.add(t.getText().toString());
            }
            startActivity(new Intent(getApplicationContext(), Booking.class));
        });
    }

    private void loadData(String id, String hour) {
        DocumentReference documentReference = db.collection("Movies").document(id);
        documentReference.get().addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                DocumentSnapshot documentSnapshot = task.getResult();
                if(documentSnapshot.exists()){
                    seatsStatus = (Map<String, String>)documentSnapshot.get(hour);
                    setSeatsColor();
                }
            }
        });
    }

    public void setSeatsColor(){
        for(ToggleButton seat : seats){
            if(!seatsStatus.get(seat.getText()).equals("free")){
                seat.setBackgroundColor(Color.RED);
                seat.setEnabled(false);
            }
        }
    }

    public void click(View view) {
        switch(view.getId()){
            case R.id.A1:
                changeColor(seats[0]);
                break;
            case R.id.B1:
                changeColor(seats[1]);
                break;
            case R.id.C1:
                changeColor(seats[2]);
                break;
            case R.id.A2:
                changeColor(seats[3]);
                break;
            case R.id.B2:
                changeColor(seats[4]);
                break;
            case R.id.C2:
                changeColor(seats[5]);
                break;
            case R.id.A3:
                changeColor(seats[6]);
                break;
            case R.id.B3:
                changeColor(seats[7]);
                break;
            case R.id.C3:
                changeColor(seats[8]);
                break;
            default:
                break;
        }
    }

    private void changeColor(ToggleButton toggleButton){
        if(toggleButton.isChecked())toggleButton.setBackgroundColor(0xFFFF9800);
        else toggleButton.setBackgroundColor(0xFFAAAAAA);
    }

    public List<String> getCheckedSeats(){
        return checkedSeats;
    }
}