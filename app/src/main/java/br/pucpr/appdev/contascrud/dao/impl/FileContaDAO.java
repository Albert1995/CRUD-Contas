package br.pucpr.appdev.contascrud.dao.impl;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.pucpr.appdev.contascrud.dao.DAOException;
import br.pucpr.appdev.contascrud.dao.DAOUtil;
import br.pucpr.appdev.contascrud.dao.interfaces.ContaDAO;
import br.pucpr.appdev.contascrud.model.Conta;
import br.pucpr.appdev.contascrud.model.FormaPagamento;
import br.pucpr.appdev.contascrud.model.TipoConta;

public class FileContaDAO implements ContaDAO {

    private Context ctx;
    private final String separator = ";";

    public FileContaDAO(Context ctx) {
        this.ctx = ctx;
    }

    private void saveAll(List<Conta> contas) throws DAOException {
        try (PrintWriter writer = new PrintWriter(DAOUtil.openFileToWrite(ctx))) {
            writer.print("");
            for (Conta conta : contas)
                writer.print(conta.getId() + separator + conta.getDescricao() + separator
                        + conta.getValor() + separator + conta.getTipo() + separator
                        + conta.getFormaPagamento() + "\r\n");
        } catch (IOException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void save(Conta c) throws DAOException {
        List<Conta> contas = getAll();
        if (c.getId() > 0) {
            for (int count = 0; count < contas.size(); count++) {
                if (contas.get(count).getId() == c.getId()) {
                    contas.set(count, c);
                }
            }
        } else {
            c.setId(Long.parseLong(new SimpleDateFormat("yyMMddHHmmss").format(new Date())));
            contas.add(c);
        }

        saveAll(contas);
    }

    @Override
    public void remove(Conta c) throws DAOException {
        remove(c.getId());
    }

    @Override
    public void remove(long id) throws DAOException {
        List<Conta> contas = getAll();
        for (int count = 0; count < contas.size(); count++) {
            if (contas.get(count).getId() == id)
                contas.remove(count);
        }
        saveAll(contas);
    }

    @Override
    public Conta getById(long id) throws DAOException {
        Conta c = null;
        List<Conta> contas = getAll();
        for (int count = 0; count < contas.size(); count++) {
            if (contas.get(count).getId() == id)
                c = contas.get(count);
        }
        return c;
    }

    @Override
    public List<Conta> getAll() throws DAOException {
        List<Conta> contas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(DAOUtil.openFileToRead(ctx))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String splited[] = line.split(separator);
                Conta c = new Conta();

                c.setId(Long.parseLong(splited[0]));
                c.setDescricao(splited[1]);
                c.setValor(Double.parseDouble(splited[2]));
                c.setTipo(TipoConta.valueOf(splited[3]));
                c.setFormaPagamento(FormaPagamento.valueOf(splited[4]));

                contas.add(c);
            }
        } catch (IOException e) {
            throw new DAOException(e);
        }
        return contas;
    }
}
