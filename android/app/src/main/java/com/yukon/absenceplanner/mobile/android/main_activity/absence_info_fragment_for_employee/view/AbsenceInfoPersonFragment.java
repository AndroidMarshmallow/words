package com.yukon.absenceplanner.mobile.android.main_activity.absence_info_fragment_for_employee.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.malinskiy.superrecyclerview.OnMoreListener;
import com.malinskiy.superrecyclerview.SuperRecyclerView;
import com.yukon.absenceplanner.mobile.android.R;
import com.yukon.absenceplanner.mobile.android.enteties.Absence;
import com.yukon.absenceplanner.mobile.android.enteties.Absences;
import com.yukon.absenceplanner.mobile.android.enteties.Employees;
import com.yukon.absenceplanner.mobile.android.main_activity.absence_info_fragment_for_employee.presenter.AbsenceInfoPersonsFragmentPresenter;
import com.yukon.absenceplanner.mobile.android.main_activity.absence_info_fragment_for_employee.presenter.AbsenceInfoPersonsFragmentPresenterImpl;
import com.yukon.absenceplanner.mobile.android.main_activity.absence_info_fragment_for_employee.view.cancel_alert_dialog.CancelDialogClass;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AbsenceInfoPersonFragment extends android.support.v4.app.Fragment implements AbsensInfoPersonFragmentView,
        OnItemClickListener,OnLinerLayoutClickListener, OnMoreListener {

    @BindView(R.id.recyclerView)
    SuperRecyclerView superRecyclerView;

    @Nullable
    @BindView(R.id.linerLayoutForInfo)
    LinearLayout linerLayoutForInfo;

    private String timestamp="";

    private List<Absence> listAbsence =new ArrayList<>();

    private List<Absence> listForCancelAbsence;

    private AbsenceInfoPersonAdapter mRecyclerViewAdapter;

    private AbsenceInfoPersonsFragmentPresenter absenceInfoPersonsFragmentPresente;

    public static android.support.v4.app.Fragment newInstance() {
        return new AbsenceInfoPersonFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.absence_info,container,false);
        ButterKnife.bind(this, root);
        superRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        superRecyclerView.getRecyclerView().setHasFixedSize(true);
        superRecyclerView.setOnMoreListener(this);
        absenceInfoPersonsFragmentPresente = new AbsenceInfoPersonsFragmentPresenterImpl(this);
        mRecyclerViewAdapter = new AbsenceInfoPersonAdapter(null, this, this,getContext());
        superRecyclerView.setAdapter(mRecyclerViewAdapter);
        absenceInfoPersonsFragmentPresente.getAbsence(timestamp);
        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onSuccessForAbsences(Absences absences ) {
        try {
            if (absences.getAbsences().toString().equals("[]")) {
                superRecyclerView.hideMoreProgress();
            } else {
                mRecyclerViewAdapter.refreshAbsenceInfo(absences.getAbsences());
                timestamp = absences.getTimestamp();
                listAbsence.addAll(absences.getAbsences());
            }
        } catch (Exception e) {}
        if(absences==null) {
            mRecyclerViewAdapter = new AbsenceInfoPersonAdapter(listAbsence, this, this,getContext());
            superRecyclerView.setAdapter(mRecyclerViewAdapter);
            Toast toast = Toast.makeText(getActivity(), R.string.successfullyDeleted, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    @Override
    public void onSuccessForEmployees(Employees employees) {

    }

    @Override
    public void onFailure() {
    }

    @Override
    public void onItemClick(Absence absence) {
        listForCancelAbsence=new ArrayList<>();
        for(Absence i: listAbsence) {
            if(i.getId()!=absence.getId())
            listForCancelAbsence.add(i);
        }
        listAbsence=listForCancelAbsence;
        CancelDialogClass cancelDialogClass = new CancelDialogClass(getActivity(),
                absence,absenceInfoPersonsFragmentPresente);
        cancelDialogClass.show();
    }

    @Override
    public void onLayoutClick(View v) {
    }

    @Override
    public void onMoreAsked(int overallItemsCount, int itemsBeforeMore,
                            int maxLastVisiblePosition) {
        superRecyclerView.setLoadingMore(true);
        absenceInfoPersonsFragmentPresente.getAbsence(timestamp);
    }
}