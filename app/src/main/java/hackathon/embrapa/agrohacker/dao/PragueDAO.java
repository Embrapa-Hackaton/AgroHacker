package hackathon.embrapa.agrohacker.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import hackathon.embrapa.agrohacker.model.Prague;

public class PragueDAO extends DAO {
    public PragueDAO(Context context) {
        super(context);
    }

    private ContentValues getPragueData(Prague prague) {
        ContentValues data = new ContentValues();
        data.put("popularName", prague.getPopularName());
        data.put("scientificName", prague.getScientificName());
        data.put("description", prague.getDescription());
        data.put("bioecology", prague.getBioecology());
        data.put("damage", prague.getDamage());
        data.put("lifeCicleStage", prague.getLifeCycleStage());
        data.put("photoPath", prague.getPhotoPath());
        return data;
    }

    public void insertPrague(Prague prague) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues data = getPragueData(prague);
        db.insert("Prague", null, data);
    }

    public List<Prague> showPragues() {
        String sql = "SELECT * FROM Prague;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor line = db.rawQuery(sql, null);
        List<Prague> pragues = new ArrayList<Prague>();
        while(line.moveToNext()) {
            Prague prague = new Prague();
            prague.setId(line.getInt(line.getColumnIndex("id")));
            prague.setPopularName(line.getString(line.getColumnIndex("popularName")));
            prague.setScientificName(line.getString(line.getColumnIndex("scientificName")));
            prague.setDescription(line.getString(line.getColumnIndex("description")));
            prague.setBioecology(line.getString(line.getColumnIndex("bioecology")));
            prague.setDamage(line.getString(line.getColumnIndex("damage")));
            prague.setLifeCycleStage(line.getString(line.getColumnIndex("lifeCicleStage")));
            prague.setPhotoPath(line.getString(line.getColumnIndex("photoPath")));
            pragues.add(prague);
        }
        line.close();
        return pragues;
    }

    public void removePrague(Prague prague) {
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {String.valueOf(prague.getId())};
        db.delete("Prague", "id = ?", params);
    }

    public void updatePrague(Prague prague) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues data = getPragueData(prague);
        String[] params = {String.valueOf(prague.getId())};
        db.update("Prague", data, "id = ?", params);
    }
}
