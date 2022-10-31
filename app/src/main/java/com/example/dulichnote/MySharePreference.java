package com.example.dulichnote;

import android.content.Context;
import android.content.SharedPreferences;

public class MySharePreference {

    private static final String SHARE_PREF_KEY = "SHARE_PREF_KEY";
    private static MySharePreference instance;


    public static MySharePreference getInstance() {
        if (instance==null){
            instance = new MySharePreference();

        }
        return instance;

    }


    private MySharePreference(){
       //for singleton
    }

    public void saveStringValue(String key, String value){
        SharedPreferences sharedPreferences = App.getInstance().getSharedPreferences(SHARE_PREF_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public String getStringValue(String key){
        SharedPreferences sharedPreferences = App.getInstance().getSharedPreferences(SHARE_PREF_KEY, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key,null);
    }
}
