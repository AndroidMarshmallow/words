package com.yukon.absenceplanner.mobile.android.main_activity.absence_info_fragment_for_manager.view;

import com.yukon.absenceplanner.mobile.android.enteties.Absences;
import com.yukon.absenceplanner.mobile.android.enteties.Employees;

/**
 * Created by ruslan on 01.04.2016.
 */
public interface AbsensInfoFragmentViewForManager {
    void onSuccessForAbsences(Absences absences);
    void onSuccessForEmployees(Employees employees);
    void onFailure();
}
