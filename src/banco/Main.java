/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco;

import java.sql.SQLException;

import Controller.ContaCtrl;
import DAO.ContaDao;
import Facade.Operations;
import Models.Conta;

/**
 *
 * @author marco
 */
public class Main {

    /**
     * @param args the command line arguments
     */ 
    public static void main(final String[] args) throws SQLException {

        Conta conta = new Conta();

        conta = new ContaDao().recover(1);

      /*  ContaDao dao = new ContaDao();

        try {
            dao.update(conta);
            return;
        } catch (Exception e) {
            e.getMessage();
            return;
        }*/

        Operations.saque(conta, 100.00);
    }

}
