package hackathon.embrapa.agrohacker.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import hackathon.embrapa.agrohacker.model.NaturalPredator;
import hackathon.embrapa.agrohacker.model.Prague;

public class InspectionFormActivity extends AppCompatActivity {

    Button confirmButton;
    Button cancelButton;
    Button addPragueButton;
    Button addPredatorButton;

    InspectionPragueListViewController inspectionPragueListViewController;
    InspectionPredatorListViewController inspectionPredatorListViewController;

    ArrayAdapter<NaturalPredator> predatorsAdapter;
    ArrayAdapter<Prague> praguesAdapter;

    ListView pragueList;
    ListView predatorList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspection_form);

        pragueList = (ListView) findViewById(R.id.lv_pragas);
        predatorList = (ListView) findViewById(R.id.lv_predadores);

        confirmButton = (Button) findViewById(R.id.buttonConfirmFieldInspec);
        cancelButton = (Button) findViewById(R.id.buttonCancelFieldInspec);
        addPragueButton = (Button) findViewById(R.id.buttonAddPrague);
        addPredatorButton = (Button) findViewById(R.id.buttonAddPredator);
    }


    @Override
    public void onResume(){
        super.onResume();
        predatorsAdapter = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1, inspectionPredatorListViewController.predators);
        predatorList.setAdapter(predatorsAdapter);


        praguesAdapter = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1, inspectionPragueListViewController.pragues);
        pragueList.setAdapter(praguesAdapter);
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
                Toast.makeText(InspectionFormActivity.this, "Botão clicado!", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void addPrague(View view){
        Intent intent = new Intent(InspectionFormActivity.this, ChoicePragueActivity.class);
        startActivity(intent);
    }

    public void addPredator(View view) {
        Intent intent = new Intent(InspectionFormActivity.this, ChoicePredatorActivity.class);
        startActivity(intent);
    }

    public void confirmInspection(View view){
        Toast.makeText(this, "Em construção", Toast.LENGTH_SHORT).show();
    }

    public void cancelInspection(View view){
        Toast.makeText(this, "Em construção", Toast.LENGTH_SHORT).show();
    }
}
