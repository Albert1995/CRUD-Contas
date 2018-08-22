package br.pucpr.appdev.contascrud.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import br.pucpr.appdev.contascrud.R;
import br.pucpr.appdev.contascrud.model.Conta;
import br.pucpr.appdev.contascrud.model.FormaPagamento;
import br.pucpr.appdev.contascrud.model.TipoConta;

public class FormActivity extends Activity {

    private boolean firstTime = false;
    private long id = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        String username = getIntent().getStringExtra("user_name");
        Conta c = getIntent().getParcelableExtra("conta");

        if (username != null) {
            TextView labelForm = findViewById(R.id.labelForm);
            String lblFormText = String.format(getResources().getString(R.string.greetings_user), username);
            labelForm.setText(lblFormText);
            firstTime = true;
        }

        if (c != null) {
            id = c.getId();
            ((Button) findViewById(R.id.addEditForm)).setText("Alterar");

            ((EditText) findViewById(R.id.txtDescricao)).setText(c.getDescricao());
            ((EditText) findViewById(R.id.txtValor)).setText(String.valueOf(c.getValor()));
            if (c.getTipo().equals(TipoConta.ENTRADA))
                ((RadioButton) findViewById(R.id.rdEntrada)).setChecked(true);
            else
                ((RadioButton) findViewById(R.id.rdSaida)).setChecked(true);

            int x = 0;

            if (c.getFormaPagamento().equals(FormaPagamento.BOLETO))
                x = 0;
            else if (c.getFormaPagamento().equals(FormaPagamento.CARTAO_CREDITO))
                x = 1;
            else if (c.getFormaPagamento().equals(FormaPagamento.CARTAO_DEBITO))
                x = 2;
            else if (c.getFormaPagamento().equals(FormaPagamento.TRANSFERENCIA))
                x = 3;

            ((Spinner) findViewById(R.id.spFormaPagamento)).setSelection(x);

        }
    }

    public void addOnClick(View v) {
        EditText txtDescricao = findViewById(R.id.txtDescricao);
        EditText txtValor = findViewById(R.id.txtValor);
        RadioButton rdEntrada = findViewById(R.id.rdEntrada);
        RadioButton rdSaida = findViewById(R.id.rdSaida);
        Spinner spFormaPagamento = findViewById(R.id.spFormaPagamento);

        String descricao = txtDescricao.getText().toString();
        double valor = Double.parseDouble(txtValor.getText().toString());
        TipoConta tipo = (rdEntrada.isChecked() ? TipoConta.ENTRADA : TipoConta.SAIDA);
        FormaPagamento formaPagamento = FormaPagamento.BOLETO;

        switch (spFormaPagamento.getSelectedItemPosition()) {
            case 0:
                formaPagamento = FormaPagamento.BOLETO;
                break;
            case 1:
                formaPagamento = FormaPagamento.CARTAO_CREDITO;
                break;
            case 2:
                formaPagamento = FormaPagamento.CARTAO_DEBITO;
                break;
            case 3:
                formaPagamento = FormaPagamento.TRANSFERENCIA;
                break;
        }

        Conta c = new Conta(id, descricao, valor, tipo, formaPagamento);
        Intent i = new Intent(this, ListAllActivity.class);
        i.putExtra("conta", c);

        if(firstTime)
            startActivity(i);
        else
            setResult(RESULT_OK, i);

        finish();
    }

}
