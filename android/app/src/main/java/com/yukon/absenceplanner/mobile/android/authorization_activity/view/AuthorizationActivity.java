package com.yukon.absenceplanner.mobile.android.authorization_activity.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.yukon.absenceplanner.mobile.android.R;
import com.yukon.absenceplanner.mobile.android.enteties.AbsenceType;
import com.yukon.absenceplanner.mobile.android.enteties.AbsenceTypes;
import com.yukon.absenceplanner.mobile.android.main_activity.MainActivity;
import com.yukon.absenceplanner.mobile.android.authorization_activity.presenter.MainActivityPresenter;
import com.yukon.absenceplanner.mobile.android.authorization_activity.presenter.MainActivityPresenterImpl;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AuthorizationActivity extends AppCompatActivity implements View.OnClickListener,AuthorizationView {

    @BindView(R.id.buttonConfirm)
    Button buttonConfirm;

    @BindView(R.id.userName)
    EditText userName;

    @BindView(R.id.userPass)
    EditText userPass;

    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;

    private MainActivityPresenter mMainActivityPresenter;

    public static List<AbsenceType> sAbsencesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        ButterKnife.bind(this);

        if( getIntent().getBooleanExtra("Exit me", false)){
            finish();
        }
        mMainActivityPresenter = new MainActivityPresenterImpl(this);
        buttonConfirm.setOnClickListener(this);
    }

    @Override
    public void onSussecc(AbsenceTypes absenceTypes) {
        sAbsencesList = absenceTypes.getTypes();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onFailure() {
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, R.string.invalidCreditenals, Snackbar.LENGTH_LONG);
        snackbar.show();

    }

    @Override
    public void onClick(View view) {
        if(view==buttonConfirm){
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        String userName = this.userName.getText().toString();
        String userPass = this.userPass.getText().toString();
        mMainActivityPresenter.authin(userName, userPass);
        submitForm();
    }

    private void submitForm() {
        if (!validateName()) {
            return;
        }
        if (!validatePassword()) {
            return;
        }
    }

    private boolean validateName() {
        if (userName.getText().toString().trim().isEmpty()) {
            userName.setError(getString(R.string.errorUserName));
            requestFocus(userName);
            return false;
        }

        return true;
    }

    private boolean validatePassword() {
        if (userPass.getText().toString().trim().isEmpty()) {
            userPass.setError(getString(R.string.errorPassName));
            requestFocus(userPass);
            return false;
        }

        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        getWindow().setSoftInputMode( WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

    }
}