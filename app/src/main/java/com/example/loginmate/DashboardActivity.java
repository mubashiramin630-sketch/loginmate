package com.example.loginmate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class DashboardActivity extends AppCompatActivity {

    private CardView cardProfile;
    private Button btnLogout;
    private TextView tvWelcome;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        cardProfile = findViewById(R.id.cardProfile);
        btnLogout = findViewById(R.id.btnLogout);
        tvWelcome = findViewById(R.id.tvWelcome);

        sharedPreferences = getSharedPreferences("LoginMatePrefs", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("KEY_NAME", "User");

        if (tvWelcome != null) {
            tvWelcome.setText("Welcome, " + name + "!");
        }

        if (cardProfile != null) {
            cardProfile.setOnClickListener(v -> {
                Intent intent = new Intent(DashboardActivity.this, ProfileActivity.class);
                startActivity(intent);
            });
        }

        btnLogout.setOnClickListener(v -> {
            // Clear login session flag
            sharedPreferences.edit().putBoolean("KEY_IS_LOGGED_IN", false).apply();

            Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
    }
}
