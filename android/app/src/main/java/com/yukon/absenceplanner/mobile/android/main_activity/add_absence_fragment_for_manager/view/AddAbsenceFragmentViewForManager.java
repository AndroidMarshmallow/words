package com.yukon.absenceplanner.mobile.android.main_activity.add_absence_fragment_for_manager.view;

import com.yukon.absenceplanner.mobile.android.enteties.Absence;
import com.yukon.absenceplanner.mobile.android.enteties.AbsenceTypes;

/**
 * Created by ruslan on 01.04.2016.
 */
public interface AddAbsenceFragmentViewForManager {
    void onSussecc(AbsenceTypes absenceTypes, Absence absence);
    void onFailure();
}
