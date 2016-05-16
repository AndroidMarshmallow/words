package com.yukon.absenceplanner.mobile.android.main_activity.add_absence_fragment_for_manager.presenter;

import com.yukon.absenceplanner.mobile.android.enteties.Absence;
import com.yukon.absenceplanner.mobile.android.enteties.AbsenceTypes;
import com.yukon.absenceplanner.mobile.android.enteties.Employees;
import com.yukon.absenceplanner.mobile.android.main_activity.add_absence_fragment_for_manager.model.AddAbsenceFragmentModel;
import com.yukon.absenceplanner.mobile.android.main_activity.add_absence_fragment_for_manager.model.AddAbsenceFragmentModelImpl;
import com.yukon.absenceplanner.mobile.android.main_activity.add_absence_fragment_for_manager.model.OnLoadAddAbsenceFragmentModelFinished;
import com.yukon.absenceplanner.mobile.android.main_activity.add_absence_fragment_for_manager.view.AddAbsenceFragmentViewForManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ruslan on 01.04.2016.
 */
public class AddAbsenceFragmentPresenterImpl implements AddAbsenceFragmentPresenter,OnLoadAddAbsenceFragmentModelFinished{

    private AddAbsenceFragmentModel maddAbsenceFragmentModel;

    private AddAbsenceFragmentViewForManager maddAbsenceFragmentView;
    private Employees employees;

    public AddAbsenceFragmentPresenterImpl(AddAbsenceFragmentViewForManager addAbsenceFragmentView){
        maddAbsenceFragmentModel= new AddAbsenceFragmentModelImpl();
        maddAbsenceFragmentModel.setCallback(this);
        maddAbsenceFragmentView=addAbsenceFragmentView;
    }

    @Override
    public void getAbsence() {
        maddAbsenceFragmentModel.getAbsence();
    }

    @Override
    public void dateForAddNewAbsence(Integer value, String startDate, String endDate, String note) {
        maddAbsenceFragmentModel.addAbsence(String.valueOf(value),startDate,endDate,note);
    }



    @Override
    public void onSuccess(AbsenceTypes absenceTypes , Absence absence) {
        maddAbsenceFragmentView.onSussecc(absenceTypes,absence);
    }

    @Override
    public void onFailure() {
        maddAbsenceFragmentView.onFailure();
    }
}
