package com.omelchenkoaleks.skillbranch.ui.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.omelchenkoaleks.skillbranch.R;
import com.omelchenkoaleks.skillbranch.utils.ConstantManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = ConstantManager.TAG_PREFIX + "Main Activity";

    private int currentEditMode;

    private FloatingActionButton fab;
    private CoordinatorLayout coordinatorLayout;
    private Toolbar toolbar;
    private DrawerLayout navigationDrawer;

    // все view пользовательские
    private EditText userPhone;
    private EditText userEmail;
    private EditText userVk;
    private EditText userGit;
    private EditText userBio;

    private List<View> userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate");

        userPhone = findViewById(R.id.phone_et);
        userEmail = findViewById(R.id.email_et);
        userVk = findViewById(R.id.vk_et);
        userGit = findViewById(R.id.github_et);
        userBio = findViewById(R.id.bio_et);

        // создаем список, в который добавляем все наши View, чтобы дальше можно было
        // работать с ними в цикле (добавлять в них данные, переключать в режим редактирования и так далее...)
        userInfo = new ArrayList<>();
        userInfo.add(userPhone);
        userInfo.add(userEmail);
        userInfo.add(userVk);
        userInfo.add(userGit);
        userInfo.add(userBio);

        coordinatorLayout = findViewById(R.id.main_coordinator_container);
        toolbar = findViewById(R.id.toolbar);
        navigationDrawer = findViewById(R.id.navigation_drawer);

        fab = findViewById(R.id.fab_btn);
        fab.setOnClickListener(this);

        setupToolbar();
        setupDrawer();


        if (savedInstanceState == null) {
        } else {
            currentEditMode = savedInstanceState.getInt(ConstantManager.EDIT_MODE_KEY, 0);
            changeEditMode(currentEditMode);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            navigationDrawer.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
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
            case R.id.fab_btn:
                if (currentEditMode == 0) {
                    changeEditMode(1);
                    currentEditMode = 1;
                } else {
                    changeEditMode(0);
                    currentEditMode = 0;
                }
                break;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(ConstantManager.EDIT_MODE_KEY, currentEditMode);
    }

    private void showSnackbar(String message) {
        Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_LONG).show();
    }

    // будет устанавливать Toolbar вместо Actionbar
    private void setupToolbar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    // вспомогательный метод, где установки для NavigationDrawer
    private void setupDrawer() {
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                showSnackbar(menuItem.getTitle().toString());
                menuItem.setChecked(true);
                navigationDrawer.closeDrawer(GravityCompat.START);
                return false;
            }
        });
    }

    /**
     * переключает режим редактирования
     *
     * @param mode 1 режим редактирования, если 0 режим просмотра
     */
    private void changeEditMode(int mode) {
        if (mode == 1) {
            fab.setImageResource(R.drawable.ic_done_black_24dp);
            for (View userValue : userInfo) {
                userValue.setEnabled(true);
                userValue.setFocusable(true);
                userValue.setFocusableInTouchMode(true);
            }
        } else {
            fab.setImageResource(R.drawable.ic_mode_edit_black_24dp);
            for (View userValue : userInfo) {
                userValue.setEnabled(false);
                userValue.setFocusable(false);
                userValue.setFocusableInTouchMode(false);
            }
        }
    }

    // отвечает за загрузку пользательских данных
    private void loadUserInfoValue() {

    }

    // отвечает за сохранение пользовательских данных
    private void saveUserInfoValue() {

    }
}
