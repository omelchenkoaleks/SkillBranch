package com.omelchenkoaleks.skillbranch.ui.activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
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
import android.widget.RelativeLayout;

import com.omelchenkoaleks.skillbranch.R;
import com.omelchenkoaleks.skillbranch.data.managers.DataManager;
import com.omelchenkoaleks.skillbranch.utils.ConstantManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = ConstantManager.TAG_PREFIX + "Main Activity";

    private DataManager dataManager;
    private int currentEditMode = 0;

    private AppBarLayout appBarLayout;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private RelativeLayout profilePlaceholder;
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

    private List<EditText> userInfoViews;

    private AppBarLayout.LayoutParams appBarParams = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate");

        dataManager = DataManager.getInstance();

        userPhone = findViewById(R.id.phone_et);
        userEmail = findViewById(R.id.email_et);
        userVk = findViewById(R.id.vk_et);
        userGit = findViewById(R.id.github_et);
        userBio = findViewById(R.id.bio_et);

        // создаем список, в который добавляем все наши View, чтобы дальше можно было
        // работать с ними в цикле (добавлять в них данные, переключать в режим редактирования и так далее...)
        userInfoViews = new ArrayList<>();
        userInfoViews.add(userPhone);
        userInfoViews.add(userEmail);
        userInfoViews.add(userVk);
        userInfoViews.add(userGit);
        userInfoViews.add(userBio);

        appBarLayout = findViewById(R.id.appbar_layout);
        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        coordinatorLayout = findViewById(R.id.main_coordinator_container);
        toolbar = findViewById(R.id.toolbar);
        navigationDrawer = findViewById(R.id.navigation_drawer);
        profilePlaceholder = findViewById(R.id.profile_placeholder);
        fab = findViewById(R.id.fab_btn);

        profilePlaceholder.setOnClickListener(this);
        fab.setOnClickListener(this);

        setupToolbar();
        setupDrawer();
        loadUserInfoValue();

//        List<String> test = dataManager.getPreferenceManager().loadUserProfileData();

        if (savedInstanceState == null) {
        } else {
            // при редактировании сохраняем данные, если вдруг будет перевернут экран устройства:
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
        saveUserInfoValue();
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
            case R.id.profile_placeholder:
                // TODO: сделать выбор откуда загружать фото
                showDialog(ConstantManager.LOAD_PROFILE_PHOTO);
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

        appBarParams = (AppBarLayout.LayoutParams) collapsingToolbarLayout.getLayoutParams();
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
     * Получение результата из другой Activity (фото из камеры или галереи)
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
    }

    // при нажатии на системную кнопку back, закрывает NavigationDrawer:
    @Override
    public void onBackPressed() {
        if (navigationDrawer.isDrawerOpen(GravityCompat.START)) {
            navigationDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /**
     * переключает режим редактирования
     *
     * @param mode 1 режим редактирования, если 0 режим просмотра
     */
    private void changeEditMode(int mode) {
        if (mode == 1) {
            fab.setImageResource(R.drawable.ic_done_black_24dp);
            for (EditText userValue : userInfoViews) {
                userValue.setEnabled(true);
                userValue.setFocusable(true);
                userValue.setFocusableInTouchMode(true);

                showProfilePlaceholder();
                lockToolbar();
                // делаем невидимым наш текст ФИО
                collapsingToolbarLayout.setExpandedTitleColor(Color.TRANSPARENT);
            }
        } else {
            fab.setImageResource(R.drawable.ic_mode_edit_black_24dp);
            for (EditText userValue : userInfoViews) {
                userValue.setEnabled(false);
                userValue.setFocusable(false);
                userValue.setFocusableInTouchMode(false);

                hideProfilePlaceholder();
                unlockToolbar();
                // делаем цвет текста вновь видимым (ФИО)
                collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.white));

                saveUserInfoValue();
            }
        }
    }

    // отвечает за загрузку пользательских данных
    private void loadUserInfoValue() {
        List<String> userData = dataManager.getPreferenceManager().loadUserProfileData();
        for (int i = 0; i < userData.size(); i++) {
            userInfoViews.get(i).setText(userData.get(i));
        }
    }

    // отвечает за сохранение пользовательских данных
    private void saveUserInfoValue() {
        List<String> userData = new ArrayList<>();
        for (EditText userFieldView : userInfoViews) {
            userData.add(userFieldView.getText().toString());
        }
        dataManager.getPreferenceManager().saveUserProfileData(userData);
    }

    // метод для загрузки изображений из Галереи
    private void loadPhotoFromGallery() {

    }

    // метод для загрузки изображений с Камеры
    private void loadPhotoFromCamera() {

    }

    // скрывает панель действия выбора фотографии
    private void hideProfilePlaceholder() {
        profilePlaceholder.setVisibility(View.GONE);
    }

    // показывает панель действия выбора фотографии
    private void showProfilePlaceholder() {
        profilePlaceholder.setVisibility(View.VISIBLE);
    }

    // блокируем скрывание (scroll) во время редактирвоания
    private void lockToolbar() {
        appBarLayout.setExpanded(true, true);
        appBarParams.setScrollFlags(0);
        collapsingToolbarLayout.setLayoutParams(appBarParams);
    }

    // разблокируем scroll после редактирования
    private void unlockToolbar() {
        appBarParams.setScrollFlags(
                AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL |
                        AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED);
        collapsingToolbarLayout.setLayoutParams(appBarParams);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case ConstantManager.LOAD_PROFILE_PHOTO:
                String[] selectItems = {
                        getString(R.string.user_profile_dialog_gallery),
                        getString(R.string.user_profile_dialog_camera),
                        getString(R.string.user_profile_dialog_cancel)};
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(getString(R.string.user_profile_dialog_title));
                builder.setItems(selectItems, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int choiceItem) {
                        switch (choiceItem) {
                            case 0:
                                // TODO: загрузить из галереи
                                loadPhotoFromGallery();
                                showSnackbar("загрузить из галереи");
                                break;
                            case 1:
                                // TODO: загрузить из камеры
                                loadPhotoFromCamera();
                                showSnackbar("загрузить из камеры");
                                break;
                            case 2:
                                // TODO: отменить загрузку
                                dialog.cancel();
                                showSnackbar("отменить загрузку");
                                break;
                        }
                    }
                });
                return builder.create();

            default:
                return null;
        }
    }
}
