package net.etfbl.pisio.pms.pms.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;

import net.etfbl.pisio.pms.pms.model.Income;

import java.util.List;

/****************************************************************************
 * Copyright (c) 2016 Elektrotehnicki fakultet
 * Patre 5, Banja Luka
 * <p/>
 * All Rights Reserved
 * <p/>
 * \file net.etfbl.pisio.pms.pms.adapter IncomeAdapter
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
public class IncomeAdapter extends ArrayAdapter<Income> {
    public IncomeAdapter(Context context, int resource) {
        super(context, resource);
    }

    public IncomeAdapter(Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public IncomeAdapter(Context context, int resource, Income[] objects) {
        super(context, resource, objects);
    }

    public IncomeAdapter(Context context, int resource, int textViewResourceId, Income[] objects) {
        super(context, resource, textViewResourceId, objects);
    }

    public IncomeAdapter(Context context, int resource, List<Income> objects) {
        super(context, resource, objects);
    }

    public IncomeAdapter(Context context, int resource, int textViewResourceId, List<Income> objects) {
        super(context, resource, textViewResourceId, objects);
    }
}
