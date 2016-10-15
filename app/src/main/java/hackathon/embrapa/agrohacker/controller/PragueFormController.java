package hackathon.embrapa.agrohacker.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import hackathon.embrapa.agrohacker.R;
import hackathon.embrapa.agrohacker.dao.PragueDAO;
import hackathon.embrapa.agrohacker.helper.PragueFormHelper;
import hackathon.embrapa.agrohacker.model.Prague;

public class PragueFormController extends AppCompatActivity{

    private PragueFormHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prague_form);

        helper = new PragueFormHelper(this);

        receivePragueData();
    }

    private void receivePragueData() {
        Intent intent = getIntent();
        Prague prague = (Prague) intent.getSerializableExtra("pragueKey");
        if (prague != null) {
            helper.fillForm(prague);
        }
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
                Prague prague = helper.getAllPrague();
                PragueDAO dao = new PragueDAO(this);

                if(prague.getId() != null) {
                    dao.updatePrague(prague);
                } else {
                    dao.insertPrague(prague);
                }

                dao.close();
                Toast.makeText(PragueFormController.this, "Aluno " + prague.getPopularName() + " salvo!", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
