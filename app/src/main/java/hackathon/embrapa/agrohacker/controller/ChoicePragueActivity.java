package hackathon.embrapa.agrohacker.controller;

import android.content.Intent;
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

    public ListView pragueList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

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
                Intent intent = new Intent(ChoicePragueActivity.this, InspectionFormActivity.class);
                intent.putExtra("pragueKeyInspection", prague.toString());
                startActivity(intent);
                finish();
            }
        });
    }

}
