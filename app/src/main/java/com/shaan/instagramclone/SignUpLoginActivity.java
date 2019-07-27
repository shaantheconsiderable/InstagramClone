package com.shaan.instagramclone;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUpLoginActivity extends AppCompatActivity {

    private EditText edtUnSignUp, edtPwSignUp, edtUnLogIn, edtPwLogIn;
    private Button btnSignUp,btnLogIn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_login_activity);

        edtUnSignUp = findViewById(R.id.edtUserNameSignUp);
        edtPwSignUp = findViewById(R.id.edtPasswordSignUp);
        edtUnLogIn = findViewById(R.id.edtUserNameLogIn);
        edtPwLogIn = findViewById(R.id.edtPasswordLogIn);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnLogIn = findViewById(R.id.btnLogIn);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ParseUser appUser = new ParseUser();
                appUser.setUsername(edtUnSignUp.getText().toString());
                appUser.setPassword(edtPwSignUp.getText().toString());
                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e == null){
                            FancyToast.makeText(SignUpLoginActivity.this,
                                    appUser.get("username") + " is signed up successfully",
                                    Toast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                        }
                        else{
                            FancyToast.makeText(SignUpLoginActivity.this,
                                    e.getMessage(), Toast.LENGTH_LONG, FancyToast.ERROR, true).show();
                        }
                    }
                });

            }
        });

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ParseUser.logInInBackground(edtUnLogIn.getText().toString(), edtPwLogIn.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if(user != null && e == null){
                            FancyToast.makeText(SignUpLoginActivity.this,
                                    user.get("username") + " is logged in successfully",
                                    Toast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                        }
                        else{
                            FancyToast.makeText(SignUpLoginActivity.this,
                                    e.getMessage(), Toast.LENGTH_LONG, FancyToast.ERROR, true).show();
                        }
                    }
                });

            }
        });
    }
}
