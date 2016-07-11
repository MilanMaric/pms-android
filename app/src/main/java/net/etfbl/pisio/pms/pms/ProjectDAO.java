package net.etfbl.pisio.pms.pms;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.etfbl.pisio.pms.pms.model.Project;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/****************************************************************************
 * Copyright (c) 2016 Elektrotehnicki fakultet
 * Patre 5, Banja Luka
 * <p/>
 * All Rights Reserved
 * <p/>
 * \file net.etfbl.pisio.pms.pms ProjectDAO
 * \brief
 * This file contains a source code for class TaskAdapter
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
public class ProjectDAO {

    public static List<Project> getFromJSON(JSONArray array) throws JSONException {
        List<Project> list = new ArrayList<>();
        Gson gson = new Gson();
        Type type = new TypeToken<Project>() {
        }.getType();
        for (int i = 0; i < array.length(); i++) {
            JSONObject object = array.getJSONObject(i);

            Project project = gson.fromJson(object.toString(), type);
            list.add(project);
        }
        return list;
    }

    public static ProjectDetails getFromJSON(JSONObject object) {
        ProjectDetails projectDetails;
        Gson gson = new Gson();
        Type type = new TypeToken<ProjectDetails>() {
        }.getType();

        return (ProjectDetails) gson.fromJson(object.toString(), type);

    }
}
