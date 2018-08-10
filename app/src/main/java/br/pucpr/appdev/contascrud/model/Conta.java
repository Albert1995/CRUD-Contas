package br.pucpr.appdev.contascrud.model;

import android.os.Parcel;
import android.os.Parcelable;


public class Conta implements Parcelable {

    private long id;
    private String descricao;
    private double valor;
    private TipoConta tipo;
    private FormaPagamento formaPagamento;

    public Conta(String descricao, double valor, TipoConta tipo, FormaPagamento formaPagamento) {
        this.descricao = descricao;
        this.valor = valor;
        this.tipo = tipo;
        this.formaPagamento = formaPagamento;
    }

    protected Conta(Parcel in) {
        id = in.readLong();
        descricao = in.readString();
        valor = in.readDouble();
        tipo = TipoConta.valueOf(in.readString());
        formaPagamento = FormaPagamento.valueOf(in.readString());
    }

    public static final Creator<Conta> CREATOR = new Creator<Conta>() {
        @Override
        public Conta createFromParcel(Parcel in) {
            return new Conta(in);
        }

        @Override
        public Conta[] newArray(int size) {
            return new Conta[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public TipoConta getTipo() {
        return tipo;
    }

    public void setTipo(TipoConta tipo) {
        this.tipo = tipo;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(descricao);
        parcel.writeDouble(valor);
        parcel.writeString(tipo.toString());
        parcel.writeString(formaPagamento.toString());
    }
}
