package hackathon.embrapa.agrohacker.helper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.EditText;
import android.widget.ImageView;

import hackathon.embrapa.agrohacker.R;
import hackathon.embrapa.agrohacker.controller.PragueFormController;
import hackathon.embrapa.agrohacker.controller.PredatorFormController;
import hackathon.embrapa.agrohacker.model.NaturalPredator;
import hackathon.embrapa.agrohacker.model.Prague;

public class PredatorFormHelper {
    private final EditText popularNameField;
    private final EditText scientificNameField;
    private final EditText cultureField;
    private final EditText lifePeriodField;
    private final EditText groupField;
    private final ImageView photoField;
    private NaturalPredator predator;

    public PredatorFormHelper(PredatorFormController activity) {
        popularNameField = (EditText) activity.findViewById(R.id.predator_popular_name_form);
        scientificNameField = (EditText) activity.findViewById(R.id.predator_scientific_name_form);
        cultureField = (EditText) activity.findViewById(R.id.predator_culture_form);
        lifePeriodField = (EditText) activity.findViewById(R.id.predator_life_period_form);
        groupField = (EditText) activity.findViewById(R.id.predator_group_form);
        photoField = (ImageView) activity.findViewById(R.id.predator_form_photo);
        predator = new NaturalPredator();
    }

    public NaturalPredator getAllPredator() {
        predator.setPopularName(popularNameField.getText().toString());
        predator.setScientificName(scientificNameField.getText().toString());
        predator.setCulture(cultureField.getText().toString());
        predator.setLifePeriod(lifePeriodField.getText().toString());
        predator.setGroup(groupField.getText().toString());
        predator.setPhotoPath((String) photoField.getTag());
        return predator;
    }

    public void fillForm(NaturalPredator predator) {
        popularNameField.setText(predator.getPopularName());
        scientificNameField.setText(predator.getScientificName());
        cultureField.setText(predator.getCulture());
        lifePeriodField.setText(predator.getLifePeriod());
        groupField.setText(predator.getGroup());
        imageLoading(predator.getPhotoPath());
        this.predator = predator;
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
