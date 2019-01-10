package com.example.alex.droid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class Home extends AppCompatActivity {

    private DBTache dbt;
    private ListView hlv;
    private ArrayAdapter<String> adapter;
    private ArrayList<Tache> values;
    private boolean checkbox=false;

    MyCustomAdapter dataAdapter = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        dbt = new DBTache(this);
        dbt.open();

        Intent intent = getIntent();
        int radio = intent.getIntExtra("radio", -1);

        hlv = findViewById(R.id.homelist);
        values = setValues(radio);
        dataAdapter = new MyCustomAdapter(this,
                R.layout.item, values, false);

        hlv.setAdapter(dataAdapter);
        final Context myContxt = this;

        hlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Tache tache = dataAdapter.tacheList.get(position);

                if(tache == null){
                    System.out.println("C null");
                }
                else{
                    Intent intent = new Intent(myContxt,Detail.class);
                    intent.putExtra("Tache",tache);
                    startActivity(intent);
                }

            }
        });
    }

    protected void onStart(Bundle savedInstanceState){

    }

    @Override
    protected void onResume() {
        dbt.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        dbt.close();
        super.onPause();
    }

    public ArrayList<Tache> setValues(int i){
        ArrayList<Tache> res=new ArrayList<>();
        switch (i){
            case 0:
                res=dbt.getAllTachesByDate();
                break;
            case 1:
                res=dbt.getAllTachesByDeadline();
                break;
            case 2:
                res=dbt.getAllTachesByPriorite();
                break;
            case 3:
                res=dbt.getAllTachesByLieu();
                break;
            case 4:
                res=dbt.getAllTachesByTheme();
                break;
            case 5:
                res=dbt.getAllTachesByStatu();
                break;
            default:
                res=dbt.getAllTaches();
        }
        return res;
    }

    public void onClickSetting(View v){
        Intent intent = new Intent(this, Setting.class);
        startActivity(intent);
    }

    public void onClickDetail(View v){
        // EXEMPLE A LIRE
        // ÇA C'EST QUAND TU VEUX METTRE QUE LE VUE XML SI ELLE EST STATIC
        //setContentView(R.layout.detail);
        // ÇA C'EST PAREIL MAIS ÇA CHARGE LES ÉLÉMENTS DYNAMIC EN PLUS (COMME LE CONTENU DE LA LISTVIEW POUR CLICKHOME) et il
        // faut remettre un OnCreate dans touts les classes du coup
        Tache test = new Tache("TestDétail","tache test pour la page de détail", Tache.Theme.travail,"RAMBOUILLET",Calendar.getInstance().getTime(),Calendar.getInstance().getTime(),Tache.Statut.done,Tache.Priorite.high, null);
        Intent intent = new Intent(this, Detail.class);
        intent.putExtra("Tache",test);
        startActivity(intent);
    }

    public void onClickModfy(View v){
        Intent intent = new Intent(this, Modify.class);
        startActivity(intent);
    }

    public void onClickCalendar(View v){
        Intent intent = new Intent(this, CalendarTask.class);
        startActivity(intent);
    }

    public void onClickHome(View v){
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

    public void onClickDelete(View v) {
        if (values.isEmpty()) {
            Toast.makeText(getApplicationContext(),"Il n'y a rien à supprimer",Toast.LENGTH_LONG).show();
        } else {
            if (!checkbox) {
                checkbox = true;
                dataAdapter = new MyCustomAdapter(this,
                        R.layout.checkitem, values, true);
                hlv.setAdapter(dataAdapter);
            } else {
                checkbox = false;
                ArrayList<Tache> tacheList = dataAdapter.tacheList;
                ArrayList<Tache> toDelete = new ArrayList<Tache>();
                for (int i = 0; i < tacheList.size(); i++) {
                    Tache tache = tacheList.get(i);
                    if (tache.isChecked()) {
                        dbt.deleteTache(tache);
                        toDelete.add(tache);
                    }
                }
                dataAdapter.deleteAll(toDelete);

                dataAdapter = new MyCustomAdapter(this,
                        R.layout.item, values, false);
                hlv.setAdapter(dataAdapter);
            /*
            List<String> values = dbt.getAllNomTaches();
            adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, values);
            hlv.setAdapter(adapter);
            */
            }
        }
    }
    public void onClickAdd(View v){
        Tache test = new Tache("TestDétail","tache test pour la page de détail", Tache.Theme.travail,"RAMBOUILLET",Calendar.getInstance().getTime(),Calendar.getInstance().getTime(),Tache.Statut.done,Tache.Priorite.high, null);
        test = dbt.createTache(test.getNom());
        dataAdapter.add(test);


    }



    public void onListItemClick(ListView l, View v, int position, long id) {
        //aller à la page détail et afficher l'item cliqué
        Tache t = dataAdapter.tacheList.get(position);
        System.out.println("JE SUIS LA ");

        Intent intent = new Intent(this, Detail.class);
        startActivity(intent);
    }





    private class MyCustomAdapter extends ArrayAdapter<Tache>{

        private ArrayList<Tache> tacheList;
        private boolean checkbox;
        private boolean showDate;
        private boolean showDead;
        private boolean showPrio;
        private boolean showLieu;
        private boolean showTheme;
        private boolean showStatu;

        public MyCustomAdapter(Context context, int resource, ArrayList<Tache> list, boolean checkbox) {
            super(context, resource, list);
            this.tacheList=list;
            this.checkbox=checkbox;
        }

        private class ViewHolder {
            TextView code;
            CheckBox name;
        }

        private class ViewHolder2 {
            TextView txt;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if(checkbox) {
                ViewHolder holder = null;
                Log.v("ConvertView", String.valueOf(position));

                if (convertView == null) {
                    LayoutInflater vi = (LayoutInflater) getSystemService(
                            Context.LAYOUT_INFLATER_SERVICE);
                    convertView = vi.inflate(R.layout.checkitem, null);

                    holder = new ViewHolder();
                    holder.code = convertView.findViewById(R.id.code);
                    holder.name = convertView.findViewById(R.id.checkBox1);
                    convertView.setTag(holder);

                    holder.name.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            CheckBox cb = (CheckBox) v;
                            Tache tache = (Tache) cb.getTag();
                            Toast.makeText(getApplicationContext(),
                                    "Clicked on Checkbox: " + cb.getText() +
                                            " is " + cb.isChecked(),
                                    Toast.LENGTH_LONG).show();
                            tache.setChecked(cb.isChecked());
                        }
                    });
                } else {
                    holder = (ViewHolder) convertView.getTag();
                }

                Tache tache = tacheList.get(position);
                holder.code.setText("  "+tache.getDate());
                holder.name.setText(tache.getNom());
                holder.name.setChecked(tache.isChecked());
                holder.name.setTag(tache);
            }else{
                ViewHolder2 holder = null;
                Log.v("ConvertView", String.valueOf(position));

                if (convertView == null) {
                    LayoutInflater vi = (LayoutInflater) getSystemService(
                            Context.LAYOUT_INFLATER_SERVICE);
                    convertView = vi.inflate(R.layout.item, null);

                    holder = new ViewHolder2();
                    holder.txt = convertView.findViewById(R.id.txt);
                    convertView.setTag(holder);
                } else {
                    holder = (ViewHolder2) convertView.getTag();
                }

                Tache tache = tacheList.get(position);
                holder.txt.setText(getText(tache));//tache.getNom()+" Date : "+ tache.getDate());
                holder.txt.setTag(tache);
            }

            return convertView;
        }

        @Override
        public void add(Tache t) {
            tacheList.add(t);
            notifyDataSetChanged();
        }

        public void delete(Tache t) {
            tacheList.remove(t);
            notifyDataSetChanged();
        }

        public void deleteAll(ArrayList<Tache> l) {
            tacheList.removeAll(l);
            notifyDataSetChanged();
        }

        public String getText(Tache t){
            String s="";
            s+=t.getNom()+" ";

            if(this.showDate){
                s+=t.getDate()+" ";
            }else if(this.showDead){
                s+=t.getDeadline()+" ";
            }else if(this.showLieu){
                s+=t.getLieu()+" ";
            }else if(this.showPrio){
                s+=t.getPriorite()+" ";
            }else if(this.showStatu){
                s+=t.getStatut()+" ";
            }else if(this.showTheme){
                s+=t.getTheme()+" ";
            }
            return s;
        }
    }

    /*private void checkButtonClick() {


        Button myButton = (Button) findViewById(R.id.button3);
        myButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                StringBuffer responseText = new StringBuffer();
                responseText.append("The following were selected...\n");

                ArrayList<Tache> countryList = dataAdapter.tacheList;
                for(int i=0;i<countryList.size();i++){
                    Tache tache = countryList.get(i);
                    if(tache.getChecked()){
                        responseText.append("\n" + tache.getNom());
                    }
                }

                Toast.makeText(getApplicationContext(),
                        responseText, Toast.LENGTH_LONG).show();

            }
        });

    }*/
}
