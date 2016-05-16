package com.yukon.absenceplanner.mobile.android.main_activity.add_absence_fragment_for_employee.model;

import com.yukon.absenceplanner.mobile.android.enteties.Absence;
import com.yukon.absenceplanner.mobile.android.enteties.AbsenceTypes;

/**
 * Created by ruslan on 01.04.2016.
 */
public interface OnLoadAddAbsenceFragmentModelFinished {
    void onSuccess(AbsenceTypes types, Absence absence);
    void onFailure();
}