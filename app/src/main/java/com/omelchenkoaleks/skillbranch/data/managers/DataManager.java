package com.omelchenkoaleks.skillbranch.data.managers;

import android.content.Context;

import com.omelchenkoaleks.skillbranch.data.network.RestService;
import com.omelchenkoaleks.skillbranch.data.network.ServiceGenerator;
import com.omelchenkoaleks.skillbranch.data.network.req.UserLoginReq;
import com.omelchenkoaleks.skillbranch.data.network.res.UserModelRes;
import com.omelchenkoaleks.skillbranch.utils.SkillbranchApplication;

import retrofit2.Call;

/**
 * Содержит единную точку общения с данными. Он нужен для того, чтобы
 * мы отдельно не ныряли в разные менеджеры. По сути он содержит get... and set...
 * - они будут нам возвращать то, что нам нужно. Логика будет в своих персональных
 * менеджерах, а здесь будет храниться ссылка на методы, которые будут возвращать
 * нам необходимые данные. (Синглтон)
 */
public class DataManager {
    private static DataManager INSTANCE = null;

    private Context context;
    private PreferenceManager preferenceManager;
    private RestService restService;

    public DataManager() {
        this.preferenceManager = new PreferenceManager();
        this.context = SkillbranchApplication.getContext();
        this.restService = ServiceGenerator.createService(RestService.class);
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

    public Context getContext() {
        return context;
    }

    public Call<UserModelRes> loginUser(UserLoginReq userLoginReq){
        return restService.loginUser(userLoginReq);
    }
}
