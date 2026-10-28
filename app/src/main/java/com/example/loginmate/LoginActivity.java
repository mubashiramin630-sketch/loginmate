package com.example.loginmate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private Button btnLogin;
    private TextView tvForgotPassword, tvRegister;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);
        tvRegister = findViewById(R.id.tvRegister);

        sharedPreferences = getSharedPreferences("LoginMatePrefs", Context.MODE_PRIVATE);

        btnLogin.setOnClickListener(v -> {
            String emailInput = etEmail.getText().toString().trim();
            String passwordInput = etPassword.getText().toString().trim();

            if (emailInput.isEmpty()) {
                etEmail.setError("Email or username is required");
                etEmail.requestFocus();
                return;
            }

            if (passwordInput.isEmpty()) {
                etPassword.setError("Password is required");
                etPassword.requestFocus();
                return;
            }

            // Retrieve stored credentials (from RegisterActivity)
            String savedEmail = sharedPreferences.getString("KEY_EMAIL", "user@loginmate.com");
            String savedPassword = sharedPreferences.getString("KEY_PASSWORD", "password123");

            // Check if credentials match either registered user or default credentials
            if ((emailInput.equals(savedEmail) || emailInput.equals("admin")) && passwordInput.equals(savedPassword)) {
                Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                
                // Save session state
                sharedPreferences.edit().putBoolean("KEY_IS_LOGGED_IN", true).apply();

                Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(LoginActivity.this, "Invalid email or password. Please try again.", Toast.LENGTH_LONG).show();
            }
        });

        tvForgotPassword.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
            startActivity(intent);
        });

        tvRegister.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }
}
