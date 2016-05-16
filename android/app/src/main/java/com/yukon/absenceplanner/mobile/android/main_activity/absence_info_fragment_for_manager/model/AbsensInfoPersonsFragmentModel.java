package com.yukon.absenceplanner.mobile.android.main_activity.absence_info_fragment_for_manager.model;

/**
 * Created by ruslan on 01.04.2016.
 */
public interface AbsensInfoPersonsFragmentModel {
    void getAbsence(String timestamp);
    void setCallback(OnLoadAbsensInfoPersonsFragmentModelFinished
                             onLoadAbsensInfoPersonsFragmentModelFinished);
    void canceledAbsence(int valueId);
}
