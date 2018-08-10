package br.pucpr.appdev.contascrud.model;

public enum FormaPagamento {

    BOLETO("Boleto"),
    CARTAO_CREDITO("Cartão de Crédito"),
    CARTAO_DEBITO("Cartão de Débito"),
    TRANSFERENCIA("Transferência");

    private String nome;

    FormaPagamento(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }


}
