package com.yukon.absenceplanner.mobile.android.authorization_activity.model;

public interface AuthModel {
    void authin(String user, String pass);
    void setCallback(OnAuthFinished onAuthFinished);

}
