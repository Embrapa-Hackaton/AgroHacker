package hackathon.embrapa.agrohacker.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import hackathon.embrapa.agrohacker.dao.*;

import hackathon.embrapa.agrohacker.R;
import hackathon.embrapa.agrohacker.model.Prague;

public class ChoicePragueActivity extends AppCompatActivity {

    public ListView pragueList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        pragueList = (ListView) findViewById(R.id.list_view_DB);

        LoadingList();

        clickAddPrague();

    }

    private void LoadingList() {
        PragueDAO dao = new PragueDAO(this);
        ArrayList<Prague> pragues = dao.showPragues();
        dao.close();
        ArrayAdapter<Prague> arrayAdapter = new ArrayAdapter<Prague>(this, android.R.layout.simple_list_item_1, pragues);
        pragueList.setAdapter(arrayAdapter);
    }

    private void clickAddPrague() {
        pragueList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int position, long id) {
                Prague prague = (Prague) pragueList.getItemAtPosition(position);
                Intent intent = getIntent();
                intent.putExtra("praga", prague.getPopularName());
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_back, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

}
