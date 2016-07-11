package net.etfbl.pisio.pms.pms.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import net.etfbl.pisio.pms.pms.R;
import net.etfbl.pisio.pms.pms.model.Project;

import java.util.List;

/****************************************************************************
 * Copyright (c) 2016 Elektrotehnicki fakultet
 * Patre 5, Banja Luka
 * <p/>
 * All Rights Reserved
 * <p/>
 * \file net.etfbl.pisio.pms.pms ProjectAdapter
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
public class ProjectAdapter extends BaseAdapter {
    private List<Project> projectList;
    private Context mContext;

    public ProjectAdapter(List<Project> projectList, Context mContext) {
        this.projectList = projectList;
        this.mContext = mContext;
    }

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }

    @Override
    public int getCount() {
        return projectList.size();
    }

    @Override
    public Object getItem(int position) {
        return projectList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return projectList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.project_list_item, null);
        }
        Project project = (Project) getItem(position);
        TextView title = (TextView) view.findViewById(R.id.projectListItemTitle);
        TextView description = (TextView) view.findViewById(R.id.projectListItemDescription);
        title.setText(project.getTitle());
        description.setText(project.getDescription());
        return view;
    }
}
