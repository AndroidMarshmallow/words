package com.yukon.absenceplanner.mobile.android.main_activity.absence_info_fragment_for_manager.presenter;

/**
 * Created by ruslan on 01.04.2016.
 */
public interface AbsenceInfoPersonsFragmentPresenter {
    void getAbsence(String timestamp);
    void getEmployee();
    void canceledAbsence(int valueId);
}
