package hackathon.embrapa.agrohacker.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import hackathon.embrapa.agrohacker.model.NaturalPredator;

public class NaturalPredatorDAO extends DAO {

    public NaturalPredatorDAO(Context context) { super(context); }

    private ContentValues getPredatorData(NaturalPredator predator) {
        ContentValues data = new ContentValues();
        data.put("culture", predator.getCulture());
        data.put("scientificName", predator.getScientificName());
        data.put("lifePeriod", predator.getLifePeriod());
        data.put("popularName", predator.getPopularName());
        data.put("groups", predator.getGroup());
        data.put("photoPath", predator.getPhotoPath());
        return data;
    }

    public void insertPredator(NaturalPredator predator) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues data = getPredatorData(predator);
        db.insert("Predator", null, data);
    }

    public List<NaturalPredator> showPredators() {
        String sql = "SELECT * FROM Predator;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor line = db.rawQuery(sql, null);
        List<NaturalPredator> predators = new ArrayList<NaturalPredator>();
        while(line.moveToNext()) {
            NaturalPredator predator = new NaturalPredator();
            predator.setId(line.getInt(line.getColumnIndex("id")));
            predator.setCulture(line.getString(line.getColumnIndex("culture")));
            predator.setScientificName(line.getString(line.getColumnIndex("scientificName")));
            predator.setLifePeriod(line.getString(line.getColumnIndex("lifePeriod")));
            predator.setPopularName(line.getString(line.getColumnIndex("popularName")));
            predator.setGroup(line.getString(line.getColumnIndex("groups")));
            predator.setPhotoPath(line.getString(line.getColumnIndex("photoPath")));
            predators.add(predator);
        }
        line.close();
        return predators;
    }

    public void removePredator(NaturalPredator predator) {
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {String.valueOf(predator.getId())};
        db.delete("Predator", "id = ?", params);
    }

    public void updatePredator(NaturalPredator predator) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues data = getPredatorData(predator);
        String[] params = {String.valueOf(predator.getId())};
        db.update("Predator", data, "id = ?", params);
    }
}
