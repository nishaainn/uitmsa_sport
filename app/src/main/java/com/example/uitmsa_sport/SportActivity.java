package com.example.uitmsa_sport;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import com.example.uitmsa_sport.entities.Club;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class SportActivity extends AppCompatActivity {

    private FirebaseFirestore firestore;
    private List<Club> clubs = new ArrayList<Club>();
    private FirebaseAuth auth;
    private ImageView profileBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport);

        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        GridView gridView = findViewById(R.id.gridView);

        CollectionReference collectionRef = firestore.collection("clubs");

        // Create a query to get all documents
        collectionRef.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        // Iterate through the documents
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            // Retrieve and handle document data
                            String documentId = documentSnapshot.getId();
                            Club club = documentSnapshot.toObject(Club.class);
                            club.setId(documentId);
                            clubs.add(club);
                        }
                        gridView.setAdapter(new ClubAdapter(SportActivity.this, clubs.toArray(new Club[0])));
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Handle any errors
                        Log.e("Error", "Error getting documents: ", e);
                    }
                });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), NetballActivity.class);
                intent.putExtra("clubId", clubs.get(position).getId());
                startActivity(intent);
            }
        });

        //logout
        profileBtn = findViewById(R.id.userIcon);
        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenu(v);
            }
        });
    }

    private void showMenu(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        popup.getMenuInflater().inflate(R.menu.menu, popup.getMenu());

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int menuLogout = R.id.menu_logout;
                if (item.getItemId() == menuLogout) {
                    auth.signOut();
                    Intent intent = new Intent(SportActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                    return true;
                }
                return false;
            }
        });

        popup.show();
    }
}
