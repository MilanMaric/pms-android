package net.etfbl.pisio.pms.pms;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String PROJECT_TAG = "project";
    private ListView listView;
    private ProjectAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        List<Project> list = (List<Project>) intent.getSerializableExtra(LoginActivity.PROJECTS_EXTRA_TAG);
        adapter = new ProjectAdapter(list, this);
        listView = (ListView) findViewById(R.id.projectList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Project project = (Project) adapter.getItem(position);
                Intent intent1 = new Intent(MainActivity.this, ProjectDetailActivity.class);
                intent1.putExtra(PROJECT_TAG,project);
                startActivity(intent1);
            }
        });
    }
}
