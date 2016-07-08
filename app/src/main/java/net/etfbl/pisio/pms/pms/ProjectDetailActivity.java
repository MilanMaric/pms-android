package net.etfbl.pisio.pms.pms;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_detail);
        Intent intent = getIntent();
        project = (Project) intent.getSerializableExtra(MainActivity.PROJECT_TAG);
        TextView title = (TextView) findViewById(R.id.projectDetailTitle);
        title.setText(project.getTitle());
        taskAdapter = new TaskAdapter(this);
        tasksListView = (ListView) findViewById(R.id.projectDetailTasksList);
        TextView description = (TextView) findViewById(R.id.projectDetailDescription);
        description.setText(project.getDescription());
    }

    public class TasksListAsyncTask extends AsyncTask<Void, Void, List<Task>> {

        @Override
        protected List<Task> doInBackground(Void... params) {
            String urlString = LoginActivity.PISIO_ETFBL_API_ROOT + "projects/" + project.getId();
            CredentialsProvider provider = new BasicCredentialsProvider();
            provider.setCredentials(AuthScope.ANY, LoginActivity.credentials);
            HttpClient httpClient = HttpClientBuilder.create().setDefaultCredentialsProvider(provider).build();
            HttpGet getMethod = new HttpGet(urlString);
            BasicResponseHandler handlerHC4 = new BasicResponseHandler();
            List<Task> tasksList = null;
            String response = "";
            try {
                response = httpClient.execute(getMethod, handlerHC4);
                JSONObject o = new JSONObject(response);
                if (o.getBoolean("success")) {
                    JSONObject data = o.getJSONObject("data");
                    JSONArray tasksArray = data.getJSONArray("tasks");
                    tasksList = TaskDAO.getFromJSON(tasksArray);
                }
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
            return tasksList;
        }

        @Override
        protected void onPostExecute(List<Task> tasks) {

        }
    }
}
