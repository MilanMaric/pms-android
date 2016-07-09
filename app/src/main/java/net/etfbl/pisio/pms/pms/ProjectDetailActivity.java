package net.etfbl.pisio.pms.pms;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_detail);
        Intent intent = getIntent();
        project = (Project) intent.getSerializableExtra(MainActivity.PROJECT_TAG);

        taskAdapter = new TaskAdapter(this);
        tasksListView = (ListView) findViewById(R.id.projectDetailTasksList);
        tasksListView.setAdapter(taskAdapter);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        setFields();
        TasksListAsyncTask asyncTask = new TasksListAsyncTask();
        asyncTask.execute();
    }

    private void setFields() {
        TextView title = (TextView) findViewById(R.id.projectDetailTitle);
        title.setText(project.getTitle());
        TextView description = (TextView) findViewById(R.id.projectDetailDescription);
        description.setText(project.getDescription());
        TextView start = (TextView) findViewById(R.id.projectDetailStartDate);
        start.setText(project.getStartDate());
        TextView end = (TextView) findViewById(R.id.projectDetailEndDate);
        end.setText(project.getEndDate());
        TextView budget = (TextView) findViewById(R.id.projectDetailBudget);
        budget.setText(project.getBudget());
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
            progressBar.setVisibility(View.GONE);
        }
    }
}
