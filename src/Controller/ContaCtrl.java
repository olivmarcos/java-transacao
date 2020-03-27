/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.ContaDao;
import Models.Conta;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author marco
 */
public class ContaCtrl {

    public boolean save(String[] dados) throws SQLException {
        Conta conta = new Conta();
        conta.vetorTo(dados);

        ContaDao contaDao = new ContaDao();

        if (conta.getCnt_codigo() == 0) {
            contaDao.insert(conta);
            return true;
        } else {
            contaDao.update(conta);
            return false;
        }
    }

    public String[] recover(int id) throws SQLException {
        ContaDao contaDao = new ContaDao();

        Conta conta = contaDao.recover(id);

        return conta.toVetor();
    }

    public void update(String[] dados) throws SQLException {
        Conta conta = new Conta();

        conta.vetorTo(dados);

        ContaDao contaDao = new ContaDao();

        if (conta.getCnt_codigo() == 0) {
            contaDao.insert(conta);
        } else {
            contaDao.update(conta);
        }
    }

    public boolean delete(int id) throws SQLException {
        ContaDao contaDao = new ContaDao();
        if (contaDao.delete(id)) {
            return true;
        }
        return false;

    }

    public String[][] recoverAll() throws SQLException {
        ContaDao contaDao = new ContaDao();

        ArrayList<Conta> listContas = contaDao.recoverAll();

        String[][] matrizReturn;
        matrizReturn = new String[listContas.size()][3];
        for (int i = 0; i < listContas.size(); i++) {
            matrizReturn[i] = listContas.get(i).toVetor();
        }
        return matrizReturn;
    }
}
