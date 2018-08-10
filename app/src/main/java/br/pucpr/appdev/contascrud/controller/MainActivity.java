package br.pucpr.appdev.contascrud.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import br.pucpr.appdev.contascrud.R;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void beginOnClick(View v) {
        Intent i = new Intent(this, SignUnActivity.class);
        startActivity(i);
        finish();
    }
}
