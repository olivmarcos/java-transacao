/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.ExtratoDao;
import Models.Extrato;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author marco
 */
public class ExtratoCtrl {
    
    public boolean save(String[] dados) throws SQLException {
        Extrato extrato = new Extrato();
        extrato.vetorTo(dados);

        ExtratoDao extratoDao = new ExtratoDao();

        if (extrato.getExt_codigo() == 0) {
            extratoDao.insert(extrato);
            return true;
        } else {
            extratoDao.update(extrato);
            return false;
        }
    }

    public String[] recover(int id) throws SQLException {
        ExtratoDao extratoDao = new ExtratoDao();

        Extrato extrato = extratoDao.recover(id);

        return extrato.toVetor();
    }

    public void update(String[] dados) throws SQLException {
        Extrato extrato = new Extrato();

        extrato.vetorTo(dados);

        ExtratoDao extratoDao = new ExtratoDao();

        if (extrato.getExt_codigo() == 0) {
            extratoDao.insert(extrato);
        } else {
            extratoDao.update(extrato);
        }
    }

    public boolean delete(int id) throws SQLException {
        ExtratoDao extratoDao = new ExtratoDao();
        if (extratoDao.delete(id)) {
            return true;
        }
        return false;

    }

    public String[][] recoverAll() throws SQLException {
        ExtratoDao extratoDao = new ExtratoDao();

        ArrayList<Extrato> listExtratos = extratoDao.recoverAll();

        String[][] matrizReturn;
        matrizReturn = new String[listExtratos.size()][5];
        for (int i = 0; i < listExtratos.size(); i++) {
            matrizReturn[i] = listExtratos.get(i).toVetor();
        }
        return matrizReturn;
    }
}
