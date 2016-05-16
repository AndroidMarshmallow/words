package com.yukon.absenceplanner.mobile.android.enteties;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Absences implements Serializable {
    @Override
    public String toString() {
        return "Absences{" +
                "ab=" + absences.toString() +
                '}';
    }
    @SerializedName("timestamp")
    @Expose
    private String timestamp;

    @SerializedName("absences")
    @Expose
    private List<Absence> absences = new ArrayList<Absence>();



    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public List<Absence> getAbsences() {
        return absences;
    }

    public void setAbsences(List<Absence> absences) {
        this.absences = absences;
    }
}
