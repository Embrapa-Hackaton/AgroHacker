package hackathon.embrapa.agrohacker.helper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import hackathon.embrapa.agrohacker.R;
import hackathon.embrapa.agrohacker.controller.PragueShowController;
import hackathon.embrapa.agrohacker.controller.PredatorShowController;
import hackathon.embrapa.agrohacker.model.NaturalPredator;
import hackathon.embrapa.agrohacker.model.Prague;

public class PredatorShowHelper {
    private final TextView popularNameField;
    private final TextView scientificNameField;
    private final TextView descriptionField;
    private final TextView importanceField;
    private final ImageView photoField;
    private NaturalPredator predator;

    public PredatorShowHelper(PredatorShowController activity) {
        popularNameField = (TextView) activity.findViewById(R.id.item_show_predator_name);
        scientificNameField = (TextView) activity.findViewById(R.id.item_show_predator_scientific_name);
        descriptionField = (TextView) activity.findViewById(R.id.item_show_predator_description);
        importanceField = (TextView) activity.findViewById(R.id.item_show_predator_importance);
        photoField = (ImageView) activity.findViewById(R.id.item_show_predator_foto);
        predator = new NaturalPredator();
    }

    public void fillForm(NaturalPredator predator) {
        popularNameField.setText(predator.getPopularName());
        scientificNameField.setText(predator.getScientificName());
        descriptionField.setText(predator.getDescription());
        importanceField.setText(predator.getImportance());
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
