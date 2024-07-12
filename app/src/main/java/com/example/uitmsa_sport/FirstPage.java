package com.example.uitmsa_sport;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class FirstPage extends AppCompatActivity {

    private Button logoutButton;
    private FirebaseAuth auth;

    private ImageView profileBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);

        // Initialize FirebaseAuth
        auth = FirebaseAuth.getInstance();

        // Create a list of sports
        List<Sport> sports = new ArrayList<>();
        sports.add(new Sport("Basketball", R.drawable.basketball, "Basketball is a team sport where two teams, most commonly of five players each, oppose each other on a rectangular court."));
        sports.add(new Sport("Football", R.drawable.football, "Football, also known as soccer, is a team sport played between two teams of eleven players with a spherical ball."));
        sports.add(new Sport("Volleyball", R.drawable.volleyball, "Volleyball is a team sport in which two teams of six players are separated by a net."));
        sports.add(new Sport("Badminton", R.drawable.badminton, "Badminton is a racquet sport played using racquets to hit a shuttlecock across a net."));

        // Set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new SportAdapter(sports));

        // Set up the Join button
        Button joinbtn = findViewById(R.id.joinbtn);
        joinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstPage.this, SportActivity.class);
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
                    Intent intent = new Intent(FirstPage.this, NetballActivity.class);
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
