package hackathon.embrapa.agrohacker.controller;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import hackathon.embrapa.agrohacker.R;

public class NotificationsActivity extends ListActivity {


    PlotController plotcontroller = new PlotController();

    ArrayList<String> listItems = new ArrayList<String>();

    ArrayAdapter<String> adapter;

    int clickCounter=0;
    Button updateButton;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_notifications);

        updateButton = (Button) findViewById(R.id.updateButton);

        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                listItems);
        setListAdapter(adapter);

        Toast.makeText(this, "Clique em atualizar para gerar o relatório!", Toast.LENGTH_LONG).show();
    }

    public void updateList(View view) {

        int worryingPlots = 0;

        if(plotcontroller.plots.size() == 0){

            listItems.add("Você não possui nenhum talhão cadastrado");
        }else {

            for (int i = 0; i < plotcontroller.plots.size(); i++) {
                listItems.add(i, plotcontroller.plots.get(i).getId() + "\n"
                        + plotcontroller.plots.get(i).getPlatationCulture() + "\n"
                        + plotcontroller.plots.get(i).getStatus() + "\n");

                if (plotcontroller.plots.get(i).getStatus() == "URGENTE") {
                    worryingPlots++;
                }
            }

            listItems.add("Você possui " + worryingPlots + "talhões em estado de Urgência");
        }
    }
}
