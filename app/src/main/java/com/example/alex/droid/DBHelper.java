package com.example.alex.droid;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    //NOM DE LA BDD, TABLE, ET COLONNES, sans accents sinon erreur
    public static final String TABLE_TACHE = "tache";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NOM = "nom";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_THEME = "theme";
    public static final String COLUMN_LIEU = "lieu";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_TIME = "time";
    public static final String COLUMN_DEADLINE = "deadline";
    public static final String COLUMN_STATU = "statu";
    public static final String COLUMN_PRIORITE = "priorite";
    public static final String COLUMN_FREQUENCE = "frequence";

    private static final String DATABASE_NAME = "tache.db";
    private static final int DATABASE_VERSION = 1;

    // Commande sql pour la création de la base de données, IL FAUT ENCORE AJOUTER TOUTES LES AUTRES COLONNES
    // POUR LES DATES LE FORMAT SQL-JAVA-ANDROID EST CANCER DONC ON VERRA APRÈS
    private static final String DATABASE_CREATE = "create table if not exists "
            + TABLE_TACHE + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_NOM + " text not null, "
            + COLUMN_DESCRIPTION + " text, "
            + COLUMN_THEME + " text, "
            + COLUMN_LIEU + " text, "
            + COLUMN_DATE + " text, "
            + COLUMN_TIME + "text, "
            + COLUMN_DEADLINE + " text, "
            + COLUMN_STATU + " text, "
            + COLUMN_PRIORITE + " text, "
            + COLUMN_FREQUENCE + " text "
            + ");";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DBHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TACHE);
        onCreate(db);
    }
}
