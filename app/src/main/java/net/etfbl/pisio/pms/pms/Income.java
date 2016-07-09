package net.etfbl.pisio.pms.pms;

/****************************************************************************
 * Copyright (c) 2016 Elektrotehnicki fakultet
 * Patre 5, Banja Luka
 * <p/>
 * All Rights Reserved
 * <p/>
 * \file net.etfbl.pisio.pms.pms Income
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
public class Income {
    private int Id;
    private String description;
    private String Amount;
    private String date;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Income() {

    }

    public Income(int id, String description, String amount, String date) {
        Id = id;
        this.description = description;
        Amount = amount;
        this.date = date;
    }
}
