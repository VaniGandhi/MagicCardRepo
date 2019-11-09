package com.example.magiccards;

import android.content.Context;
import android.content.SharedPreferences;

public class UserSharedPrefernce {
    private  String TITLE="title";
    private String TYPE="type";
    private  String NUMBER="number";
    private String CARD="card";
    SharedPreferences sharedPreferences;
    static UserSharedPrefernce userSharedPrefernce = null;

    Context context;

    public UserSharedPrefernce() {

    }

    public UserSharedPrefernce(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

    }

    public static UserSharedPrefernce

    getInstance() {
        if (userSharedPrefernce == null) {
            userSharedPrefernce = new UserSharedPrefernce(BaseApplication.getContext());
        }
        return userSharedPrefernce;
    }
    public void setTITLE(String title) {
        sharedPreferences.edit().putString(TITLE,title).commit();
    }

    public String getTITLE() {
        return sharedPreferences.getString(TITLE, "");
    }
    public void setTYPE(int type) {
        sharedPreferences.edit().putInt(TYPE,type).commit();
    }

    public int getTYPE()
    {
        return  sharedPreferences.getInt(TYPE,0);
    }
    public void setNUMBER(String number) {
        sharedPreferences.edit().putString(NUMBER,number).commit();
    }

    public String getNUMBER()
    {
        return  sharedPreferences.getString(NUMBER,"");
    }
    public void setCARD(String card) {
        sharedPreferences.edit().putString(CARD,card).commit();
    }

    public String getCARD() {
        return sharedPreferences.getString(CARD, "");
    }
}
