package hackathon.embrapa.agrohacker.helper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.EditText;
import android.widget.ImageView;

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
    private final ImageView photoField;
    private Prague prague;

    public PragueFormHelper(PragueFormController activity) {
        popularNameField = (EditText) activity.findViewById(R.id.prague_popular_name_form);
        scientificNameField = (EditText) activity.findViewById(R.id.prague_scientific_name_form);
        cultureField = (EditText) activity.findViewById(R.id.prague_culture_form);
        atackPeriodField = (EditText) activity.findViewById(R.id.prague_atack_period_form);
        lifePeriodField = (EditText) activity.findViewById(R.id.prague_life_period_form);
        damageTypeField = (EditText) activity.findViewById(R.id.prague_damage_type_form);
        groupField = (EditText) activity.findViewById(R.id.prague_group_form);
        photoField = (ImageView) activity.findViewById(R.id.prague_form_photo);
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
        prague.setPhotoPath((String) photoField.getTag());
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
        imageLoading(prague.getPhotoPath());
        this.prague = prague;
    }

    public void imageLoading(String photoPath) {
        if (photoPath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(photoPath);
            Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bitmap, 300, 300, true);
            photoField.setImageBitmap(bitmapReduzido);
            photoField.setScaleType(ImageView.ScaleType.FIT_XY);
            photoField.setTag(photoPath);
        }
    }


}
