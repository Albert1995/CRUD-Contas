package br.pucpr.appdev.contascrud;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SignUnActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_un);
    }

    public void nextOnClick(View v) {
        EditText editName = findViewById(R.id.editName);
        String name = editName.getText().toString();
        TextView err = findViewById(R.id.txtNameError);

        if (name.length() == 0) {
            err.setText("Por favor, preencha o campo com o seu nome.");
        } else {
            Intent i = new Intent(this, FormActivity.class);
            i.putExtra("user_name", name);
            startActivity(i);
        }
    }
}
