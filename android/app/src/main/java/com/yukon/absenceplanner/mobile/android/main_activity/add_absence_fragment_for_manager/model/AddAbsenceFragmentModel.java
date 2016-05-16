package com.yukon.absenceplanner.mobile.android.main_activity.add_absence_fragment_for_manager.model;

/**
 * Created by ruslan on 01.04.2016.
 */
public interface AddAbsenceFragmentModel {
    void getAbsence();
    void setCallback(OnLoadAddAbsenceFragmentModelFinished
                             onLoadAddAbsenceFragmentModelFinished);
    void addAbsence(String spinner, String startDate, String endDate, String note);
}
