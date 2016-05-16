package com.yukon.absenceplanner.mobile.android.main_activity.absence_info_fragment_for_manager.model.employee;

/**
 * Created by ruslan on 20.04.2016.
 */
public interface EmployeeInfoPersonFragmentModel {
    void getEmployees();
    void setCallback(OnLoadEmployeeInfoPersonsFragmentModelFinished
                             onLoadEmployeeInfoPersonsFragmentModelFinished);
}
