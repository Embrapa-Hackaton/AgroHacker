package hackathon.embrapa.agrohacker.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import hackathon.embrapa.agrohacker.R;
import hackathon.embrapa.agrohacker.model.Prague;

public class PragueAdapter extends BaseAdapter {

    private final List<Prague> pragues;
    private final Context context;

    public PragueAdapter(Context context, List<Prague> pragues) {
        this.context = context;
        this.pragues = pragues;
    }

    @Override
    public int getCount() {
        return pragues.size();
    }

    @Override
    public Object getItem(int position) {
        return pragues.get(position);
    }

    @Override
    public long getItemId(int position) {
        return pragues.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Prague prague = pragues.get(position);
        View view = convertView;

        LayoutInflater inflater = LayoutInflater.from(context);
        if(view == null){view = inflater.inflate(R.layout.activity_prague_list_item, parent, false);}

        TextView popularNameField = (TextView) view.findViewById(R.id.item_prague_name);
        popularNameField.setText(prague.getPopularName());

        TextView cultureField = (TextView) view.findViewById(R.id.item_prague_culture);
        cultureField.setText(prague.getCulture());

        TextView damageTypeField = (TextView) view.findViewById(R.id.item_prague_damage);
        damageTypeField.setText(prague.getDamageType());

        ImageView photoField = (ImageView) view.findViewById(R.id.prague_item_foto);
        ImageLoading(prague, photoField);

        return view;
    }

    private void ImageLoading(Prague prague, ImageView photoField) {
        String photoPath = prague.getPhotoPath();
        if (photoPath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(photoPath);
            Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bitmap, 100, 100, true);
            photoField.setImageBitmap(bitmapReduzido);
            photoField.setScaleType(ImageView.ScaleType.FIT_XY);
        }
    }
}