package net.etfbl.pisio.pms.pms;

/****************************************************************************
 * Copyright (c) 2016 Elektrotehnicki fakultet
 * Patre 5, Banja Luka
 * <p/>
 * All Rights Reserved
 * <p/>
 * \file net.etfbl.pisio.pms.pms Task
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
public class Task {
    private int Id;
    private String Description;
    private String Start;
    private String End;
    private String Deadline;
    private String Title;
    private String ManHour;
    private String PercentageDone;
    private String Hours;

    public Task(int id, String description, String start, String end, String deadline, String title, String manHour, String percentageDone, String hours) {
        Id = id;
        Description = description;
        Start = start;
        End = end;
        Deadline = deadline;
        Title = title;
        ManHour = manHour;
        PercentageDone = percentageDone;
        Hours = hours;
    }

    public Task() {
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

    public String getStart() {
        return Start;
    }

    public void setStart(String start) {
        Start = start;
    }

    public String getEnd() {
        return End;
    }

    public void setEnd(String end) {
        End = end;
    }

    public String getDeadline() {
        return Deadline;
    }

    public void setDeadline(String deadline) {
        Deadline = deadline;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getManHour() {
        return ManHour;
    }

    public void setManHour(String manHour) {
        ManHour = manHour;
    }

    public String getPercentageDone() {
        return PercentageDone;
    }

    public void setPercentageDone(String percentageDone) {
        PercentageDone = percentageDone;
    }

    public String getHours() {
        return Hours;
    }

    public void setHours(String hours) {
        Hours = hours;
    }
}
