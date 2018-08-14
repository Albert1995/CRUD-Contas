package br.pucpr.appdev.contascrud.model;

import android.graphics.Color;

import br.pucpr.appdev.contascrud.R;

public enum TipoConta {

    ENTRADA(R.color.conta_entrada_valor),
    SAIDA(R.color.conta_saida_valor);

    private int colorId;

    TipoConta (int colorId) {
        this.colorId = colorId;
    }

    public int getColorId() {
        return colorId;
    }
}
