package com.omelchenkoaleks.skillbranch.data.managers;

import com.omelchenkoaleks.skillbranch.utils.SkillbranchApplication;

/**
 * Содержит единную точку общения с данными. Он нужен для того, чтобы
 * мы отдельно не ныряли в разные менеджеры. По сути он содержит get... and set...
 * - они будут нам возвращать то, что нам нужно. Логика будет в своих персональных
 * менеджерах, а здесь будет храниться ссылка на методы, которые будут возвращать
 * нам необходимые данные. (Синглтон)
 */
public class DataManager {
    private static DataManager INSTANCE = null;

    private PreferenceManager preferenceManager;

    public DataManager() {
        this.preferenceManager = new PreferenceManager();
    }

    public static DataManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DataManager();
        }
        return INSTANCE;
    }

    public PreferenceManager getPreferenceManager() {
        return preferenceManager;
    }
}
