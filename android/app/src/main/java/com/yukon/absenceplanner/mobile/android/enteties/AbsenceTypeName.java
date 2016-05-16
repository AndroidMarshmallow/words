package com.yukon.absenceplanner.mobile.android.enteties;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AbsenceTypeName implements Serializable {

    @Override
    public String toString() {
        return "AbsenceTypeName{" +
                "value='" + value + '\'' +
                ", language='" + language + '\'' +
                '}';
    }

    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("language")
    @Expose
    private String language;

    /**
     *
     * @return
     * The value
     */
    public String getValue() {
        return value;
    }

    /**
     *
     * @param value
     * The value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     *
     * @return
     * The language
     */
    public String getLanguage() {
        return language;
    }

    /**
     *
     * @param language
     * The language
     */
    public void setLanguage(String language) {
        this.language = language;
    }

}
