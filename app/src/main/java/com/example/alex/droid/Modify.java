package com.example.alex.droid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

// FORM POUR MODIFIER UNE TACHE, OU LA SUPPRIMER

public class Modify extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify);
    }

    public void onClickHome(View v){
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }
}
