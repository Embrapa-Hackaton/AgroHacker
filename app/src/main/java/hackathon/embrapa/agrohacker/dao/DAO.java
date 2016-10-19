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
        super(context, "agroHacker", null, 5);
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
            case 3:
                sql = "CREATE TABLE Plot (id INTEGER PRIMARY KEY, plantationStage TEXT, plantationStartDate DATE," +
                        "harvestDate DATE, platationCulture TEXT, status TEXT);";
                db.execSQL(sql);
                sql = "CREATE TABLE Inspection (id INTEGER PRIMARY KEY, realization DATE, inspectorName TEXT," +
                        "status TEXT);";
                db.execSQL(sql);
                sql = "CREATE TABLE Trap (id INTEGER PRIMARY KEY, lastChange DATE, duration INTEGER," +
                        "pheromone TEXT, status TEXT);";
                db.execSQL(sql);
            case 4:
                sql = "DROP TABLE IF EXISTS Prague;";
                db.execSQL(sql);
                sql = "CREATE TABLE Prague (id INTEGER PRIMARY KEY, popularName TEXT, scientificName TEXT," +
                        "description TEXT, bioecology TEXT, damage TEXT, lifeCicleStage TEXT, photoPath TEXT);";
                db.execSQL(sql);
                sql = "DROP TABLE IF EXISTS Predator;";
                db.execSQL(sql);
                sql = "CREATE TABLE Predator (id INTEGER PRIMARY KEY, popularName TEXT, scientificName TEXT," +
                        "description TEXT, importance TEXT, photoPath TEXT);";
                db.execSQL(sql);
        }
    }

}
