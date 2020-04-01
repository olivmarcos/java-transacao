package Facade;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

import Conexao.Conexao;
import Controller.ContaCtrl;
import DAO.ContaDao;
import DAO.ExtratoDao;
import Models.Conta;
import Models.Extrato;

public class Operations {

    public static void saque(Conta conta, double saque) throws SQLException {
        if(conta.getCnt_saldo() >= saque) {
            Connection conn = Conexao.getConexaoTransacional();
            try {

                Extrato extrato = new Extrato();
                extrato.setExt_descricao("Saque");
                extrato.setExt_tipo("S");
                extrato.setExt_valor(saque);
                extrato.setExt_cod_conta(conta.getCnt_codigo());
                extrato.setExt_data(new Date());

                ExtratoDao extDao = new ExtratoDao(conn);
                extDao.insert(extrato);

                conta.setCnt_saldo(conta.getCnt_saldo() - saque);
                ContaDao cntDao = new ContaDao(conn);
                cntDao.update(conta);

                conn.commit();

            } catch (Exception e) {
                System.err.println("Erro ao realizar saque: " + e.getMessage());
                conn.rollback();
            }
        }
        System.out.println("Não há saldo suficiente!");
    }
}