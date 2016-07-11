package net.etfbl.pisio.pms.pms.model;

import android.util.Log;

/****************************************************************************
 * Copyright (c) 2016 Elektrotehnicki fakultet
 * Patre 5, Banja Luka
 * <p/>
 * All Rights Reserved
 * <p/>
 * \file net.etfbl.pisio.pms.pms Expense
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
public class Expense {
    public static final String TAG = "Expense";
    private String Description;
    private String Amount;
    private String Date;

    public Expense() {

    }

    public Expense(String description, String amount, String date) {
        this.Description = description;
        Amount = amount;
        this.Date = date;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        this.Date = date;
    }

    @Override
    public String toString() {
        Log.d(TAG,"toString");
        return "Description: " + Description + " \n" + "Amount: " + getAmount() + "\n" + "Date: " + Date;
    }
}
