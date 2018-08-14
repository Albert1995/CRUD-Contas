package br.pucpr.appdev.contascrud.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import br.pucpr.appdev.contascrud.R;
import br.pucpr.appdev.contascrud.model.User;

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

            saveName(new User(name));
            i.putExtra("user_name", name);
            startActivity(i);
            finish();
        }
    }

    public void saveName(User user) {
        try (
                OutputStream out = openFileOutput("app.dat", MODE_PRIVATE);
                OutputStreamWriter writer = new OutputStreamWriter(out);
                ) {
            Log.d("CONTAS-SAVE-001", "Salvando o nome do usuário...");
            writer.append(">>> USER\n");
            writer.append(user.getName());
            writer.flush();
            Log.d("CONTAS-SAVE-002", "Nome do usuário foi salvo com sucesso.");
        } catch (FileNotFoundException e) {
            Log.d("CONTAS-SAVE-ER001", "O arquivo app.dat não foi encontrado!");
            e.printStackTrace();
        } catch (IOException e) {
            Log.d("CONTAS-SAVE-ER999", "Houve um problema na operação de IO!");
            e.printStackTrace();
        }
    }
}
