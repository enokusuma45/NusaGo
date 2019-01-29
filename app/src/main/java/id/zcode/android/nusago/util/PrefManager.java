package id.zcode.android.nusago.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.google.gson.Gson;

public class PrefManager {
    private static SharedPreferences sharedPreferences;
    private static PrefManager instance;

    public static PrefManager getInstance(Context context) {
        if (instance == null) {
            instance = new PrefManager();
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        }
        return instance;
    }

    public void putString(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public String getString(String key, String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }

    public <T> void putCustom(String key, T value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, new Gson().toJson(value));
        editor.commit();
    }

    public <T> T getCustom(String key, Class<T> typeOf) {
        String s = sharedPreferences.getString(key, null);
        if (s == null) return null;
        return new Gson().fromJson(s, typeOf);
    }

    public boolean cleanUp(){
        return sharedPreferences.edit().clear().commit();
    }

}
