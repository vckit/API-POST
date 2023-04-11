package com.example.myapplication;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button sendButton;
    private Button checkPasswordButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        sendButton = findViewById(R.id.sendButton);
        checkPasswordButton = findViewById(R.id.checkPasswordButton);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                if (isValidEmail(email)) {
                    ApiClient.sendEmail(email);
                    Toast.makeText(MainActivity.this, "Generated password sent to email", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Invalid email address", Toast.LENGTH_SHORT).show();
                }
            }
        });


        checkPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = passwordEditText.getText().toString();
                if (isSecurePassword(password)) {
                    Toast.makeText(MainActivity.this, "Password is secure", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Password is not secure", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isSecurePassword(String password) {
        int minLength = 8;
        boolean hasUpperCase = false;
        boolean hasLowerCase = false;
        boolean hasDigit = false;
        boolean hasSpecialChar = false;
        String specialCharacters = "!@#$%&*";

        if (password.length() < minLength) {
            return false;
        }

        for (int i = 0; i < password.length(); i++) {
            char currentChar = password.charAt(i);
            if (Character.isUpperCase(currentChar)) {
                hasUpperCase = true;
            } else if (Character.isLowerCase(currentChar)) {
                hasLowerCase = true;
            } else if (Character.isDigit(currentChar)) {
                hasDigit = true;
            } else if (specialCharacters.contains(String.valueOf(currentChar))) {
                hasSpecialChar = true;
            }
        }

        return hasUpperCase && hasLowerCase && hasDigit && hasSpecialChar;
    }


        private boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        }
        return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    private String generateRandomPassword(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%&*";
        StringBuilder password = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            password.append(characters.charAt(random.nextInt(characters.length())));
        }

        return password.toString();
    }
}
