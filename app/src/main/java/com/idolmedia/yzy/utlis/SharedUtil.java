package com.idolmedia.yzy.utlis;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

public class SharedUtil {

    public static SharedPreferences mPreference;
    private static Context mContext;

    public static void init(Context context) {
        mContext = context;
    }

    public static void clear() {
        if (mPreference == null)
            mPreference = PreferenceManager.getDefaultSharedPreferences(mContext);
        mPreference.edit().clear();
    }

    public static SharedPreferences getPreference() {
        if (mPreference == null)
            mPreference = PreferenceManager.getDefaultSharedPreferences(mContext);
        return mPreference;
    }

    public static void setInteger(String name, int value) {
        getPreference().edit().putInt(name, value).commit();
    }

    public static int getInteger(String name, int default_i) {
        return getPreference().getInt(name, default_i);
    }

    public static void setString(String name, String value) {
        getPreference().edit().putString(name, value).commit();
    }

    public static String getString(String name) {
        return getPreference().getString(name, null);
    }

    public static String getString(String name, String defalt) {
        return getPreference().getString(name, defalt);
    }

    /**
     * 获取boolean类型的配置
     *
     * @param name
     * @param defaultValue
     * @return
     */
    public static boolean getBoolean(String name, boolean defaultValue) {
        return getPreference().getBoolean(name, defaultValue);
    }

    /**
     * 设置boolean类型的配置
     *
     * @param
     * @param name
     * @param value
     */
    public static void setBoolean(String name, boolean value) {
        getPreference().edit().putBoolean(name, value).commit();
    }

    public static Long getLong(String name, long defaultValue) {
        return getPreference().getLong(name, defaultValue);
    }

    public static void setLong(String name, long value) {
        getPreference().edit().putLong(name, value).commit();
    }

    public static void setMoreService(String name, String value) {
        getPreference().edit().putString(name, value).commit();
    }

    public static String getMoreService(String name, String defaultValue) {
        return getPreference().getString(name, defaultValue);
    }

    // 通过键删除值
    public static void isValue(String name) {
        getPreference().edit().remove(name);
    }

    public static void saveObjecToString(String key, Object value) {
        String strJson = "";
        Gson gson = new Gson();
        strJson = gson.toJson(value);
        setString(key, strJson);
    }

    public static Object getObjectFromJsonString(String key, Class clazz) {
        String jsonString = getString(key, "");
        Gson gson= new Gson();
        return  gson.fromJson(jsonString,clazz);
    }



}
