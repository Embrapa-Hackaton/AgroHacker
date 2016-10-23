package hackathon.embrapa.agrohacker.helper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
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
    private final EditText descriptionField;
    private final EditText importanceField;
    private final ImageView photoField;
    private NaturalPredator predator;

    public PredatorFormHelper(PredatorFormController activity) {
        popularNameField = (EditText) activity.findViewById(R.id.predator_popular_name_form);
        scientificNameField = (EditText) activity.findViewById(R.id.predator_scientific_name_form);
        descriptionField = (EditText) activity.findViewById(R.id.predator_description_form);
        importanceField = (EditText) activity.findViewById(R.id.predator_importance_form);
        photoField = (ImageView) activity.findViewById(R.id.predator_form_photo);
        predator = new NaturalPredator();
    }

    public NaturalPredator getAllPredator() {
        predator.setPopularName(popularNameField.getText().toString());
        predator.setScientificName(scientificNameField.getText().toString());
        predator.setDescription(descriptionField.getText().toString());
        predator.setImportance(importanceField.getText().toString());
        predator.setPhotoPath((String) photoField.getTag());
        return predator;
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
            bitmapReduzido = getRoundCornerBitmap(bitmapReduzido, 10);
            photoField.setImageBitmap(bitmapReduzido);
            photoField.setScaleType(ImageView.ScaleType.FIT_XY);
            photoField.setTag(photoPath);
        }
    }

    public static Bitmap getRoundCornerBitmap(Bitmap bitmap, int radius) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Bitmap output = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        final RectF rectF = new RectF(0, 0, w, h);

        canvas.drawRoundRect(rectF, radius, radius, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, null, rectF, paint);

        final Rect clipRect = new Rect(0, 0, w, h - radius);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
        canvas.drawRect(clipRect, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, null, rectF, paint);

        bitmap.recycle();

        return output;
    }




}
