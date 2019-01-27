package com.omelchenkoaleks.skillbranch.ui.activities;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.omelchenkoaleks.skillbranch.R;
import com.omelchenkoaleks.skillbranch.utils.ConstantManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = ConstantManager.TAG_PREFIX + "Main Activity";

    protected EditText editText;
    protected Button redButton;
    protected Button greenButton;
    protected int colorMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate");

        editText = findViewById(R.id.edit_text);
        redButton = findViewById(R.id.red_btn);
        greenButton = findViewById(R.id.green_btn);

        redButton.setOnClickListener(this);
        greenButton.setOnClickListener(this);

        if (savedInstanceState == null) {
            // Activity запускается впервые
        } else {
            colorMode = savedInstanceState.getInt(ConstantManager.COLOR_MODE_KEY);
            if (colorMode == Color.RED) {
                editText.setBackgroundColor(Color.RED);
            } else if (colorMode == Color.GREEN) {
                editText.setBackgroundColor(Color.GREEN);
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.red_btn:
                editText.setBackgroundColor(Color.RED);
                colorMode = Color.RED;
                break;
            case R.id.green_btn:
                editText.setBackgroundColor(Color.GREEN);
                colorMode = Color.GREEN;
                break;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState");

        outState.putInt(ConstantManager.COLOR_MODE_KEY, colorMode);
    }
}
