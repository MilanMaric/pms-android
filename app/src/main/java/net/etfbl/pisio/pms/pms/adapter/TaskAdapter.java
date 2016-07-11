package net.etfbl.pisio.pms.pms.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.etfbl.pisio.pms.pms.R;
import net.etfbl.pisio.pms.pms.model.Task;

import java.util.ArrayList;
import java.util.List;

/****************************************************************************
 * Copyright (c) 2016 Elektrotehnicki fakultet
 * Patre 5, Banja Luka
 * <p/>
 * All Rights Reserved
 * <p/>
 * \file net.etfbl.pisio.pms.pms TaskAdapter
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
public class TaskAdapter extends BaseAdapter {
    List<Task> list = new ArrayList<>();
    private Context mContext;

    public TaskAdapter(Context mContext) {

        this.mContext = mContext;
    }

    public void setList(List<Task> list) {
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
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.tasks_list_row, null);

            ViewHolder holder = new ViewHolder();
            holder.mDescription = (TextView) view.findViewById(R.id.taskListDescription);
            holder.mStart = (TextView) view.findViewById(R.id.taskListStart);
            holder.mEnd = (TextView) view.findViewById(R.id.taskListEnd);
            holder.mDeadline = (TextView) view.findViewById(R.id.taskListDeadline);
            holder.mTitle = (TextView) view.findViewById(R.id.taskListTitle);
            holder.mManHour = (TextView) view.findViewById(R.id.taskListManHour);
            holder.mPercentageDone = (TextView) view.findViewById(R.id.taskListPercentageDone);
            holder.mHours = (TextView) view.findViewById(R.id.taskListHours);
            holder.mListView = (LinearLayout) view.findViewById(R.id.taskListActivityListView);
            view.setTag(holder);
        }
        Task task = (Task) getItem(position);
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.mTitle.setText(task.getTitle());
        holder.mDescription.setText(task.getDescription());
        holder.mStart.setText(task.getStart());
        holder.mEnd.setText(task.getEnd());
        holder.mDeadline.setText(task.getDeadline());
        holder.mPercentageDone.setText(task.getPercentageDone());
        holder.mHours.setText(task.getHours());
        for (int i = 0; i < task.getActivities().size(); i++) {
            TextView textView = new TextView(mContext);
            textView.setText(task.getActivities().get(i).toString());
            holder.mListView.addView(textView);
        }
//        arrayAdapter.notifyDataSetChanged();
        return view;
    }

    private class ViewHolder {
        public TextView mDescription = null;
        public TextView mStart = null;
        public TextView mEnd = null;
        public TextView mDeadline = null;
        public TextView mTitle = null;
        public TextView mManHour = null;
        public TextView mPercentageDone = null;
        public TextView mHours = null;
        public LinearLayout mListView = null;
    }
}
