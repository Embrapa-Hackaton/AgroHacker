package hackathon.embrapa.agrohacker.helper;

import android.widget.EditText;

import hackathon.embrapa.agrohacker.R;
import hackathon.embrapa.agrohacker.controller.PragueFormController;
import hackathon.embrapa.agrohacker.model.Prague;

public class PragueFormHelper {
    private final EditText popularNameField;
    private final EditText scientificNameField;
    private final EditText cultureField;
    private final EditText atackPeriodField;
    private final EditText lifePeriodField;
    private final EditText damageTypeField;
    private final EditText groupField;
    private Prague prague;

    public PragueFormHelper(PragueFormController activity) {
        popularNameField = (EditText) activity.findViewById(R.id.popular_name_form);
        scientificNameField = (EditText) activity.findViewById(R.id.scientific_name_form);
        cultureField = (EditText) activity.findViewById(R.id.culture_form);
        atackPeriodField = (EditText) activity.findViewById(R.id.atack_period_form);
        lifePeriodField = (EditText) activity.findViewById(R.id.life_period_form);
        damageTypeField = (EditText) activity.findViewById(R.id.damage_type_form);
        groupField = (EditText) activity.findViewById(R.id.group_form);
        prague = new Prague();
    }

    public Prague getAllPrague() {
        prague.setPopularName(popularNameField.getText().toString());
        prague.setScientificName(scientificNameField.getText().toString());
        prague.setCulture(cultureField.getText().toString());
        prague.setAtackPeriod(atackPeriodField.getText().toString());
        prague.setLifePeriod(lifePeriodField.getText().toString());
        prague.setDamageType(damageTypeField.getText().toString());
        prague.setGroup(groupField.getText().toString());
        return prague;
    }

    public void fillForm(Prague prague) {
        popularNameField.setText(prague.getPopularName());
        scientificNameField.setText(prague.getScientificName());
        cultureField.setText(prague.getCulture());
        atackPeriodField.setText(prague.getAtackPeriod());
        lifePeriodField.setText(prague.getLifePeriod());
        damageTypeField.setText(prague.getDamageType());
        groupField.setText(prague.getGroup());
        this.prague = prague;
    }
}
