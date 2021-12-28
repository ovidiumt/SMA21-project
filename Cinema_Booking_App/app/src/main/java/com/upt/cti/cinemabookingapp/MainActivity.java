package com.upt.cti.cinemabookingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    ListView moviesLV;
    ArrayList<Movie> movieArrayList;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        moviesLV = findViewById(R.id.listViewMovies);
        movieArrayList = new ArrayList<>();

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        loadDataInListView();
    }

    private void loadDataInListView(){
        db.collection("Movies").get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    if(!queryDocumentSnapshots.isEmpty()){
                        List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                        for(DocumentSnapshot d : list){
                            Movie movie = d.toObject(Movie.class);
                            movieArrayList.add(movie);
                        }
                        MoviesLVAdapter adapter = new MoviesLVAdapter(MainActivity.this, movieArrayList);
                        moviesLV.setAdapter(adapter);
                    }else{
                        Toast.makeText(MainActivity.this, "No data found in Database", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(e ->
                Toast.makeText(MainActivity.this, "Fail to load data", Toast.LENGTH_SHORT).show());
    }

    public void logout(View view) {
        mAuth.signOut();
        startActivity(new Intent(getApplicationContext(), Login.class));
    }
}