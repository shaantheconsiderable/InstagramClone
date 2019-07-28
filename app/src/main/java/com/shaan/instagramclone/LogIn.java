package com.shaan.instagramclone;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.security.Key;

public class LogIn extends AppCompatActivity implements View.OnClickListener {

    private Button btnLogInLogIn, btnSignUpLogin;
    private EditText edtEmailLogIn, edtPasswordLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.instamini);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        btnSignUpLogin = findViewById(R.id.btnSignUpLogin);
        btnSignUpLogin.setOnClickListener(this);
        btnLogInLogIn = findViewById(R.id.btnLogInLogIn);
        btnLogInLogIn.setOnClickListener(this);
        edtEmailLogIn = findViewById(R.id.edtEmailLogIn);
        edtPasswordLogIn = findViewById(R.id.edtPasswordLogIn);
        edtPasswordLogIn.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode == KeyEvent.KEYCODE_ENTER && event.getAction()==KeyEvent.ACTION_DOWN)
                {
                    onClick(btnLogInLogIn);
                }
                return false;
            }
        });

        if(ParseUser.getCurrentUser() != null){
            ParseUser.getCurrentUser().logOut();
        }

    }

    @Override
    public void onClick(View buttonView) {

        switch(buttonView.getId()){
            case R.id.btnLogInLogIn:
                final ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setMessage("Logging in...");
                progressDialog.show();
                ParseUser.logInInBackground(edtEmailLogIn.getText().toString(), edtPasswordLogIn.getText().toString(),
                        new LogInCallback() {
                            @Override
                            public void done(ParseUser user, ParseException e) {
                                if(user != null && e == null){
                                    FancyToast.makeText(LogIn.this, user.get("username") +
                                            " is logged is successfully ", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                                }
                                else{
                                    FancyToast.makeText(LogIn.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();

                                }
                                progressDialog.dismiss();
                            }
                        });

                break;
            case R.id.btnSignUpLogin:
                Intent intent = new Intent(LogIn.this, SignUp.class);
                startActivity(intent);
                break;
        }
    }
    public void loginLayoutTapped(View view){
        try{
            InputMethodManager inputMethodManager= (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);

        }
        catch(Exception e){
         e.printStackTrace();
        }
    }
}
