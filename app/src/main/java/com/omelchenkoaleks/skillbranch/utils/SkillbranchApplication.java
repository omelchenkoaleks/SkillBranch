package com.omelchenkoaleks.skillbranch.utils;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SkillbranchApplication extends Application {
    public static SharedPreferences sharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();

        // этой строчкой мы извлекаем данные из SharedPreferences:
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
    }

    public static SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }
}
