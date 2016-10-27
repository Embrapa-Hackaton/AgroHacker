package hackathon.embrapa.agrohacker.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import hackathon.embrapa.agrohacker.R;
import hackathon.embrapa.agrohacker.dao.NaturalPredatorDAO;
import hackathon.embrapa.agrohacker.model.NaturalPredator;

public class ChoicePredatorActivity extends AppCompatActivity {

    public ListView predatorList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        predatorList = (ListView) findViewById(R.id.list_view_DB);

        LoadingList();

        clickAddPrague();

    }

    private void LoadingList() {
        NaturalPredatorDAO dao = new NaturalPredatorDAO(this);
        ArrayList<NaturalPredator> predators = dao.showPredators();
        dao.close();
        ArrayAdapter<NaturalPredator> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, predators);
        predatorList.setAdapter(arrayAdapter);
    }

    private void clickAddPrague() {
        predatorList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int position, long id) {
                NaturalPredator predator = (NaturalPredator) predatorList.getItemAtPosition(position);
                //Intent intent = new Intent(ChoicePredatorActivity.this, InspectionFormActivity.class);
                //intent.putExtra("predatorKeyInspection", predator.toString());
                //startActivity(intent);
                Toast.makeText(ChoicePredatorActivity.this, "Em construção", Toast.LENGTH_SHORT).show();
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

