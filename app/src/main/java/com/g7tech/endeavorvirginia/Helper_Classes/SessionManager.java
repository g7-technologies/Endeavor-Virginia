package com.g7tech.endeavorvirginia.Helper_Classes;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SessionManager {

    SharedPreferences pref;
    Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "Endeavorginia";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    private static final String USER_ID = "user_id";
    private static final String F_NAME = "f_name";
    private static final String L_NAME = "l_name";
    private static final String EMAIL = "email";
    private static final String PROPERTY_ID = "property_id";

    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setLogin(boolean x) {
        editor.putBoolean(KEY_IS_LOGGED_IN, x);
        editor.commit();
    }

    public void setUserId(int x) {
        editor.putInt(USER_ID, x);
        editor.commit();
    }

    public void setfName(String x) {
        editor.putString(F_NAME, x);
        editor.commit();
    }

    public void setlName(String x) {
        editor.putString(L_NAME, x);
        editor.commit();
    }

    public void setEmail(String x) {

        editor.putString(EMAIL, x);
        editor.commit();
    }

    public void setPropertyId(int x) {

        editor.putInt(PROPERTY_ID, x);
        editor.commit();
    }
    public boolean isLoggedIn(){
        return pref.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    public int getUserId(){
        return pref.getInt(USER_ID, 0);
    }

    public String getfName(){
        return pref.getString(F_NAME, "");
    }

    public String getlName(){
        return pref.getString(L_NAME, "");
    }

    public String getEmail(){
        return pref.getString(EMAIL, "");
    }

    public int getPropertyId(){
        return pref.getInt(PROPERTY_ID, 0);
    }
}
