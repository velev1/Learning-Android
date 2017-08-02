package com.example.velev.dexterlab.views.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.velev.dexterlab.R;
import com.example.velev.dexterlab.data.LocalData;
import com.example.velev.dexterlab.data.models.User;
import com.example.velev.dexterlab.views.signUp.SignUpActivity;

import java.util.List;

public class LogInActivity extends AppCompatActivity implements LoginContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);


        // TODO implement the logic
        onLogIn();

        onSignUp();
    }

    @Override
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

    @Override
    public void onLogIn() {

        Button btnLogin = (Button) findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText etUsername = (EditText) findViewById(R.id.et_username);
                String username = etUsername.getText().toString();
                EditText etPassword = (EditText) findViewById(R.id.et_password);
                String password = etPassword.getText().toString();

                boolean isFound = false;

                LocalData data = new LocalData();
                List<User> users = data.getUsers();

                for (int i = 0; i < users.size(); i++) {
                    if (users.get(i).getUsername().equals(username.toLowerCase()) &&
                            users.get(i).getPassword().equals(password.toLowerCase())) {
                        isFound = true;
                        break;
                    }
                }

                if (isFound) {
                    Toast.makeText(LogInActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LogInActivity.this, "username or password does not match", Toast.LENGTH_SHORT).show();
                    showError(etUsername);
                    showError(etPassword);
                }

            }
        });

    }

    private void showError(EditText editText) {
        Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
        editText.startAnimation(shake);
    }
}
