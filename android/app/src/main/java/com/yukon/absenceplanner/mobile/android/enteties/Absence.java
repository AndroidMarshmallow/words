package com.yukon.absenceplanner.mobile.android.enteties;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Absence implements Serializable{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("startDateTime")
    @Expose
    private String startDateTime;
    @SerializedName("endDateTime")
    @Expose
    private String endDateTime;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;
    @SerializedName("absenceTypeName")
    @Expose
    private String absenceTypeName;
    @SerializedName("createdForEmployee")
    @Expose
    private String createdForEmployee;
    @SerializedName("createdByEmployee")
    @Expose
    private String createdByEmployee;
    @SerializedName("canCancel")
    @Expose
    private String canCancel;
    @SerializedName("logbooks")
    @Expose
    private List<Logbook> logbooks = new ArrayList<Logbook>();

    @Override
    public String toString() {
        return "Absence{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", startDateTime='" + startDateTime + '\'' +
                ", endDateTime='" + endDateTime + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", absenceTypeName=" + absenceTypeName +
                ", createdForEmployee='" + createdForEmployee + '\'' +
                ", createdByEmployee='" + createdByEmployee + '\'' +
                ", canCancel=" + canCancel +
                ", logbooks=" + logbooks +
                '}';
    }

    /**
     *
     * @return
     * The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(Integer id) {
        this.id = id;
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

    /**
     *
     * @return
     * The startDateTime
     */
    public String getStartDateTime() {
        return startDateTime;
    }

    /**
     *
     * @param startDateTime
     * The startDateTime
     */
    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    /**
     *
     * @return
     * The endDateTime
     */
    public String getEndDateTime() {
        return endDateTime;
    }

    /**
     *
     * @param endDateTime
     * The endDateTime
     */
    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
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
     * The absenceTypeName
     */
    public String getAbsenceTypeName() {
        return absenceTypeName;
    }

    /**
     *
     * @param absenceTypeName
     * The absenceTypeId
     */
    public void setAbsenceTypeName(String absenceTypeName) {
        this.absenceTypeName = absenceTypeName;
    }

    /**
     *
     * @return
     * The createdForEmployee
     */
    public String getCreatedForEmployee() {
        return createdForEmployee;
    }

    /**
     *
     * @param createdForEmployee
     * The createdForEmployee
     */
    public void setCreatedForEmployee(String createdForEmployee) {
        this.createdForEmployee = createdForEmployee;
    }

    /**
     *
     * @return
     * The createdByEmployee
     */
    public String getCreatedByEmployee() {
        return createdByEmployee;
    }

    /**
     *
     * @param createdByEmployee
     * The createdByEmployee
     */
    public void setCreatedByEmployee(String createdByEmployee) {
        this.createdByEmployee = createdByEmployee;
    }

    /**
     *
     * @return
     * The canCancel
     */
    public String getCanCancel() {
        return canCancel;
    }

    /**
     *
     * @param canCancel
     * The canCancel
     */
    public void setCanCancel(String canCancel) {
        this.canCancel = canCancel;
    }

    /**
     *
     * @return
     * The logbooks
     */
    public List<Logbook> getLogbooks() {
        return logbooks;
    }

    /**
     *
     * @param logbooks
     * The logbooks
     */
    public void setLogbooks(List<Logbook> logbooks) {
        this.logbooks = logbooks;
    }


    public Absence(Integer id,String status, String startDateTime, String endDateTime, String timestamp, String absenceTypeName, String createdForEmployee,  String createdByEmployee, String canCancel){
        this.absenceTypeName = absenceTypeName;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.createdForEmployee = createdForEmployee;
        this.createdByEmployee =createdByEmployee;
        this.status =status;
        this.id = id;
        this.timestamp= timestamp;
        this.canCancel = canCancel;
    }

    public Absence(String absenceTypeName, String startDateTime, String endDateTime){
        this.absenceTypeName = absenceTypeName;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }
}