package com.yukon.absenceplanner.mobile.android.main_activity.absence_info_fragment_for_manager.presenter;

import com.yukon.absenceplanner.mobile.android.enteties.Absences;
import com.yukon.absenceplanner.mobile.android.enteties.Employees;
import com.yukon.absenceplanner.mobile.android.main_activity.absence_info_fragment_for_manager.model.AbsensInfoPersonsFragmentModel;
import com.yukon.absenceplanner.mobile.android.main_activity.absence_info_fragment_for_manager.model.AbsensInfoPersonsFragmentModelImpl;
import com.yukon.absenceplanner.mobile.android.main_activity.absence_info_fragment_for_manager.model.OnLoadAbsensInfoPersonsFragmentModelFinished;
import com.yukon.absenceplanner.mobile.android.main_activity.absence_info_fragment_for_manager.model.employee.EmployeeInfoPersonFragmentModel;
import com.yukon.absenceplanner.mobile.android.main_activity.absence_info_fragment_for_manager.model.employee.EmployeeInfoPersonsFragmentModelImpl;
import com.yukon.absenceplanner.mobile.android.main_activity.absence_info_fragment_for_manager.model.employee.OnLoadEmployeeInfoPersonsFragmentModelFinished;
import com.yukon.absenceplanner.mobile.android.main_activity.absence_info_fragment_for_manager.view.AbsensInfoFragmentViewForManager;

/**
 * Created by ruslan on 01.04.2016.
 */
public class AbsenceInfoPersonsFragmentPresenterImpl implements AbsenceInfoPersonsFragmentPresenter,
        OnLoadAbsensInfoPersonsFragmentModelFinished,OnLoadEmployeeInfoPersonsFragmentModelFinished {

    private AbsensInfoPersonsFragmentModel mabsensInfoPersonsFragmentModel;

    private EmployeeInfoPersonFragmentModel employeeInfoPersonFragmentModel;

    private AbsensInfoFragmentViewForManager mabsensInfoPersonFragmentView;

    public AbsenceInfoPersonsFragmentPresenterImpl(AbsensInfoFragmentViewForManager absensInfoPersonFragmentView){
        employeeInfoPersonFragmentModel = new EmployeeInfoPersonsFragmentModelImpl();
        employeeInfoPersonFragmentModel.setCallback(this);
        mabsensInfoPersonsFragmentModel= new AbsensInfoPersonsFragmentModelImpl();
        mabsensInfoPersonsFragmentModel.setCallback(this);
        mabsensInfoPersonFragmentView=absensInfoPersonFragmentView;
    }

    @Override
    public void getAbsence(String timestamp){
            mabsensInfoPersonsFragmentModel.getAbsence(timestamp);
    }

    @Override
    public void getEmployee() {
        employeeInfoPersonFragmentModel.getEmployees();
    }

    @Override
    public void canceledAbsence(int valueId) {
        mabsensInfoPersonsFragmentModel.canceledAbsence(valueId);
    }

    @Override
    public void onSuccess(Absences absences){
        mabsensInfoPersonFragmentView.onSuccessForAbsences(absences);
    }

    @Override
    public void onSuccess(Employees employees) {

        mabsensInfoPersonFragmentView.onSuccessForEmployees(employees);
    }

    @Override
    public void onFailure() {
        mabsensInfoPersonFragmentView.onFailure();
    }
}
