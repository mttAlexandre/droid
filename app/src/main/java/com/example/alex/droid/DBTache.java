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

    public ArrayList<Tache> getAllTaches() {
        ArrayList<Tache> taches = new ArrayList<Tache>();

        Cursor cursor = database.query(DBHelper.TABLE_TACHE,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Tache t = cursorToTache(cursor);
            taches.add(t);
            cursor.moveToNext();
        }
        cursor.close();
        return taches;
    }

    public ArrayList<Tache> getAllTachesByDate() {
        ArrayList<Tache> taches = new ArrayList<Tache>();

        Cursor cursor = database.query(DBHelper.TABLE_TACHE,
                allColumns, null, null, null, null, DBHelper.COLUMN_DATE+" DESC");

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Tache t = cursorToTache(cursor);
            taches.add(t);
            cursor.moveToNext();
        }
        cursor.close();
        return taches;
    }

    public ArrayList<Tache> getAllTachesByDeadlin() {
        ArrayList<Tache> taches = new ArrayList<Tache>();

        Cursor cursor = database.query(DBHelper.TABLE_TACHE,
                allColumns, null, null, null, null, DBHelper.COLUMN_DEADLINE+" DESC");

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Tache t = cursorToTache(cursor);
            taches.add(t);
            cursor.moveToNext();
        }
        cursor.close();
        return taches;
    }

    public ArrayList<Tache> getAllTachesByLieu() {
        ArrayList<Tache> taches = new ArrayList<Tache>();

        Cursor cursor = database.query(DBHelper.TABLE_TACHE,
                allColumns, null, null, DBHelper.COLUMN_LIEU, null, DBHelper.COLUMN_DEADLINE+" DESC");

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Tache t = cursorToTache(cursor);
            taches.add(t);
            cursor.moveToNext();
        }
        cursor.close();
        return taches;
    }

    public ArrayList<Tache> getAllTachesByPriorite() {
        ArrayList<Tache> taches = new ArrayList<Tache>();

        Cursor cursor = database.query(DBHelper.TABLE_TACHE,
                allColumns, null, null, DBHelper.COLUMN_PRIORITE, null, DBHelper.COLUMN_DEADLINE+" DESC");

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Tache t = cursorToTache(cursor);
            taches.add(t);
            cursor.moveToNext();
        }
        cursor.close();
        return taches;
    }

    public ArrayList<Tache> getAllTachesByStatu() {
        ArrayList<Tache> taches = new ArrayList<Tache>();

        Cursor cursor = database.query(DBHelper.TABLE_TACHE,
                allColumns, null, null, DBHelper.COLUMN_STATU, null, DBHelper.COLUMN_DEADLINE+" DESC");

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Tache t = cursorToTache(cursor);
            taches.add(t);
            cursor.moveToNext();
        }
        cursor.close();
        return taches;
    }

    public ArrayList<Tache> getAllTachesByTheme() {
        ArrayList<Tache> taches = new ArrayList<Tache>();

        Cursor cursor = database.query(DBHelper.TABLE_TACHE,
                allColumns, null, null, DBHelper.COLUMN_THEME, null, DBHelper.COLUMN_DEADLINE+" DESC");

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Tache t = cursorToTache(cursor);
            taches.add(t);
            cursor.moveToNext();
        }
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
