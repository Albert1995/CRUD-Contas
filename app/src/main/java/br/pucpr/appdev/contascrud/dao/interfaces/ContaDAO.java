package br.pucpr.appdev.contascrud.dao.interfaces;

import java.util.List;

import br.pucpr.appdev.contascrud.dao.DAOException;
import br.pucpr.appdev.contascrud.model.Conta;

public interface ContaDAO {

    void save(Conta c) throws DAOException;
    void remove(Conta c) throws DAOException;
    void remove(long id) throws DAOException;
    Conta getById(long id) throws DAOException;
    List<Conta> getAll() throws DAOException;

}
