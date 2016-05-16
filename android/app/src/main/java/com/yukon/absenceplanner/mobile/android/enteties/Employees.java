package com.yukon.absenceplanner.mobile.android.enteties;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ruslan on 20.04.2016.
 */
public class Employees  implements Serializable {

    @SerializedName("employees")
    @Expose
    private List<Employee> employees = new ArrayList<Employee>();

    @Override
    public String toString() {
        return "Employees{" +
                "employees=" + employees +
                '}';
    }

    /**
     *
     * @return
     * The employees
     */
    public List<Employee> getEmployees() {
        return employees;
    }

    /**
     *
     * @param employees
     * The employees
     */
    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
