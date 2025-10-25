package com.example.racinggame;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private final String USERNAME = "huy";
    private final String PASSWORD = "123";
    EditText edtUsername, edtPassword;
    Button btnSignIn;
    String username, password;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsername = (EditText) findViewById(R.id.editTextUsername);
        edtPassword = (EditText) findViewById(R.id.editTextPassword);
        btnSignIn = (Button) findViewById(R.id.buttonSignIn);

        username = edtUsername.getText().toString();
        password = edtPassword.getText().toString();

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, username, Toast.LENGTH_LONG).show();
                if (username.equalsIgnoreCase("huy") && password.equalsIgnoreCase("1")) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });

    }

}
