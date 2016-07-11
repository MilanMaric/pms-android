package net.etfbl.pisio.pms.pms.model;

import java.io.Serializable;

/****************************************************************************
 * Copyright (c) 2016 Elektrotehnicki fakultet
 * Patre 5, Banja Luka
 * <p/>
 * All Rights Reserved
 * <p/>
 * \file net.etfbl.pisio.pms.pms Project
 * \brief
 * This file contains a source code for class Project
 * <p/>
 * Created on 07.07.2016
 *
 * @Author Milan Maric
 * <p/>
 * \notes
 * <p/>
 * <p/>
 * \history
 * <p/>
 **********************************************************************/
public class Project implements Serializable{

    private int Id;
    private String Title;
    private String StartDate;
    private String EndDate;
    private String Description;
    private String Budget;

    public Project(int id, String title, String startDate, String endDate, String description, String budget) {
        Id = id;
        Title = title;
        StartDate = startDate;
        EndDate = endDate;
        Description = description;
        Budget = budget;
    }

    public Project() {

    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String endDate) {
        EndDate = endDate;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getBudget() {
        return Budget;
    }

    public void setBudget(String budget) {
        Budget = budget;
    }
}
