package com.example.loginmate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    private Button btnBack, btnLogout;
    private TextView tvName, tvEmail;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        btnBack = findViewById(R.id.btnBack);
        btnLogout = findViewById(R.id.btnLogout);
        tvName = findViewById(R.id.tvName);
        tvEmail = findViewById(R.id.tvEmail);

        sharedPreferences = getSharedPreferences("LoginMatePrefs", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("KEY_NAME", "John Doe");
        String email = sharedPreferences.getString("KEY_EMAIL", "johndoe@example.com");

        if (tvName != null) {
            tvName.setText(name);
        }
        if (tvEmail != null) {
            tvEmail.setText(email);
        }

        btnBack.setOnClickListener(v -> finish());

        btnLogout.setOnClickListener(v -> {
            sharedPreferences.edit().putBoolean("KEY_IS_LOGGED_IN", false).apply();
            Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
    }
}
