package com.yukon.absenceplanner.mobile.android.main_activity.absence_info_fragment_for_manager.model;

import com.yukon.absenceplanner.mobile.android.enteties.Absences;

/**
 * Created by ruslan on 01.04.2016.
 */
public interface OnLoadAbsensInfoPersonsFragmentModelFinished {
    void onSuccess(Absences absences);
    void onFailure();
}
