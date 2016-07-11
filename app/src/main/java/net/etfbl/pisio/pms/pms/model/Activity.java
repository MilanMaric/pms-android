package net.etfbl.pisio.pms.pms.model;

/****************************************************************************
 * Copyright (c) 2016 Elektrotehnicki fakultet
 * Patre 5, Banja Luka
 * <p/>
 * All Rights Reserved
 * <p/>
 * \file net.etfbl.pisio.pms.pms Activity
 * \brief
 * This file contains a source code for class TaskAdapter
 * <p/>
 * Created on 08.07.2016
 *
 * @Author Milan Maric
 * <p/>
 * \notes
 * <p/>
 * <p/>
 * \history
 * <p/>
 **********************************************************************/
public class Activity {
    private int Id;
    private String Description;
    private String Date;
    private String hours;

    public Activity(int id, String description, String date, String hours) {
        Id = id;
        Description = description;
        Date = date;
        this.hours = hours;
    }

    public Activity() {

    }

    @Override
    public String toString() {
        return "Description: " + Description + "\nDate: " + Date + "\nhours: " + hours + "\n";
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }
}
