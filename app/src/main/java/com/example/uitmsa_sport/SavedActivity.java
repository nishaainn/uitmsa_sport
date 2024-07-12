package com.example.uitmsa_sport;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class SavedActivity extends AppCompatActivity {

    private String name;
    private String clubName;
    private String email;
    private String phoneNo;
    private String programCode;
    private int semester;

    FirebaseAuth auth;
    private ImageView profileBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved);
        auth = FirebaseAuth.getInstance();

        // Retrieve the TextView
        TextView retrievedInfo = findViewById(R.id.retrievedInfo);

        // Get the intent and extract the information
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        clubName = intent.getStringExtra("clubName");
        email = intent.getStringExtra("email");
        phoneNo = intent.getStringExtra("phoneNo");
        programCode = intent.getStringExtra("programCode");
        semester = intent.getIntExtra("semester", 1);

        // Format the retrieved information
        String retrievedText = String.format(
                "Name: %s\nSport: %s\nEmail: %s\nPhone: %s\nProgram Code: %s\nCurrent Semester: %s",
                name, clubName, email, phoneNo, programCode, semester
        );

        // Display the retrieved information
        retrievedInfo.setText(retrievedText);

        Button backBtn = findViewById(R.id.backbtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SavedActivity.this, SportActivity.class);
                startActivity(intent);
                finish();
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
                    Intent intent = new Intent(SavedActivity.this, MainActivity.class);
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
