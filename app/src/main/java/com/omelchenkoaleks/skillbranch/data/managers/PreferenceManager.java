package com.omelchenkoaleks.skillbranch.data.managers;

import android.content.SharedPreferences;

import com.omelchenkoaleks.skillbranch.utils.ConstantManager;
import com.omelchenkoaleks.skillbranch.utils.SkillbranchApplication;

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
}
