package hackathon.embrapa.agrohacker.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import hackathon.embrapa.agrohacker.R;
import hackathon.embrapa.agrohacker.adapter.PredatorAdapter;
import hackathon.embrapa.agrohacker.dao.NaturalPredatorDAO;
import hackathon.embrapa.agrohacker.dao.PragueDAO;
import hackathon.embrapa.agrohacker.model.NaturalPredator;
import hackathon.embrapa.agrohacker.model.Prague;

public class ChoicePredatorActivity extends AppCompatActivity {

    NaturalPredatorDAO predatorDAO = new NaturalPredatorDAO(this);
    InspectionFormController inspectionFormController = new InspectionFormController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_predator);

        ListView predatorsOnDBLV = (ListView) findViewById(R.id.lv_predators_DB);
        final ArrayList<NaturalPredator> naturalPredators = (ArrayList<NaturalPredator>) predatorDAO.showPredators();
        ArrayAdapter<NaturalPredator> arrayAdapter = new ArrayAdapter<NaturalPredator>(this, android.R.layout.simple_list_item_1, naturalPredators);
        predatorsOnDBLV.setAdapter(arrayAdapter);

        predatorsOnDBLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                inspectionFormController.setNaturalPredators(naturalPredators.get(position));
                getIntent().putExtra("predators", naturalPredators.get(position).toString());
                ChoicePredatorActivity.super.onBackPressed();
            }
        });
    }
}
