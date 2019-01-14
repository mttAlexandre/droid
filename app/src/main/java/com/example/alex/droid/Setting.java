package com.example.alex.droid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;


public class Setting extends Activity {

    private RadioGroup radio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);

        radio=findViewById(R.id.radio);
        radio.clearCheck();
    }

    public void onClickHome(View v){
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

    public void onClickValider(View v){
        int s = radio.getCheckedRadioButtonId();


        Intent intent = new Intent();
        intent.putExtra("radio", s);
        setResult(3,intent);
        finish();
    }
}
