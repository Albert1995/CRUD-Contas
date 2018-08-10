package br.pucpr.appdev.contascrud.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        String username = getIntent().getStringExtra("user_name");
        if (username != null) {
            TextView labelForm = findViewById(R.id.labelForm);
            String lblFormText = String.format(getResources().getString(R.string.greetings_user), username);
            labelForm.setText(lblFormText);
            firstTime = true;
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
            case 1:
                formaPagamento = FormaPagamento.BOLETO;
            case 2:
                formaPagamento = FormaPagamento.CARTAO_CREDITO;
            case 3:
                formaPagamento = FormaPagamento.CARTAO_DEBITO;
            case 4:
                formaPagamento = FormaPagamento.TRANSFERENCIA;
        }

        Conta c = new Conta(descricao, valor, tipo, formaPagamento);
        Intent i = new Intent(this, ListAllActivity.class);
        i.putExtra("conta", c);

        if(firstTime)
            startActivity(i);
        else
            setResult(RESULT_OK, i);

        finish();
    }

}
