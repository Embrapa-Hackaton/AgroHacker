package hackathon.embrapa.agrohacker.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DAO extends SQLiteOpenHelper{

    public DAO(Context context) {
        super(context, "AgroHackerTable", null, 2);
    }

    private String sql = "";

    @Override
    public void onCreate(SQLiteDatabase db) {
        sql = "CREATE TABLE Prague (id INTEGER PRIMARY KEY, " +
                "popularName TEXT, " +
                "scientificName TEXT, " +
                "description TEXT, " +
                "bioecology TEXT, " +
                "damage TEXT, " +
                "lifeCicleStage TEXT, " +
                "photoPath TEXT);";
        db.execSQL(sql);
        sql = "CREATE TABLE Predator (id INTEGER PRIMARY KEY, " +
                "popularName TEXT, " +
                "scientificName TEXT, " +
                "description TEXT, " +
                "importance TEXT, " +
                "photoPath TEXT);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion) {
            case 1:
                sql = "CREATE TABLE Plot (id INTEGER PRIMARY KEY, plantationStage TEXT, plantationStartDate DATE," +
                        "harvestDate DATE, platationCulture TEXT, status TEXT);";
                db.execSQL(sql);
                sql = "CREATE TABLE Inspection (id INTEGER PRIMARY KEY, realization DATE, inspectorName TEXT," +
                        "status TEXT);";
                db.execSQL(sql);
                sql = "CREATE TABLE Trap (id INTEGER PRIMARY KEY, lastChange DATE, duration INTEGER," +
                        "pheromone TEXT, status TEXT);";
                db.execSQL(sql);
        }
    }
}
