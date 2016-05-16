package com.yukon.absenceplanner.mobile.android.enteties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class AbsenceTypes implements Serializable {
    @Override
    public String toString() {
        return "AbsenceTypes{" +
                "types=" + listAbsenceType.toString() +
                '}';
    }

    @SerializedName("types")
    @Expose
    private List<AbsenceType> listAbsenceType = new ArrayList<AbsenceType>();

    /**
     *
     * @return
     * The types
     */
    public List<AbsenceType> getTypes() {
        return listAbsenceType;
    }

    /**
     *
     * @param types
     * The types
     */
    public void setTypes(List<AbsenceType> types) {
        this.listAbsenceType = types;
    }

}
