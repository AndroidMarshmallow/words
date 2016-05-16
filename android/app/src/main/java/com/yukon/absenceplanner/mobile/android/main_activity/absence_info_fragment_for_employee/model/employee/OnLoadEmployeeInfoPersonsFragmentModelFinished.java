package com.yukon.absenceplanner.mobile.android.main_activity.absence_info_fragment_for_employee.model.employee;

import com.yukon.absenceplanner.mobile.android.enteties.Employees;

/**
 * Created by ruslan on 20.04.2016.
 */
public interface OnLoadEmployeeInfoPersonsFragmentModelFinished {
    void onSuccess(Employees employees);
    void onFailure();
}
