package com.yukon.absenceplanner.mobile.android.main_activity.absence_info_fragment_for_employee.view.cancel_alert_dialog;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.yukon.absenceplanner.mobile.android.R;
import com.yukon.absenceplanner.mobile.android.enteties.Absence;
import com.yukon.absenceplanner.mobile.android.main_activity.absence_info_fragment_for_employee.presenter.AbsenceInfoPersonsFragmentPresenter;
import com.yukon.absenceplanner.mobile.android.main_activity.absence_factory_date.DateFactory;

import java.text.ParseException;

/**
 * Created by ruslan on 26.04.2016.
 */
public class CancelDialogClass extends Dialog implements View.OnClickListener {

    private Button btDeleteRequest, btCancelDeleteRequest;
    private TextView tvLastModifaedBy,tvLastModifaed,tvStatusInCancel,tvPeriod,tvTypeInCancel;
    private Absence absence;
    private AbsenceInfoPersonsFragmentPresenter absenceInfoPersonsFragmentPresente;


    public CancelDialogClass(Activity activity, Absence absence,AbsenceInfoPersonsFragmentPresenter absenceInfoPersonsFragmentPresente) {
        super(activity);
        this.absence=absence;
        this.absenceInfoPersonsFragmentPresente=absenceInfoPersonsFragmentPresente;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.cancel_alert_dialog);
        btDeleteRequest = (Button) findViewById(R.id.btDeleteRequest);
        btDeleteRequest.setBackgroundColor(Color.rgb(255,255,255));
        btCancelDeleteRequest = (Button) findViewById(R.id.btCancelDeleteRequest);
        btCancelDeleteRequest.setBackgroundColor(Color.rgb(255,255,255));
        btDeleteRequest.setOnClickListener(this);
        btCancelDeleteRequest.setOnClickListener(this);

        tvLastModifaed=(TextView) findViewById(R.id.tvLastModifaed);
        try {
            tvLastModifaed.setText(DateFactory.getDateFormat(absence.getLogbooks().get(0).getTimestamp()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        tvLastModifaedBy=(TextView)findViewById(R.id.tvLastModifaedBy);
        tvLastModifaedBy.setText(absence.getLogbooks().get(0).getByEmployee());
        tvStatusInCancel=(TextView)findViewById(R.id.tvStatusInCancel);
        tvStatusInCancel.setText(absence.getLogbooks().get(0).getStatus());
        tvPeriod=(TextView)findViewById(R.id.tvPeriod);
        try {
            tvPeriod.setText(String.valueOf(DateFactory.getDateFormat(absence.getStartDateTime())+" - "+ DateFactory.getDateFormat(absence.getEndDateTime())));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        tvTypeInCancel=(TextView)findViewById(R.id.tvTypeInCancel);
        tvTypeInCancel.setText(absence.getAbsenceTypeName());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btDeleteRequest:
                absenceInfoPersonsFragmentPresente.canceledAbsence(absence.getId());
                btDeleteRequest.setBackgroundColor(Color.rgb(192,192,192));
                break;
            case R.id.btCancelDeleteRequest:
                btCancelDeleteRequest.setBackgroundColor(Color.rgb(192,192,192));
                v.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dismiss();
                    }
                },500);
                break;
            default:
                break;
        }
        dismiss();
    }
}
