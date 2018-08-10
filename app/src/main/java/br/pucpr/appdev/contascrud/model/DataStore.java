package br.pucpr.appdev.contascrud.model;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DataStore {
    private static final DataStore ourInstance = new DataStore();

    private List<Conta> contas;
    private Context ctx;

    public static DataStore getInstance() {
        return ourInstance;
    }

    private DataStore() {
        contas = new ArrayList<>();
    }

    public void setContext(Context context) {
        this.ctx = context;
    }

    public void addConta(Conta c) {
        contas.add(c);
    }

    public void editConta(Conta c, int position) {
        Log.d("Teste","posição : " + position);
        if (position >= 0)
            contas.set(position, c);
    }

    public void removeConta(int position) {
        contas.remove(position);
    }

    public Conta getConta(int position) {
        return contas.get(position);
    }

    public List<Conta> getAllContas() {
        return contas;
    }
}
