package com.yukon.absenceplanner.mobile.android.authorization_activity.view;


import com.yukon.absenceplanner.mobile.android.enteties.AbsenceTypes;


public interface AuthorizationView {
   void onSussecc(AbsenceTypes absenceTypes);
    void onFailure();
}
