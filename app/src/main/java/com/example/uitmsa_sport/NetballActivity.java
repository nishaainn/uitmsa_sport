package com.example.uitmsa_sport;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.uitmsa_sport.entities.Application;
import com.example.uitmsa_sport.entities.Club;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class NetballActivity extends AppCompatActivity {

    EditText playerName;
    EditText phoneNo;
    EditText programCode;
    EditText semester;

    Button buttonAdd;

    private Club club;
    private FirebaseAuth auth;
    private FirebaseFirestore firestore;
    private ImageView profileBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_netball);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        playerName = findViewById(R.id.inputnamenetball);
        phoneNo = findViewById(R.id.inputphonenetball);
        programCode = findViewById(R.id.inputprogramnetball);
        semester = findViewById(R.id.inputsemseternetball);

        Intent intent = getIntent();
        String clubId = (String) intent.getStringExtra("clubId");
        TextView title = findViewById(R.id.netballtxt);
        ImageView imageView = findViewById(R.id.imageViewSport);

        firestore.collection("clubs").document(clubId).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot snapshot) {
                club = snapshot.toObject(Club.class);
                club.setId(snapshot.getId());
                title.setText("Join " + club.getName() + " Club");
                Picasso.get().load(club.getImageURL()).into(imageView);

            }
        });

        Button btnSubmit = findViewById(R.id.submitbtnnetball);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> formData = new HashMap<>();
                formData.put("userId", auth.getCurrentUser().getUid());
                formData.put("clubId", club.getId());
                formData.put("email", auth.getCurrentUser().getEmail());
                formData.put("name", playerName.getText().toString());
                formData.put("phoneNo", phoneNo.getText().toString());
                formData.put("programCode", programCode.getText().toString());
                formData.put("semester", Integer.parseInt(semester.getText().toString()));

                firestore.collection("applications").add(formData).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Intent intent = new Intent(NetballActivity.this, SavedActivity.class);
                        intent.putExtra("name", formData.get("name").toString());
                        intent.putExtra("clubName", club.getName());
                        intent.putExtra("email", formData.get("email").toString());
                        intent.putExtra("phoneNo", formData.get("phoneNo").toString());
                        intent.putExtra("programCode", formData.get("programCode").toString());
                        intent.putExtra("semester", Integer.parseInt(formData.get("semester").toString()));

                        startActivity(intent);
                    }
                });
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
                    Intent intent = new Intent(NetballActivity.this, LoginActivity.class);
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