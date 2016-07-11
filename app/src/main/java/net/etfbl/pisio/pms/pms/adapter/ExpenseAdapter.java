package net.etfbl.pisio.pms.pms.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;

import net.etfbl.pisio.pms.pms.model.Expense;

import java.util.List;

/****************************************************************************
 * Copyright (c) 2016 Elektrotehnicki fakultet
 * Patre 5, Banja Luka
 * <p/>
 * All Rights Reserved
 * <p/>
 * \file net.etfbl.pisio.pms.pms.adapter ExpenseAdapter
 * \brief
 * This file contains a source code for class TaskAdapter
 * <p/>
 * Created on 11.07.2016
 *
 * @Author Milan Maric
 * <p/>
 * \notes
 * <p/>
 * <p/>
 * \history
 * <p/>
 **********************************************************************/
public class ExpenseAdapter extends ArrayAdapter<Expense> {
    public ExpenseAdapter(Context context, int resource) {
        super(context, resource);
    }

    public ExpenseAdapter(Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public ExpenseAdapter(Context context, int resource, Expense[] objects) {
        super(context, resource, objects);
    }

    public ExpenseAdapter(Context context, int resource, int textViewResourceId, Expense[] objects) {
        super(context, resource, textViewResourceId, objects);
    }

    public ExpenseAdapter(Context context, int resource, List<Expense> objects) {
        super(context, resource, objects);
    }

    public ExpenseAdapter(Context context, int resource, int textViewResourceId, List<Expense> objects) {
        super(context, resource, textViewResourceId, objects);
    }
}
