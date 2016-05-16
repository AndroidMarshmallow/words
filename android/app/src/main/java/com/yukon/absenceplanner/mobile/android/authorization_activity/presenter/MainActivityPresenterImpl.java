package com.yukon.absenceplanner.mobile.android.authorization_activity.presenter;

import android.text.TextUtils;

import com.yukon.absenceplanner.mobile.android.enteties.AbsenceTypes;
import com.yukon.absenceplanner.mobile.android.authorization_activity.model.AuthModel;
import com.yukon.absenceplanner.mobile.android.authorization_activity.model.AuthModelImpl;
import com.yukon.absenceplanner.mobile.android.authorization_activity.model.OnAuthFinished;
import com.yukon.absenceplanner.mobile.android.authorization_activity.view.AuthorizationView;

public class MainActivityPresenterImpl implements MainActivityPresenter, OnAuthFinished {

    private AuthModel mAuthModel;

    private AuthorizationView mMainView;

    public MainActivityPresenterImpl(AuthorizationView mainView){
        mAuthModel = new AuthModelImpl();
        mAuthModel.setCallback(this);
        mMainView = mainView;
    }

    @Override
    public void authin(String user, String pass) {
        if (!TextUtils.isEmpty(user)&& !TextUtils.isEmpty(pass)) {
            mAuthModel.authin(user,pass);
        } else {
            mMainView.onFailure();
        }
    }

    @Override
    public void onSuccess(AbsenceTypes types) {
        mMainView.onSussecc(types);
    }

    @Override
    public void onFailure() {
        mMainView.onFailure();
    }
}
