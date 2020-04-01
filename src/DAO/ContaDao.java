/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Models.Conta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import Conexao.Conexao;
import java.util.ArrayList;

/**
 *
 * @author marco
 */
public class ContaDao {

    private Connection minhaConexao;

    public ContaDao() throws SQLException {
        minhaConexao = Conexao.getConexaoSingleton();
    }

    public ContaDao(Connection conn) {
        minhaConexao = conn;
    }

    public boolean insert(Conta conta) {

        String sql = "INSERT INTO TBL_CONTA(cnt_descricao, cnt_saldo) VALUES (?, ?)";

        try {
            PreparedStatement pst = minhaConexao.prepareStatement(sql);
            pst.setString(1, conta.getCnt_descricao());
            pst.setDouble(2, conta.getCnt_saldo());

            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao salvar o registro " + e.getMessage());
        }
        return false;
    }

    public Conta recover(int id) {

        String sql = "SELECT cnt_codigo, cnt_descricao, cnt_saldo FROM TBL_CONTA WHERE cnt_codigo = ?";

        try {
            PreparedStatement stp = minhaConexao.prepareStatement(sql);
            stp.setInt(1, id);
            ResultSet result = stp.executeQuery();
            result.next();

            Conta conta = new Conta();
            conta.setCnt_codigo(result.getInt("cnt_codigo"));
            conta.setCnt_descricao(result.getString("cnt_descricao"));
            conta.setCnt_saldo(Double.parseDouble(result.getString("cnt_saldo")));

            return conta;
        } catch (SQLException e) {
            Logger.getLogger(ContaDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    public Boolean update(Conta conta) {
        String sql = "UPDATE TBL_CONTA SET cnt_descricao = ?, cnt_saldo = ? WHERE cnt_codigo = ?";
        try {

            PreparedStatement stp = minhaConexao.prepareStatement(sql);

            stp.setString(1, conta.getCnt_descricao());
            stp.setDouble(2, conta.getCnt_saldo());
            stp.setInt(3, conta.getCnt_codigo());

            stp.executeUpdate();
            return true;
        } catch (SQLException err) {
            System.err.println("Erro ao atualizar o objeto: " + err.getMessage());
            return false;
        }
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM TBL_CONTA WHERE cnt_id = ?";

        try {
            PreparedStatement stp = minhaConexao.prepareStatement(sql);

            stp.setInt(1, id);
            stp.execute();
            return true;
        } catch (SQLException err) {
            System.err.println("Erro ao deletar o objeto: " + err.getMessage());
        }
        return false;
    }

    public ArrayList<Conta> recoverAll() {
        String sql = "SELECT * FROM TBL_CONTA";
        ArrayList<Conta> contas = new ArrayList<Conta>();

        try {
            PreparedStatement stp = minhaConexao.prepareStatement(sql);

            ResultSet result = stp.executeQuery();

            while (result.next()) {
                    Conta conta = new Conta();

                    conta.setCnt_codigo(result.getInt("cnt_codigo"));
                    conta.setCnt_descricao(result.getString("cnt_descricao"));
                    conta.setCnt_saldo(result.getDouble("cnt_saldo"));

                    contas.add(conta);
            }
            return contas;

        } catch (SQLException ex) {

            System.err.println(ex.getMessage());
        }
        return null;
    }
}
