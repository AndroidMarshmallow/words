package com.yukon.absenceplanner.mobile.android.enteties;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ruslan on 20.04.2016.
 */
public class Employee implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("infix")
    @Expose
    private Object infix;
    @SerializedName("extReference")
    @Expose
    private Object extReference;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("language")
    @Expose
    private String language;


    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", infix=" + infix +
                ", extReference=" + extReference +
                ", role='" + role + '\'' +
                ", language='" + language + '\'' +
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
     * The lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     *
     * @param lastName
     * The lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     *
     * @return
     * The firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *
     * @param firstName
     * The firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *
     * @return
     * The infix
     */
    public Object getInfix() {
        return infix;
    }

    /**
     *
     * @param infix
     * The infix
     */
    public void setInfix(Object infix) {
        this.infix = infix;
    }

    /**
     *
     * @return
     * The extReference
     */
    public Object getExtReference() {
        return extReference;
    }

    /**
     *
     * @param extReference
     * The extReference
     */
    public void setExtReference(Object extReference) {
        this.extReference = extReference;
    }

    /**
     *
     * @return
     * The role
     */
    public String getRole() {
        return role;
    }

    /**
     *
     * @param role
     * The role
     */
    public void setRole(String role) {
        this.role = role;
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