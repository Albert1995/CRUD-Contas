package br.pucpr.appdev.contascrud.dao;

public class DAOException extends RuntimeException {
    public DAOException(Exception e) {
        super(e);
    }
}
