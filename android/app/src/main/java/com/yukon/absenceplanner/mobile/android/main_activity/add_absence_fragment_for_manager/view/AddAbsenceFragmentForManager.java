package com.yukon.absenceplanner.mobile.android.main_activity.add_absence_fragment_for_manager.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.yukon.absenceplanner.mobile.android.R;
import com.yukon.absenceplanner.mobile.android.enteties.Absence;
import com.yukon.absenceplanner.mobile.android.enteties.AbsenceType;
import com.yukon.absenceplanner.mobile.android.enteties.AbsenceTypes;
import com.yukon.absenceplanner.mobile.android.main_activity.absence_factory_date.DateFactory;
import com.yukon.absenceplanner.mobile.android.main_activity.add_absence_fragment_for_manager.presenter.AddAbsenceFragmentPresenter;
import com.yukon.absenceplanner.mobile.android.main_activity.add_absence_fragment_for_manager.presenter.AddAbsenceFragmentPresenterImpl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AddAbsenceFragmentForManager extends Fragment implements  View.OnClickListener,AddAbsenceFragmentViewForManager {

    @BindView(R.id.spAbsenceType)
    Spinner spAbsenceType;

    @BindView(R.id.spEmployee)
    Spinner spEmployee;

    @BindView(R.id.startTimeTextEdit)
    TextView startTimeTextEdit;

    @BindView(R.id.tvNoteForAddRequest)
    TextView tvNoteForAddRequest;

    @BindView(R.id.endTimeTextEdit)
    TextView endTimeTextEdit;

    @BindView(R.id.startTime)
    LinearLayout startTime;

    @BindView(R.id.endTime)
    LinearLayout endTime;

    @BindView(R.id.addAbsence)
    Button addAbsence;

    @BindView(R.id.inputDate)
    EditText inputDate;

    @BindView(R.id.tvAbsenceTypeBeforeClick)
    TextView tvAbsenceTypeBeforeClick;

    @BindView(R.id.tvEmployeeBeforeClick)
    TextView tvEmployeeBeforeClick;

    @BindView(R.id.tvEmployeeAfterClick)
    TextView tvEmployeeAfterClick;

    @BindView(R.id.tvAbsenceTypeAfterClick)
    TextView tvAbsenceTypeAfterClick;

    @BindView(R.id.llAbsenceTypeAfterClick)
    LinearLayout llAbsenceTypeAfterClick;

    @BindView(R.id.llEmployeeAfterClick)
    LinearLayout llEmployeeAfterClick;

    private int mYear, mMonth, mDay, mHour, mMinute;
    public List<String> languages = new ArrayList();
    public String startDate,endDate;
    public Integer spinnerValue;
    private List<String> absenceList;

    AddAbsenceFragmentPresenter addAbsenceFragmentPresenter;

    public static Fragment newInstance() {
        return new AddAbsenceFragmentForManager();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.add_absences_for_manager, container, false);
        ButterKnife.bind(this,root);
        addAbsenceFragmentPresenter = new AddAbsenceFragmentPresenterImpl(this);
        addAbsenceFragmentPresenter.getAbsence();
        startTime.setOnClickListener(this);
        endTime.setOnClickListener(this);
        addAbsence.setOnClickListener(this);
        tvAbsenceTypeBeforeClick.setOnClickListener(this);
        tvEmployeeBeforeClick.setOnClickListener(this);
        tvEmployeeAfterClick.setOnClickListener(this);
        tvAbsenceTypeAfterClick.setOnClickListener(this);
        tvNoteForAddRequest.setOnClickListener(this);
        return root;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.startTime:
                startDateTime();
                break;
            case R.id.endTime:
                endDateTime();
                break;
            case R.id.addAbsence:
                        addAbsenceFragmentPresenter.dateForAddNewAbsence(spinnerValue,startDate, endDate,inputDate.getText().toString());
                break;
            case R.id.tvAbsenceTypeBeforeClick:
                absenceTypeEditOpen();
                break;
            case R.id.tvAbsenceTypeAfterClick:
                absenceTypeEditClose();
                break;
            case R.id.tvEmployeeBeforeClick:
                employeeEditOpen();
                break;
            case R.id.tvEmployeeAfterClick:
                employeeEditClose();
                break;
            case R.id.tvNoteForAddRequest:
                noteForAddRequestShow();
                break;
        }
    }

    public void noteForAddRequestShow(){
            if(inputDate.getVisibility()==View.GONE){
                inputDate.setVisibility(View.VISIBLE);
                tvNoteForAddRequest.setTextSize(20);

            } else {
                inputDate.setVisibility(View.GONE);
                tvNoteForAddRequest.setTextSize(15);
            }

    }

    public void absenceTypeEditOpen(){
            tvAbsenceTypeBeforeClick.setVisibility(View.GONE);
            tvAbsenceTypeAfterClick.setVisibility(View.VISIBLE);
            llAbsenceTypeAfterClick.setVisibility(View.VISIBLE);
            spAbsenceType.setAdapter(new SpinnerAdapterForAbsenceType(getActivity(),R.layout.custom_spinner,absenceList));
    }

    public void absenceTypeEditClose(){
        tvAbsenceTypeBeforeClick.setVisibility(View.VISIBLE);
        tvAbsenceTypeAfterClick.setVisibility(View.GONE);
        spAbsenceType.setAdapter(null);
    }

    public void employeeEditOpen(){
        tvEmployeeBeforeClick.setVisibility(View.GONE);
        llEmployeeAfterClick.setVisibility(View.VISIBLE);
        tvEmployeeAfterClick.setVisibility(View.VISIBLE);
        spEmployee.setAdapter(new SpinnerAdapterForAbsenceType(getActivity(),R.layout.custom_spinner,absenceList));
    }

    public void employeeEditClose(){
        tvEmployeeBeforeClick.setVisibility(View.VISIBLE);
        tvEmployeeAfterClick.setVisibility(View.GONE);
        spEmployee.setAdapter(null);
    }

    public void endDateTime(){

        new DateFactory().getDateAndTimePicker("endButton","DatePicker",getFragmentManager());

    }

    public void startDateTime(){

        new DateFactory().getDateAndTimePicker("startButton","DatePicker",getFragmentManager());


    }



    @Override
    public void onSussecc(final AbsenceTypes absenceTypes , Absence absence) {
        for(AbsenceType i : absenceTypes.getTypes()) {
            languages.add(i.getNames().get(0).getValue());
        }

        absenceList = new ArrayList<>(languages);
        spAbsenceType.setOnItemSelectedListener(new  AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    spinnerValue =absenceTypes.getTypes().get(adapterView.getSelectedItemPosition()).getId();
                } catch (Exception e) {

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        if(absence.getId()!=null) {
            Toast toast = Toast.makeText(getContext(),
                    R.string.success, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    @Override
    public void onFailure() {

    }
}
