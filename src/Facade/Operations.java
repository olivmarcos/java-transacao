package Facade;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import Conexao.Conexao;
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
                System.out.println("Saque realizado com sucesso!");
            } catch (Exception e) {
                System.err.println("Erro ao realizar saque: " + e.getMessage());
                conn.rollback();
            }
        }
        else {
            System.err.println("Não há saldo suficiente!");
        }
    }

    public static void deposito(Conta conta, double deposito) {
        Connection conn = Conexao.getConexaoTransacional();

        try {
            Extrato extrato = new Extrato();
            extrato.setExt_descricao("Depósito");
            extrato.setExt_tipo("D");
            extrato.setExt_valor(deposito);
            extrato.setExt_cod_conta(conta.getCnt_codigo());
            extrato.setExt_data(new Date());

            ExtratoDao extDao = new ExtratoDao(conn);
            extDao.insert(extrato);

            conta.setCnt_saldo(conta.getCnt_saldo() + deposito);
            ContaDao cntDao = new ContaDao(conn);
            cntDao.update(conta);

            conn.commit();
            System.out.println("Depósito realizado com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao realizar depósito: " + e.getMessage());
        }
    }

    public static void transferencia(Conta contaOrigem, Conta contaDestino, double valorTransferencia) {
        if(contaOrigem.getCnt_saldo() >= valorTransferencia) {

            Connection conn = Conexao.getConexaoTransacional();

            try {
                Extrato extratoOrigem = new Extrato();
                extratoOrigem.setExt_descricao("Transferência - saída | (Valor transferido para: "
                                                + contaDestino.getCnt_descricao() + ")");
                extratoOrigem.setExt_tipo("T");
                extratoOrigem.setExt_valor(valorTransferencia);
                extratoOrigem.setExt_cod_conta(contaOrigem.getCnt_codigo());
                extratoOrigem.setExt_data(new Date());

                Extrato extratoDestino = new Extrato();
                extratoDestino.setExt_descricao("Transferência - entrada | (Valor recebido de: "
                                                 + contaOrigem.getCnt_descricao() + ")");
                extratoDestino.setExt_tipo("T");
                extratoDestino.setExt_valor(valorTransferencia);
                extratoDestino.setExt_cod_conta(contaDestino.getCnt_codigo());
                extratoDestino.setExt_data(new Date());
    
                ExtratoDao extDao = new ExtratoDao(conn);
                extDao.insert(extratoOrigem);
                extDao.insert(extratoDestino);

                contaOrigem.setCnt_saldo(contaOrigem.getCnt_saldo() - valorTransferencia);
                contaDestino.setCnt_saldo(contaDestino.getCnt_saldo() + valorTransferencia);

                ContaDao cntDao = new ContaDao(conn);
                cntDao.update(contaOrigem);
                cntDao.update(contaDestino);

                conn.commit();

                System.out.println("Transferência realizada com sucesso!");
            } catch (Exception e) {
                System.err.println("Erro ao realizar a transferência!" + e.getMessage());
            }
        }
        else {
            System.err.println("Não há saldo suficiente!");
        }
    }

    public static void rendimento(Conta conta, double rendimento) {
        Connection conn = Conexao.getConexaoTransacional();

        try {
            Extrato extrato = new Extrato();
            extrato.setExt_descricao("Rendimento");
            extrato.setExt_tipo("R");
            extrato.setExt_valor(rendimento);
            extrato.setExt_cod_conta(conta.getCnt_codigo());
            extrato.setExt_data(new Date());

            ExtratoDao extDao = new ExtratoDao(conn);
            extDao.insert(extrato);

            double valorRendimento = rendimento/100;
            double valorARender = valorRendimento * conta.getCnt_saldo();
            conta.setCnt_saldo(conta.getCnt_saldo() + valorARender);

            ContaDao cntDao = new ContaDao(conn);
            cntDao.update(conta);

            conn.commit();
            System.out.println("Rendeu mais " + rendimento + "% no mês!");
        } catch (Exception e) {
            System.err.println("Erro ao processar rendimento!:  " + e.getMessage());
        }
    }
}