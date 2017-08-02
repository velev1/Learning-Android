package com.example.velev.dexterlab.views.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.velev.dexterlab.R;
import com.example.velev.dexterlab.views.signUp.SignUpActivity;

public class LogInActivity extends AppCompatActivity {

    private static final String username = "dexter";
    private static final String password = "123456";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);


        // TODO implement the logic
        onLogIn();

        onSignUp();
    }

    public void onSignUp() {
        TextView tvSignUp = (TextView) findViewById(R.id.tv_sign_up);
        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogInActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    public void onLogIn() {

        Button btnLogin = (Button) findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
