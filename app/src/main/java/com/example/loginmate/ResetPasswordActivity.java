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

public class ResetPasswordActivity extends AppCompatActivity {

    private EditText etNewPassword, etConfirmNewPassword;
    private Button btnReset;
    private TextView tvBackToLogin;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        etNewPassword = findViewById(R.id.etNewPassword);
        etConfirmNewPassword = findViewById(R.id.etConfirmNewPassword);
        btnReset = findViewById(R.id.btnReset);
        tvBackToLogin = findViewById(R.id.tvBackToLogin);

        sharedPreferences = getSharedPreferences("LoginMatePrefs", Context.MODE_PRIVATE);

        btnReset.setOnClickListener(v -> {
            String newPass = etNewPassword.getText().toString().trim();
            String confirmPass = etConfirmNewPassword.getText().toString().trim();

            if (newPass.isEmpty()) {
                etNewPassword.setError("New password is required");
                etNewPassword.requestFocus();
                return;
            }

            if (newPass.length() < 6) {
                etNewPassword.setError("Password must be at least 6 characters");
                etNewPassword.requestFocus();
                return;
            }

            if (confirmPass.isEmpty()) {
                etConfirmNewPassword.setError("Please confirm your password");
                etConfirmNewPassword.requestFocus();
                return;
            }

            if (!newPass.equals(confirmPass)) {
                etConfirmNewPassword.setError("Passwords do not match");
                etConfirmNewPassword.requestFocus();
                Toast.makeText(ResetPasswordActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                return;
            }

            // Update password in SharedPreferences
            sharedPreferences.edit().putString("KEY_PASSWORD", newPass).apply();

            Toast.makeText(ResetPasswordActivity.this, "Password reset successful! Please login with your new password.", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(ResetPasswordActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });

        tvBackToLogin.setOnClickListener(v -> {
            Intent intent = new Intent(ResetPasswordActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }
}
