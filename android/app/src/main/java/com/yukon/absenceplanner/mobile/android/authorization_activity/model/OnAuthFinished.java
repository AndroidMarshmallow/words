package com.yukon.absenceplanner.mobile.android.authorization_activity.model;

import com.yukon.absenceplanner.mobile.android.enteties.AbsenceTypes;

public interface OnAuthFinished {
    void onSuccess(AbsenceTypes types);
    void onFailure();
}
