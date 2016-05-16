package com.yukon.absenceplanner.mobile.android.main_activity.add_absence_fragment_for_employee.view;

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
import com.yukon.absenceplanner.mobile.android.main_activity.absence_factory_date.DateAndTime;
import com.yukon.absenceplanner.mobile.android.main_activity.absence_factory_date.DateFactory;
import com.yukon.absenceplanner.mobile.android.main_activity.add_absence_fragment_for_employee.presenter.AddAbsenceFragmentPresenter;
import com.yukon.absenceplanner.mobile.android.main_activity.add_absence_fragment_for_employee.presenter.AddAbsenceFragmentPresenterImpl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class AddAbsenceFragment extends Fragment implements  View.OnClickListener,AddAbsenceFragmentView {

    @BindView(R.id.spAbsenceType)
    Spinner spAbsenceType;

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

    @BindView(R.id.tvAbsenceTypeAfterClick)
    TextView tvAbsenceTypeAfterClick;

    public List<String> languages = new ArrayList();
    public String startDate,endDate;
    public Integer spinnerValue;
    private List<String> absenceList;

    AddAbsenceFragmentPresenter addAbsenceFragmentPresenter;

    public static Fragment newInstance() {
        return new AddAbsenceFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.add_absences, container, false);
        ButterKnife.bind(this,root);
        addAbsenceFragmentPresenter = new AddAbsenceFragmentPresenterImpl(this);
        addAbsenceFragmentPresenter.getAbsence();

        DateFactory dateFactory = new DateFactory();
        DateAndTime dateAndTime = dateFactory.getCurrentDateAndTime("Date");
        String DateTime = dateAndTime.getCurrent();
        dateAndTime = dateFactory.getCurrentDateAndTime("Time");
        DateTime += dateAndTime.getCurrent();

        startTimeTextEdit.setText(DateTime);
        startTime.setOnClickListener(this);
        endTime.setOnClickListener(this);
        addAbsence.setOnClickListener(this);
        tvAbsenceTypeBeforeClick.setOnClickListener(this);
        tvAbsenceTypeAfterClick.setOnClickListener(this);
        tvNoteForAddRequest.setOnClickListener(this);
        return root;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.addAbsence:

                addAbsenceFragmentPresenter.dateForAddNewAbsence(spinnerValue,startTimeTextEdit.getText().toString(), endTimeTextEdit.getText().toString(),inputDate.getText().toString());

                break;
            case R.id.tvAbsenceTypeBeforeClick:
                absenceTypeEditOpen();
                break;
            case R.id.tvAbsenceTypeAfterClick:
                absenceTypeEditClose();
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
            spAbsenceType.setAdapter(new SpinnerAdapterForAbsenceType(getActivity(),R.layout.custom_spinner,absenceList));
    }

    public void absenceTypeEditClose(){
        tvAbsenceTypeBeforeClick.setVisibility(View.VISIBLE);
        tvAbsenceTypeAfterClick.setVisibility(View.INVISIBLE);
        spAbsenceType.setAdapter(null);
    }

    @OnClick(R.id.startTimeTextView)
    public void startTimeClick(View view){

        new DateFactory().getDateAndTimePicker("startButton","DatePicker",getFragmentManager());

    }

    @OnClick(R.id.endTimeTextView)
    public void endTimeClick(View view){

        new DateFactory().getDateAndTimePicker("endButton","DatePicker",getFragmentManager());

    }


    @Override
    public void onSussecc(final AbsenceTypes absenceTypes ,Absence absence) {
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
