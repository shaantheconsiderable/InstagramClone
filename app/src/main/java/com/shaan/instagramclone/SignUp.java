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
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private Button btnLogInSignUp, btnSignUpSignUp;
    private EditText edtEmailSignUp, edtUsernameSignUp, edtPasswordSignUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        setTitle("Sign Up");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.instamini);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        btnLogInSignUp = findViewById(R.id.btnLogInSignUp);
        btnLogInSignUp.setOnClickListener(this);
        btnSignUpSignUp = findViewById(R.id.btnSignUpSignUp);
        btnSignUpSignUp.setOnClickListener(this);
        edtEmailSignUp = findViewById(R.id.edtEmailSignUp);
        edtUsernameSignUp = findViewById(R.id.edtUsernameSignUp);
        edtPasswordSignUp = findViewById(R.id.edtPasswordSignUp);
        edtPasswordSignUp.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent event) {
                if(keyCode==KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN){
                    onClick(btnSignUpSignUp);
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
            case R.id.btnSignUpSignUp:

                if (edtEmailSignUp.getText().toString().equals("") ||
                edtUsernameSignUp.getText().toString().equals("") ||
                edtPasswordSignUp.getText().toString().equals("")){
                    FancyToast.makeText(SignUp.this,"Email, username, password are required!",
                            FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                }
                else {
                    final ParseUser appUser = new ParseUser();
                    appUser.setEmail(edtEmailSignUp.getText().toString());
                    appUser.setUsername(edtUsernameSignUp.getText().toString());
                    appUser.setPassword(edtPasswordSignUp.getText().toString());

                    final ProgressDialog progressDialog = new ProgressDialog(this);
                    progressDialog.setMessage("Signing up " + edtUsernameSignUp.getText().toString());
                    progressDialog.show();
                    appUser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                FancyToast.makeText(SignUp.this, appUser.getUsername() + " is signed up successfully",
                                        FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                            } else {
                                FancyToast.makeText(SignUp.this, e.getMessage(),
                                        FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                            }
                            progressDialog.dismiss();
                        }
                    });
                }
                break;
            case R.id.btnLogInSignUp:
                Intent intent = new Intent(SignUp.this, LogIn.class);
                startActivity(intent);
                break;
        }


    }
    public void rootLayoutTapped(View view){
        try{
            InputMethodManager inputMethodManager =
                    (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
