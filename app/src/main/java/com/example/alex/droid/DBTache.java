package com.example.alex.droid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DBTache {

    private SQLiteDatabase database;
    private DBHelper dbHelper;
    private String[] allColumns = {
            DBHelper.COLUMN_ID,
            DBHelper.COLUMN_NOM,
            DBHelper.COLUMN_DESCRIPTION,
            DBHelper.COLUMN_THEME,
            DBHelper.COLUMN_LIEU,
            DBHelper.COLUMN_DATE,
            DBHelper.COLUMN_TIME,
            DBHelper.COLUMN_DEADLINE,
            DBHelper.COLUMN_STATU,
            DBHelper.COLUMN_PRIORITE,
            DBHelper.COLUMN_FREQUENCE
    };

    public DBTache(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }


    public void createTask(Tache task){
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_NOM,task.getNom());
        values.put(DBHelper.COLUMN_DESCRIPTION, task.getDescription());
        values.put(DBHelper.COLUMN_DATE, task.getTaskDate());
        values.put(DBHelper.COLUMN_TIME,task.getTaskTime());
        values.put(DBHelper.COLUMN_LIEU, task.getLieu());

        if(task.getStatut() != null)
            values.put(DBHelper.COLUMN_STATU, task.getStatut().toString());
        if(task.getPriorite() !=null)
            values.put(DBHelper.COLUMN_PRIORITE, task.getPriorite().toString());
        if(task.getTheme() != null)
            values.put(DBHelper.COLUMN_THEME, task.getTheme().toString());

        values.put(DBHelper.COLUMN_DEADLINE, task.getTaskDeadline());

        task.setId(database.insert(DBHelper.TABLE_TACHE, null,
                values));
    }

    public void deleteTache(Tache t) {
        long id = t.getId();
        System.out.println("Tache deleted with id: " + id);
        database.delete(DBHelper.TABLE_TACHE, DBHelper.COLUMN_ID
                + " = " + id, null);
    }

    public ArrayList<Tache> getTaskByPeriod(String date1){
        ArrayList<Tache> taches = new ArrayList<Tache>();
        //Cursor cursor = database.query(DBHelper.TABLE_TACHE, allColumns, "date <= ? and date >= ?", new String[]{date1, date2}, null, null, DBHelper.COLUMN_DATE+" DESC");
        Cursor cursor = database.query(DBHelper.TABLE_TACHE, allColumns, null, null, null, null, DBHelper.COLUMN_DATE+" DESC");

        cursor.moveToFirst();
        String [] date = date1.split("/");

        while (!cursor.isAfterLast()) {
            Tache t = cursorToTache(cursor);
            if(t.getTaskDate() != null){
                String[] dateTask = t.getTaskDate().split("/");

                if(date[0].equals(dateTask[1]) && date[1].equals(dateTask[2])){
                    //Log.e("coucoujfnkj888", t.getTaskDate());
                    taches.add(t);
                }
            }
            cursor.moveToNext();
        }
        cursor.close();
        return taches;
    }

    public ArrayList<String> getAllNomsTacheDuJour(){
        ArrayList<String>tasksnames = new ArrayList<String>();

        Cursor cursor =database.query(DBHelper.TABLE_TACHE,allColumns,null,null,null,null,DBHelper.COLUMN_DATE);
        Calendar c = Calendar.getInstance();
        String today = ""+c.get(Calendar.DAY_OF_MONTH)+"/"+(c.get(Calendar.MONTH)+1)+"/"+c.get(Calendar.YEAR);
        cursor.moveToFirst();

        do {
            Tache t = cursorToTache(cursor);
            if(t.getTaskDate().equals(today))
                tasksnames.add(t.getNom());
            cursor.moveToNext();
        }  while(!cursor.isAfterLast());
        cursor.close();
        return tasksnames;
    }

    public ArrayList<Tache> getAllTaches() {
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

    public ArrayList<Tache> getAllTachesByDeadline() {
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

    private Tache cursorToTache(Cursor cursor){
        Tache t = new Tache();
        t.setId(cursor.getLong(0));
        t.setNom(cursor.getString(1));
            t.setDescription(cursor.getString(2));
            String theme = cursor.getString(3);
            if (theme != null) {
                if (theme == "Travail")
                    t.setTheme(Tache.Theme.travail);
                else if (theme == "Famille")
                    t.setTheme(Tache.Theme.famille);
                else if (theme == "RDV")
                    t.setTheme(Tache.Theme.rdv);
                else if (theme == "Maison")
                    t.setTheme(Tache.Theme.maison);
                else
                    t.setTheme(Tache.Theme.course);
            } else
                t.setTheme(null);

            t.setLieu(cursor.getString(4));
            t.setTaskDate(cursor.getString(5));
            t.setTaskTime(cursor.getString(6));
            t.setTaskDeadline(cursor.getString(7));

            String statut = cursor.getString(8);
            if (statut != null) {
                if (statut == "To do")
                    t.setStatut(Tache.Statut.todo);
                else
                    t.setStatut(Tache.Statut.done);
            }

            String priorite = cursor.getString(9);
            if (priorite != null) {
                if (priorite == "Haute")
                    t.setPriorite(Tache.Priorite.high);
                else if (priorite == "Moyenne")
                    t.setPriorite(Tache.Priorite.medium);
                else
                    t.setPriorite(Tache.Priorite.low);
            } else {
                t.setPriorite(null);
            }

        /*String frequence = cursor.getString(10);
        if(frequence != null){
            if(frequence == "Tous les jours")
        }*/


            //fraquence


        return t;
    }

}
