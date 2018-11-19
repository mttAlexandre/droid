package com.example.alex.droid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DBTache {

    private SQLiteDatabase database;
    private DBHelper dbHelper;
    private String[] allColumns = { DBHelper.COLUMN_ID,
            DBHelper.COLUMN_NOM};

    public DBTache(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Tache createTache(String nom) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_NOM, nom);
        long insertId = database.insert(DBHelper.TABLE_TACHE, null,
                values);
        Cursor cursor = database.query(DBHelper.TABLE_TACHE,
                allColumns, DBHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Tache newTache = cursorToTache(cursor);
        cursor.close();
        return newTache;
    }

    public void deleteTache(Tache t) {
        long id = t.getId();
        System.out.println("Tache deleted with id: " + id);
        database.delete(DBHelper.TABLE_TACHE, DBHelper.COLUMN_ID
                + " = " + id, null);
    }

    public List<Tache> getAllTaches() {
        List<Tache> taches = new ArrayList<Tache>();

        Cursor cursor = database.query(DBHelper.TABLE_TACHE,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Tache t = cursorToTache(cursor);
            taches.add(t);
            cursor.moveToNext();
        }
        // assurez-vous de la fermeture du curseur
        cursor.close();
        return taches;
    }

    public List<String> getAllNomTaches() {
        List<String> taches = new ArrayList<String>();

        Cursor cursor = database.query(DBHelper.TABLE_TACHE,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Tache t = cursorToTache(cursor);
            taches.add(t.getNom());
            cursor.moveToNext();
        }
        // assurez-vous de la fermeture du curseur
        cursor.close();
        return taches;
    }



    private Tache cursorToTache(Cursor cursor) {
        Tache t = new Tache();
        t.setId(cursor.getLong(0));
        t.setNom(cursor.getString(1));
        return t;
    }
}
