package hackathon.embrapa.agrohacker.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import hackathon.embrapa.agrohacker.R;

public class InspectionFormActivity extends AppCompatActivity {

    Button addPragueButton;
    Button addPredatorButton;
    ArrayList<String> pragues = new ArrayList<>();
    ArrayList<String> predators = new ArrayList<>();
    ArrayAdapter<String> predatorsAdapter;
    ArrayAdapter<String> praguesAdapter;
    ListView pragueList;
    ListView predatorList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspection_form);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        pragueList = (ListView) findViewById(R.id.lv_pragas);
        predatorList = (ListView) findViewById(R.id.lv_predadores);

        addPragueButton = (Button) findViewById(R.id.buttonAddPrague);
        addPredatorButton = (Button) findViewById(R.id.buttonAddPredator);
    }

    @Override
    public void onResume(){
        super.onResume();
        receivePragueData();
        receivePredatorData();

        Log.i("Tamanho praga", pragues.size()+"");
        Log.i("Tamanho predador", predators.size()+"");

        praguesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, pragues);
        predatorsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, predators);
    }

    private void receivePragueData() {
        Intent intent = getIntent();
        String prague = intent.getStringExtra("praga");
        Log.i("nomePraga", prague);
        pragues.add(prague);
    }

    private void receivePredatorData() {
        Intent intent = getIntent();
        String predator = intent.getStringExtra("predator");
        Log.i("NomePredador", predator);
        predators.add(predator);
    }

    public void addPrague(View view){
        Intent intent = new Intent(InspectionFormActivity.this, ChoicePragueActivity.class);
        startActivity(intent);
    }

    public void addPredator(View view) {
        Intent intent = new Intent(InspectionFormActivity.this, ChoicePredatorActivity.class);
        startActivity(intent);
    }

    public void confirmInspection(){
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_formulary, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_formulary_ok:
                confirmInspection();
                break;
            default:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
