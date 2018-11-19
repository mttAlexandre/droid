package com.example.alex.droid;

import android.app.Activity;
import android.os.Bundle;

// FORM POUR MODIFIER UNE TACHE, OU LA SUPPRIMER

public class Modify extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify);
    }
}
