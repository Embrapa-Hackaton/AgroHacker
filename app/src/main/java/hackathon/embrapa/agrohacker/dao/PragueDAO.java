package hackathon.embrapa.agrohacker.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import hackathon.embrapa.agrohacker.model.Prague;

public class PragueDAO extends SQLiteOpenHelper{

    public PragueDAO(Context context) {
        super(context, "agroHacker", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Prague (id INTEGER PRIMARY KEY, culture TEXT, scientificName TEXT," +
                "lifePeriod TEXT, popularName TEXT, groups TEXT, atackPeriod TEXT, damageType TEXT);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "";
        switch (oldVersion) {
            case 1:
                sql = "ALTER TABLE Prague ADD COLUMN photoPath TEXT";
                db.execSQL(sql);
        }
    }

    private ContentValues getPragueData(Prague prague) {
        ContentValues data = new ContentValues();
        data.put("culture", prague.getCulture());
        data.put("scientificName", prague.getScientificName());
        data.put("lifePeriod", prague.getLifePeriod());
        data.put("popularName", prague.getPopularName());
        data.put("groups", prague.getGroup());
        data.put("photoPath", prague.getPhotoPath());
        data.put("atackPeriod", prague.getAtackPeriod());
        data.put("damageType", prague.getDamageType());
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
            prague.setCulture(line.getString(line.getColumnIndex("culture")));
            prague.setScientificName(line.getString(line.getColumnIndex("scientificName")));
            prague.setLifePeriod(line.getString(line.getColumnIndex("lifePeriod")));
            prague.setPopularName(line.getString(line.getColumnIndex("popularName")));
            prague.setGroup(line.getString(line.getColumnIndex("groups")));
            prague.setPhotoPath(line.getString(line.getColumnIndex("photoPath")));
            prague.setAtackPeriod(line.getString(line.getColumnIndex("atackPeriod")));
            prague.setDamageType(line.getString(line.getColumnIndex("damageType")));
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
