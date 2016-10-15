package hackathon.embrapa.agrohacker.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import hackathon.embrapa.agrohacker.model.Prague;

public class DAO extends SQLiteOpenHelper{

    public DAO(Context context) {
        super(context, "agroHacker", null, 3);
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
            case 2:
                sql = "CREATE TABLE Predator (id INTEGER PRIMARY KEY, culture TEXT, scientificName TEXT," +
                        "lifePeriod TEXT, popularName TEXT, groups TEXT, photoPath TEXT, atackPeriod TEXT, damageType TEXT);";
                db.execSQL(sql);

        }
    }

}
