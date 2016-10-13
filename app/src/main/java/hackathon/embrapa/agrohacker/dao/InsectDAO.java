package hackathon.embrapa.agrohacker.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import hackathon.embrapa.agrohacker.model.Insect;
import hackathon.embrapa.agrohacker.model.Plot;

public class InsectDAO extends SQLiteOpenHelper{

    public InsectDAO(Context context) {
        super(context, "AgroHacker", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Insect (id INTEGER PRIMARY KEY, culture TEXT, scientificName TEXT," +
                "lifePeriod INTEGER, popularName TEXT, group TEXT);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXIST Insect;";
        db.execSQL(sql);
        onCreate(db);
    }

    private ContentValues getInsectData(Insect insect) {
        ContentValues data = new ContentValues();
        data.put("culture", insect.getCulture());
        data.put("scientificName", insect.getScientificName());
        data.put("lifePeriod", insect.getLifePeriod().toString());
        data.put("popularName", insect.getPopularName());
        data.put("group", insect.getGroup());
        return data;
    }

    public void insertInsect(Insect insect) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues data = getInsectData(insect);
        db.insert("Insect", null, data);
    }

    public List<Insect> showInsects() {
        String sql = "SELECT * FROM Insect;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor line = db.rawQuery(sql, null);
        List<Insect> insects = new ArrayList<Insect>();
        while(line.moveToNext()) {
            Insect insect = new Insect();
            insect.setId(line.getInt(line.getColumnIndex("id")));
            insect.setCulture(line.getString(line.getColumnIndex("culture")));
            insect.setScientificName(line.getString(line.getColumnIndex("scientificName")));
            insect.setLifePeriod(Integer.valueOf(line.getString(line.getColumnIndex("lifePeriod"))));
            insect.setPopularName(line.getString(line.getColumnIndex("popularName")));
            insect.setGroup(line.getString(line.getColumnIndex("group")));
            insects.add(insect);
        }
        line.close();
        return insects;
    }

    public void removeInsect(Insect insect) {
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {String.valueOf(insect.getId())};
        db.delete("Insect", "id = ?", params);
    }

    public void updateInsect(Insect insect) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues data = getInsectData(insect);
        String[] params = {String.valueOf(insect.getId())};
        db.update("Insect", data, "id = ?", params);
    }
}
