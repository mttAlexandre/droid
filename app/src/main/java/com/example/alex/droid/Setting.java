package com.example.alex.droid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

// MAXIME : "PAGE DEU SETTINGS" BREF ON VERRA APRES POUR LE DESIGNE ET LES DIFFERENTES MODIF

public class Setting extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
    }

    public void onClickHome(View v){
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

    public void onClickValider(View v){
        

        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }
}
