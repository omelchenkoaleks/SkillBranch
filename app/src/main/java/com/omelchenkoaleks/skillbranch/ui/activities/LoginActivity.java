package com.omelchenkoaleks.skillbranch.ui.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.omelchenkoaleks.skillbranch.R;
import com.omelchenkoaleks.skillbranch.utils.ConstantManager;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = ConstantManager.TAG_PREFIX + "LoginActivity";

    private Button singIn;
    private TextView rememberPassword;
    private EditText login;
    private EditText password;
    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        coordinatorLayout = findViewById(R.id.login_coordinator_container);
        singIn = findViewById(R.id.login_btn);
        rememberPassword = findViewById(R.id.remember_txt);
        login = findViewById(R.id.login_email_et);
        password = findViewById(R.id.login_password_et);

        rememberPassword.setOnClickListener(this);
        singIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_btn:
                showSnackbar("Вход понарошку...)))");
                break;
            case R.id.remember_txt:
                rememberPassword();
                break;
        }
    }

    private void showSnackbar(String message) {
        Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_LONG).show();
    }

    // напоминание пользователю, что он забыл пароль
    private void rememberPassword() {
        Intent rememberIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://devintensive.softdesign-apps.ru/forgotpass"));
        startActivity(rememberIntent);
    }

    // срабатывает, есил логин успешен
    private void loginSuccess() {

    }
}
