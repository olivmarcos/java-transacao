/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco;

import DAO.ContaDao;
import Models.Conta;
import java.sql.Connection;
import Conexao.Conexao;
import Controller.ContaCtrl;
import java.sql.SQLException;

/**
 *
 * @author marco
 */
public class Banco {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {

        try {
            Conta conta = new Conta();
            conta.setCnt_descricao("Minha Conta");
            conta.setCnt_saldo(0.0);

            Connection conn = Conexao.getConexaoTransacional();
            ContaDao contaDao = new ContaDao(conn);
            contaDao.insert(conta);
//           conn.commit();
            System.out.println(contaDao.recover(7));

        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
