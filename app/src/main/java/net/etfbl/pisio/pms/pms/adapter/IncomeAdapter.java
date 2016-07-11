package net.etfbl.pisio.pms.pms.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import net.etfbl.pisio.pms.pms.R;
import net.etfbl.pisio.pms.pms.model.Income;

import java.util.ArrayList;
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
public class IncomeAdapter extends BaseAdapter {
    private List<Income> list = new ArrayList<>();
    private Context context;

    public IncomeAdapter(Context context) {

        this.context = context;
    }

    public List<Income> getList() {
        return list;
    }

    public void setList(List<Income> list) {
        if (list == null)
            this.list = new ArrayList<>();
        else
            this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.income_expense_row, null);
        }
        TextView textView = (TextView) view.findViewById(R.id.textHolder);
        textView.setText(getItem(position).toString());
        return view;
    }
}
