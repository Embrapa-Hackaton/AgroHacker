package hackathon.embrapa.agrohacker.helper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
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
            bitmapReduzido = roundCorner(bitmapReduzido, 10);
            photoField.setImageBitmap(bitmapReduzido);
            photoField.setScaleType(ImageView.ScaleType.FIT_XY);
            photoField.setTag(photoPath);
        }
    }

    public static Bitmap roundCorner(Bitmap src, float round) {
        // image size
        int width = src.getWidth();
        int height = src.getHeight();

        // create bitmap output
        Bitmap result = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        // set canvas for painting
        Canvas canvas = new Canvas(result);
        canvas.drawARGB(0, 0, 0, 0);

        // config paint
        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);

        // config rectangle for embedding
        final Rect rect = new Rect(0, 0, width, height);
        final RectF rectF = new RectF(rect);

        // draw rect to canvas
        canvas.drawRoundRect(rectF, round, round, paint);

        // create Xfer mode
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        // draw source image to canvas
        canvas.drawBitmap(src, rect, rect, paint);

        // return final image
        return result;
    }


}
