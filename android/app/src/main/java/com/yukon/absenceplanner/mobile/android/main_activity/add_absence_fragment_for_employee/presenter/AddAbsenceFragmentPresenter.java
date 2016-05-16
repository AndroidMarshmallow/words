package com.yukon.absenceplanner.mobile.android.main_activity.add_absence_fragment_for_employee.presenter;

/**
 * Created by ruslan on 01.04.2016.
 */
public interface AddAbsenceFragmentPresenter {
    void getAbsence();
    void dateForAddNewAbsence(Integer value, String startDate, String endDate, String note);
}
