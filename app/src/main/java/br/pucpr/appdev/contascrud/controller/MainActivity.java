package br.pucpr.appdev.contascrud.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import br.pucpr.appdev.contascrud.R;

public class MainActivity extends Activity {

    private boolean signup = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try (BufferedReader br = new BufferedReader(new InputStreamReader(openFileInput("app.dat")))) {
            TextView welcome = findViewById(R.id.welcomeTxt);
            welcome.setText("Bem-vindo novamente, " + br.readLine());
            signup = false;
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }
    }

    public void beginOnClick(View v) {
        Intent i = null;
        if (signup) {
            i = new Intent(this, SignUnActivity.class);
        } else {
            i = new Intent(this, ListAllActivity.class);
        }
        startActivity(i);
        finish();
    }
}
