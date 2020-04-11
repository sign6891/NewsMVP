package com.example.newsmvp.authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newsmvp.R;
import com.example.newsmvp.news.NewsActivity;
import com.example.newsmvp.start_activity.StartActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

public class AuthenticationActivity extends AppCompatActivity {

    private TextInputLayout textInputEmail;
    private TextInputLayout textInputName;
    private TextInputLayout textInputPassword;
    private TextInputLayout textInputConfirmPassword;

    private Button loginSignUpButton;
    private TextView toggleLoginSignUpTextView;

    private boolean isLoginModeActive;
    private static final String TAG = "AuthenticationActivity";
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(AuthenticationActivity.this, NewsActivity.class));
        }

        textInputEmail = findViewById(R.id.textInputEmail);
        textInputName = findViewById(R.id.textInputName);
        textInputPassword = findViewById(R.id.textInputPassword);
        textInputConfirmPassword = findViewById(R.id.textInputConfirmPassword);
        loginSignUpButton = findViewById(R.id.loginSignUpButton);
        toggleLoginSignUpTextView = findViewById(R.id.toggleLoginSignUpTextView);
    }

    //валидация email
    private boolean validateEmail() {

        String emailInputPassenger = textInputEmail.getEditText().getText().toString().trim();

        if (emailInputPassenger.isEmpty()) {
            textInputEmail.setError("Please input your email");
            return false;
        } else {
            textInputEmail.setError("");
            return true;
        }
    }

    //валидация введеного имени
    private boolean validateName() {

        String nameInputPassenger = textInputName.getEditText().getText().toString().trim();

        if (nameInputPassenger.isEmpty()) {
            textInputName.setError("Please input your name");
            return false;
        } else if (nameInputPassenger.length() > 15) {
            textInputName.setError("Name length have to be less than 15");
            return false;
        } else {
            textInputName.setError("");
            return true;
        }
    }

    //валидация пароля
    private boolean validatePassword() {

        String passwordInput = textInputPassword.getEditText().getText().toString().trim();

        if (passwordInput.isEmpty()) {
            textInputPassword.setError("Please input your password");
            return false;
        } else if (passwordInput.length() < 7) {
            textInputPassword.setError("Password length have to be more than 6");
            return false;
        } else {
            textInputPassword.setError("");
            return true;
        }
    }

    //валидация повторного ввода пороля
    private boolean validateConfirmPassword() {

        String passwordInput = textInputPassword.getEditText().getText().toString().trim();
        String confirmPasswordInput = textInputConfirmPassword.getEditText().getText().toString().trim();

        if (!passwordInput.equals(confirmPasswordInput)) {
            textInputConfirmPassword.setError("Passwords have to match");
            return false;
        } else {
            textInputConfirmPassword.setError("");
            return true;
        }
    }

    //...
    public void loginSignUpUser(View view) {
        //если вход то проверяем введеные данные
        if (!validateEmail() | !validatePassword()) {
            return;
        }

        if (isLoginModeActive) {
            auth.signInWithEmailAndPassword(textInputEmail.getEditText().getText().toString().trim()
                    , textInputPassword.getEditText().getText().toString().trim())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInWithEmail:success");
                                startActivity(new Intent(AuthenticationActivity.this, NewsActivity.class));
                                FirebaseUser user = auth.getCurrentUser();
                            } else {
                                // If sign in fails, display a message to the user./
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(AuthenticationActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        } else {
            //иначе если регестрация то проверяем введеные данные
            if (!validateEmail() | !validateName() |  !validatePassword() | !validateConfirmPassword()) {
                return;
            }

            auth.createUserWithEmailAndPassword(textInputEmail.getEditText().getText().toString().trim()
                    , textInputPassword.getEditText().getText().toString().trim())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "createUserWithEmail:success");
                                FirebaseUser user = auth.getCurrentUser();
                                startActivity(new Intent(AuthenticationActivity.this, NewsActivity.class));
                            } else {
                                // If sign in fails, display a message to the user.
                                if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                    textInputEmail.setError("The email address is already in use by another account");
                                } else {
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(AuthenticationActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });
        }
    }

    //меняем регистрацию на вход и обратно
    public void toggleLoginSignUp(View view) {

        if (isLoginModeActive) {
            isLoginModeActive = false;
            loginSignUpButton.setText("Sign Up");
            toggleLoginSignUpTextView.setText("Or, log in");
            textInputConfirmPassword.setVisibility(View.VISIBLE);
            textInputName.setVisibility(View.VISIBLE);
        } else {
            isLoginModeActive = true;
            loginSignUpButton.setText("Log In");
            toggleLoginSignUpTextView.setText("Or, sign up");
            textInputConfirmPassword.setVisibility(View.GONE);
            textInputName.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
