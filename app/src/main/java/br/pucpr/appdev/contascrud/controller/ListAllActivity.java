package br.pucpr.appdev.contascrud.controller;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import br.pucpr.appdev.contascrud.R;
import br.pucpr.appdev.contascrud.dao.DAOException;
import br.pucpr.appdev.contascrud.dao.impl.FileContaDAO;
import br.pucpr.appdev.contascrud.dao.interfaces.ContaDAO;
import br.pucpr.appdev.contascrud.model.Conta;
import br.pucpr.appdev.contascrud.model.FormaPagamento;
import br.pucpr.appdev.contascrud.model.TipoConta;
import br.pucpr.appdev.contascrud.view.ContaAdapter;

import static android.content.ContentValues.TAG;

public class ListAllActivity extends Activity {

    private RecyclerView recyclerView;
    private ContaAdapter adapter;
    private ContaDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_all);

        dao = new FileContaDAO(this);
        List<Conta> contas = new ArrayList<>();
        try {
            contas = dao.getAll();
        } catch (DAOException e) {
            AssetManager am = getAssets();
            Log.d(TAG, "onCreate: Adicionando o Asset");
            try {
                InputStream in = am.open("default_contas.dat");
                BufferedReader reader = new BufferedReader(new InputStreamReader((in)));
                String line;
                while ((line = reader.readLine()) != null) {
                    String splited[] = line.split(";");
                    Conta c = new Conta();

                    c.setDescricao(splited[0]);
                    c.setValor(Double.parseDouble(splited[1]));
                    c.setTipo(TipoConta.valueOf(splited[2]));
                    c.setFormaPagamento(FormaPagamento.valueOf(splited[3]));

                    dao.save(c);
                }
                reader.close();
                in.close();
                contas = dao.getAll();
            } catch (IOException i) {
                i.printStackTrace();
            }
        }

        Conta c = getIntent().getParcelableExtra("conta");
        if (c != null) {
            dao.save(c);
            contas = dao.getAll();
        }

        Log.d(TAG, "onCreate: " + contas.size());

        recyclerView = findViewById(R.id.rvListAll);
        adapter = new ContaAdapter(contas, dao);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options, menu);
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addConta:
                startActivityForResult(new Intent(this, FormActivity.class), 1000);
                break;
            case R.id.menuReset:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Você tem certeza em reiniciar todos os dados do aplicativo?");
                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(ListAllActivity.this, MainActivity.class);
                        dao.removeAll();
                        deleteFile("app.dat");
                        startActivity(i);
                        ListAllActivity.this.finish();
                    }
                });
                builder.setNegativeButton("Não", null);
                builder.show();
                break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1000 || requestCode == 2000) {
            if (resultCode == RESULT_OK) {
                Conta c = data.getParcelableExtra("conta");
                dao.save(c);
                adapter.setContas(dao.getAll());
                adapter.notifyDataSetChanged();
            }
        }
    }
}
