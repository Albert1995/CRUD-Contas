package br.pucpr.appdev.contascrud.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import br.pucpr.appdev.contascrud.R;
import br.pucpr.appdev.contascrud.model.Conta;
import br.pucpr.appdev.contascrud.model.DataStore;
import br.pucpr.appdev.contascrud.view.ContaAdapter;

import static android.content.ContentValues.TAG;

public class ListAllActivity extends Activity {

    private RecyclerView recyclerView;
    private ContaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_all);

        Conta c = getIntent().getParcelableExtra("conta");
        DataStore.getInstance().setContext(this);

        if (c != null) {
            DataStore.getInstance().addConta(c);
        }

        recyclerView = findViewById(R.id.rvListAll);
        adapter = new ContaAdapter();
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
                DataStore.getInstance().clear();
                adapter.notifyDataSetChanged();
                break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1000) {
            if (resultCode == RESULT_OK) {
                Conta c = data.getParcelableExtra("conta");
                DataStore.getInstance().addConta(c);
                adapter.notifyDataSetChanged();
            }
        } else if (requestCode == 2000) {
            if (resultCode == RESULT_OK) {
                Conta c = data.getParcelableExtra("conta");
                int pos = data.getIntExtra("pos", -1);
                Log.d(TAG, "onActivityResult: " + pos);

                DataStore.getInstance().editConta(c, pos);
                adapter.notifyDataSetChanged();
            }
        }
    }
}
