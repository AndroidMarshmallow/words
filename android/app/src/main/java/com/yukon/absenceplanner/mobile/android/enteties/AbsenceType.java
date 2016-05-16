package com.yukon.absenceplanner.mobile.android.enteties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AbsenceType implements Serializable {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("shortName")
    @Expose
    private String shortName;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("availabilityLevel")
    @Expose
    private String availabilityLevel;
    @SerializedName("names")
    @Expose
    private List<AbsenceTypeName> names = new ArrayList<AbsenceTypeName>();

    @Override
    public String toString() {
        return "Type_{" +
                "id=" + id +
                ", shortName='" + shortName + '\'' +
                ", category='" + category + '\'' +
                ", availabilityLevel='" + availabilityLevel + '\'' +
                ", names=" + names +
                '}';
    }

    /**
     *
     * @return
     * The id
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The shortName
     */
    public String getShortName() {
        return shortName;
    }

    /**
     *
     * @param shortName
     * The shortName
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    /**
     *
     * @return
     * The category
     */
    public String getCategory() {
        return category;
    }

    /**
     *
     * @param category
     * The category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     *
     * @return
     * The availabilityLevel
     */
    public String getAvailabilityLevel() {
        return availabilityLevel;
    }

    /**
     *
     * @param availabilityLevel
     * The availabilityLevel
     */
    public void setAvailabilityLevel(String availabilityLevel) {
        this.availabilityLevel = availabilityLevel;
    }

    /**
     *
     * @return
     * The names
     */
    public List<AbsenceTypeName> getNames() {
        return names;
    }

    /**
     *
     * @param names
     * The names
     */
    public void setNames(List<AbsenceTypeName> names) {
        this.names = names;
    }

}
