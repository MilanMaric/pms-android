package net.etfbl.pisio.pms.pms;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import net.etfbl.pisio.pms.pms.adapter.IncomeAdapter;
import net.etfbl.pisio.pms.pms.adapter.TaskAdapter;
import net.etfbl.pisio.pms.pms.model.Project;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import cz.msebera.android.httpclient.auth.AuthScope;
import cz.msebera.android.httpclient.client.CredentialsProvider;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.impl.client.BasicCredentialsProvider;
import cz.msebera.android.httpclient.impl.client.BasicResponseHandler;
import cz.msebera.android.httpclient.impl.client.HttpClientBuilder;

public class ProjectDetailActivity extends AppCompatActivity {
    private Project project;
    private ListView tasksListView;
    private TaskAdapter taskAdapter;
    private ProgressBar progressBar;
    private IncomeAdapter incomeAdapter;
    private ListView incomesListView;
    private View projectDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_detail);
        Intent intent = getIntent();
        project = (Project) intent.getSerializableExtra(MainActivity.PROJECT_TAG);
        taskAdapter = new TaskAdapter(this);
        incomeAdapter = new IncomeAdapter(this, android.R.layout.simple_list_item_1);
        tasksListView = (ListView) findViewById(R.id.projectDetailTasksList);
        tasksListView.setAdapter(taskAdapter);
        projectDetails = findViewById(R.id.projectDetails);
        incomesListView = (ListView) findViewById(R.id.incomesListView);
        incomesListView.setAdapter(incomeAdapter);
        incomesListView.setVisibility(View.GONE);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        setFields();
        TasksListAsyncTask asyncTask = new TasksListAsyncTask();
        asyncTask.execute();
        setButtonsOnClickListeners();
    }

    private void setAllToGone() {
        incomesListView.setVisibility(View.INVISIBLE);
        projectDetails.setVisibility(View.INVISIBLE);
        tasksListView.setVisibility(View.INVISIBLE);
    }

    private void setButtonsOnClickListeners() {
        Button btnProjectDetails = (Button) findViewById(R.id.btnProjectDetails);
        if (btnProjectDetails != null) {
            btnProjectDetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setAllToGone();
                    projectDetails.setVisibility(View.VISIBLE);
                }
            });
        }
        Button btnIncomes = (Button) findViewById(R.id.btnIncomes);
        if (btnIncomes != null) {
            btnIncomes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setAllToGone();
                    incomesListView.setVisibility(View.VISIBLE);
                }
            });
        }
        Button btnTasks = (Button) findViewById(R.id.btnTasks);
        if (btnTasks != null) {
            btnTasks.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setAllToGone();
                    tasksListView.setVisibility(View.VISIBLE);
                }
            });
        }
    }

    private void setFields() {
        TextView title = (TextView) findViewById(R.id.projectDetailTitle);
        if (title != null) {
            title.setText(project.getTitle());
        }
        TextView description = (TextView) findViewById(R.id.projectDetailDescription);
        if (description != null) {
            description.setText(project.getDescription());
        }
        TextView start = (TextView) findViewById(R.id.projectDetailStartDate);
        if (start != null) {
            start.setText(project.getStartDate());
        }
        TextView end = (TextView) findViewById(R.id.projectDetailEndDate);
        if (end != null) {
            end.setText(project.getEndDate());
        }
        TextView budget = (TextView) findViewById(R.id.projectDetailBudget);
        if (budget != null) {
            budget.setText(project.getBudget());
        }
    }

    public class TasksListAsyncTask extends AsyncTask<Void, Void, ProjectDetails> {

        @Override
        protected ProjectDetails doInBackground(Void... params) {
            String urlString = LoginActivity.PISIO_ETFBL_API_ROOT + "projects/" + project.getId();
            CredentialsProvider provider = new BasicCredentialsProvider();
            provider.setCredentials(AuthScope.ANY, LoginActivity.credentials);
            HttpClient httpClient = HttpClientBuilder.create().setDefaultCredentialsProvider(provider).build();
            HttpGet getMethod = new HttpGet(urlString);
            BasicResponseHandler handlerHC4 = new BasicResponseHandler();
            ProjectDetails projectDetails = null;
            String response = "";
            try {
                response = httpClient.execute(getMethod, handlerHC4);
                JSONObject o = new JSONObject(response);
                if (o.getBoolean("success")) {
                    JSONObject data = o.getJSONObject("data");
                    projectDetails = ProjectDAO.getFromJSON(data);
                }
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
            return projectDetails;
        }

        @Override
        protected void onPostExecute(ProjectDetails details) {
            taskAdapter.setList(details.getTasks());
            incomeAdapter.addAll(details.getIncomes());

            progressBar.setVisibility(View.GONE);
            setAllToGone();
        }
    }
}
