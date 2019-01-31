package com.omelchenkoaleks.skillbranch.data.managers;

import android.content.SharedPreferences;
import android.net.Uri;

import com.omelchenkoaleks.skillbranch.utils.ConstantManager;
import com.omelchenkoaleks.skillbranch.utils.SkillbranchApplication;

import java.net.PortUnreachableException;
import java.util.ArrayList;
import java.util.List;

// в этом классе будем инкапсулировать логику, связанную с работой SharedPreferences
public class PreferenceManager {
    private SharedPreferences sharedPreferences;

    private static final String[] USER_FIELDS = {
            ConstantManager.USER_PHONE_KEY,
            ConstantManager.USER_MAIL_KEY,
            ConstantManager.USER_VK_KEY,
            ConstantManager.USER_GIT_KEY,
            ConstantManager.USER_BIO_KEY};

    public PreferenceManager() {
        // извлеченные данные, которые загрузились при установке приложения кладем
        // в этот объект и работаем с ним:
        this.sharedPreferences = SkillbranchApplication.getSharedPreferences();
    }

    // метод будет сохранять пользовательские данные
    public void saveUserProfileData(List<String> userFields) {
        // в этой строчке мы получаем редактор, который вставляет данные в SharedPreferences:
        SharedPreferences.Editor editor = sharedPreferences.edit();

        for (int i = 0; i < USER_FIELDS.length; i++) {
            editor.putString(USER_FIELDS[i], userFields.get(i));
        }
        editor.apply();
    }

    public List<String> loadUserProfileData() {
        List<String> userFields = new ArrayList<>();
        userFields.add(sharedPreferences.getString(ConstantManager.USER_PHONE_KEY, "null"));
        userFields.add(sharedPreferences.getString(ConstantManager.USER_MAIL_KEY, "null"));
        userFields.add(sharedPreferences.getString(ConstantManager.USER_VK_KEY, "null"));
        userFields.add(sharedPreferences.getString(ConstantManager.USER_GIT_KEY, "null"));
        userFields.add(sharedPreferences.getString(ConstantManager.USER_BIO_KEY, "null"));
        return userFields;
    }

    // нужно сохранить ссылку на изображение, чтобы при запуске оно подгружалось
    public void saveUserPhoto(Uri uri) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(ConstantManager.USER_PHOTO_KEY, uri.toString());
        editor.apply();
    }

    //чтобы мы могли извлечь uri нашего сохраненного изображения
    public Uri loadUserPhoto() {
        return Uri.parse(sharedPreferences
                .getString(ConstantManager.USER_PHOTO_KEY,
                        "android.resource://com.omelchenkoaleks.skillbranch/drawable/oa"));
    }

    // этот метод нужен, чтобы подписывать наши исходящие запросы, чтобы сервер
    // был убежден, что это приходят запросы от какого-то авторизованного пользователя
    public void saveAuthToken(String authToken) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(ConstantManager.AUTH_TOKEN_KEY, authToken);
        editor.apply();
    }

    // метод для получения нашего токена
    public String getAuthToken() {
        return sharedPreferences.getString(ConstantManager.AUTH_TOKEN_KEY, "null");
    }

    // сохраняем идентификатор пользователя
    public void saveUserId(String userId) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(ConstantManager.USER_ID_KEY, userId);
        editor.apply();
    }

    public String getUserId() {
        return sharedPreferences.getString(ConstantManager.USER_ID_KEY, "null");
    }
}
