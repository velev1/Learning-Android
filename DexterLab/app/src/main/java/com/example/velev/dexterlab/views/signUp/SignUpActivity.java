package com.example.velev.dexterlab.views.signUp;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.velev.dexterlab.R;
import com.example.velev.dexterlab.enums.MessageProvider;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity implements SignUpContract.View{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        onCreateUser();
    }

    @Override
    public void onCreateUser() {
        FloatingActionButton fabCreateUser = (FloatingActionButton) findViewById(R.id.fab_sign_up);
        fabCreateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText etUsername = (EditText) findViewById(R.id.et_username);
                String username = etUsername.getText().toString();

                String validSymbolMessage = isSymbolsValid(username);
                if(!validSymbolMessage.equals("")){
                    Toast.makeText(SignUpActivity.this, validSymbolMessage, Toast.LENGTH_SHORT).show();
                    showError(etUsername);
                }

            }
        });
    }

    public String isSymbolsValid(String input){
        Pattern pattern = Pattern.compile("[~#@*+%{}<>\\[\\]|\"\\_^]");
        Matcher matcher = pattern.matcher(input);

        String msg = "";

        boolean isMatch  = matcher.find();
        if(isMatch) {
            String invalidSymbols = "[~#@*+%{}<>\\[\\]|\"\\_^]";
            msg = "Cannot use the symbols " + invalidSymbols;
        }

        return msg;
    }

    private void showError(EditText editText) {
        Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
        editText.startAnimation(shake);
    }
}
