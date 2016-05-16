package com.yukon.absenceplanner.mobile.android.enteties;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ruslan on 25.04.2016.
 */
public class Logbook implements Serializable {

    @SerializedName("note")
    @Expose
    private String note;
    @SerializedName("byEmployee")
    @Expose
    private String byEmployee;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;
    @SerializedName("status")
    @Expose
    private String status;

    @Override
    public String toString() {
        return "Logbook{" +
                "note='" + note + '\'' +
                ", byEmployee='" + byEmployee + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    /**
     *
     * @return
     * The note
     */
    public String getNote() {
        return note;
    }

    /**
     *
     * @param note
     * The note
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     *
     * @return
     * The byEmployee
     */
    public String getByEmployee() {
        return byEmployee;
    }

    /**
     *
     * @param byEmployee
     * The byEmployee
     */
    public void setByEmployee(String byEmployee) {
        this.byEmployee = byEmployee;
    }

    /**
     *
     * @return
     * The timestamp
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     *
     * @param timestamp
     * The timestamp
     */
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    /**
     *
     * @return
     * The status
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status
     * The status
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
