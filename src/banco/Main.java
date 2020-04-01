/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco;

import java.sql.SQLException;

import Controller.ContaCtrl;
import Controller.ExtratoCtrl;
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

        // Conta contaOrigem = new Conta();
        // contaOrigem = new ContaDao().recover(4);

        // Conta contaDestino = new Conta();
        // contaDestino = new ContaDao().recover(1);

        // ContaDao dao = new ContaDao();

        // try {
        //     dao.update(conta);
        //     return;
        // } catch (Exception e) {
        //     e.getMessage();
        //     return;
        // }

        // Operations.deposito(conta, 100.00);
        // Operations.saque(contaDestino, 100.00);
        // Operations.transferencia(contaOrigem, contaDestino, 500.00);

        ContaCtrl ctrl = new ContaCtrl();

        ctrl.transferencia(4, 1, 1000.00);
    }

}
