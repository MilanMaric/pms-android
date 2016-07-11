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

import net.etfbl.pisio.pms.pms.adapter.ExpenseAdapter;
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
    private ExpenseAdapter expenseAdapter;
    private ListView expensesListView;
    private Button btnProjectDetails;
    private Button btnIncomes;
    private Button btnTasks;
    private Button btnExpenses;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_detail);
        Intent intent = getIntent();
        project = (Project) intent.getSerializableExtra(MainActivity.PROJECT_TAG);
        taskAdapter = new TaskAdapter(this);
        incomeAdapter = new IncomeAdapter(this);
        expenseAdapter = new ExpenseAdapter(this);
        tasksListView = (ListView) findViewById(R.id.projectDetailTasksList);
        if (tasksListView != null) {
            tasksListView.setAdapter(taskAdapter);
        }
        projectDetails = findViewById(R.id.projectDetails);
        incomesListView = (ListView) findViewById(R.id.incomesListView);
        if (incomesListView != null) {
            incomesListView.setAdapter(incomeAdapter);
            incomesListView.setVisibility(View.INVISIBLE);
        }
        expensesListView = (ListView) findViewById(R.id.expensesListView);
        if (expensesListView != null) {
            expensesListView.setAdapter(expenseAdapter);
            expensesListView.setVisibility(View.INVISIBLE);
        }

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        if (progressBar != null) {
            progressBar.setVisibility(View.VISIBLE);
        }
        projectDetails.setVisibility(View.GONE);
        setFields();
        TasksListAsyncTask asyncTask = new TasksListAsyncTask();
        asyncTask.execute();
    }

    private void setAllToGone() {
        incomesListView.setVisibility(View.GONE);
        projectDetails.setVisibility(View.GONE);
        tasksListView.setVisibility(View.GONE);
        expensesListView.setVisibility(View.GONE);
    }

    private void setButtonsOnClickListeners() {
        btnProjectDetails = (Button) findViewById(R.id.btnProjectDetails);
        if (btnProjectDetails != null) {
            btnProjectDetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setAllToGone();
                    projectDetails.setVisibility(View.VISIBLE);
                }
            });
        }
        btnIncomes = (Button) findViewById(R.id.btnIncomes);
        if (btnIncomes != null) {
            btnIncomes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setAllToGone();
                    incomesListView.setVisibility(View.VISIBLE);
                }
            });
        }
        btnTasks = (Button) findViewById(R.id.btnTasks);
        if (btnTasks != null) {
            btnTasks.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setAllToGone();
                    tasksListView.setVisibility(View.VISIBLE);
                }
            });
        }
        btnExpenses = (Button) findViewById(R.id.btnExpenses);
        if (btnExpenses != null) {
            btnExpenses.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setAllToGone();
                    expensesListView.setVisibility(View.VISIBLE);
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
            if (details != null) {
                setButtonsOnClickListeners();
                if (details.getTasks() == null) {
                    btnTasks.setVisibility(View.GONE);
                } else {
                    taskAdapter.setList(details.getTasks());
                }
                if (details.getIncomes() == null) {
                    btnIncomes.setVisibility(View.GONE);
                } else {
                    incomeAdapter.setList(details.getIncomes());
                }
                if (details.getExpenses() == null) {
                    btnExpenses.setVisibility(View.GONE);
                } else {
                    expenseAdapter.setList(details.getExpenses());
                }
                progressBar.setVisibility(View.GONE);
                setAllToGone();
                projectDetails.setVisibility(View.VISIBLE);
            }
        }
    }
}
