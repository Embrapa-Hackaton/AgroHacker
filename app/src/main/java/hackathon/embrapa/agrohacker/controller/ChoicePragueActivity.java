package hackathon.embrapa.agrohacker.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import hackathon.embrapa.agrohacker.dao.*;

import hackathon.embrapa.agrohacker.R;
import hackathon.embrapa.agrohacker.model.Prague;

public class ChoicePragueActivity extends AppCompatActivity {

    PragueDAO pragueDAO = new PragueDAO(this);
    InspectionFormActivity inspectionFormActivity = new InspectionFormActivity();
    InspectionPragueListViewController inspectionPragueListViewController =
            new InspectionPragueListViewController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_prague);

        ListView praguesOnDBLV = (ListView) findViewById(R.id.lv_pragues_DB);
        final ArrayList<Prague> pragues = (ArrayList<Prague>) pragueDAO.showPragues();
        ArrayAdapter<Prague> arrayAdapter = new ArrayAdapter<Prague>(this, android.R.layout.simple_list_item_1, pragues);
        praguesOnDBLV.setAdapter(arrayAdapter);

        praguesOnDBLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                inspectionPragueListViewController.setPragues(pragues.get(position));
                getIntent().putExtra("pragues",pragues.get(position).toString() );
                ChoicePragueActivity.super.onBackPressed();
            }
        });
    }
}
