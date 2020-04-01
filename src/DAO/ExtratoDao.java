/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Models.Extrato;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import Conexao.Conexao;
import java.util.ArrayList;

/**
 *
 * @author marco
 */
public class ExtratoDao {

    private Connection minhaConexao;

    public ExtratoDao() throws SQLException {
        minhaConexao = Conexao.getConexaoSingleton();
    }

    public ExtratoDao(Connection conn) {
        minhaConexao = conn;
    }

    public boolean insert(Extrato extrato) {

        String sql = "INSERT INTO TBL_EXTRATO(ext_tipo, ext_descricao, ext_valor, ext_cod_CONTA, ext_data) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement pst = minhaConexao.prepareStatement(sql);

            pst.setString(1, extrato.getExt_tipo());
            pst.setString(2, extrato.getExt_descricao());
            pst.setString(3, String.valueOf(extrato.getExt_valor()));
            pst.setString(4, String.valueOf(extrato.getExt_cod_conta()));
            pst.setString(5, extrato.getExt_data());

            pst.executeUpdate();
            return true;
        } catch (SQLException err) {
            System.err.println("Erro ao salvar o registro " + err.getMessage());
        }
        return false;
    }

    public Extrato recover(int id) {

        String sql = "SELECT * FROM TBL_EXTRATO WHERE ext_codigo = ?";

        try {
            PreparedStatement stp = minhaConexao.prepareStatement(sql);
            stp.setInt(1, id);
            ResultSet result = stp.executeQuery();
            result.next();

            Extrato extrato = new Extrato();

            extrato.setExt_tipo(result.getString("ext_descricao"));
            extrato.setExt_descricao(result.getString("ext_descricao"));
            extrato.setExt_valor(Double.parseDouble(result.getString("ext_valor")));
            extrato.setExt_cod_conta(result.getInt("ext_descricao"));

            return extrato;
        } catch (SQLException err) {
            System.err.println("Erro ao recuperar o registro " + err.getMessage());
        }
        return null;
    }

    public void update(Extrato extrato) {
        String sql = "UPDATE TBL_EXTRATO SET ext_codigo = ?, ext_tipo = ?, ext_descricao = ?, ext_valor = ?, ext_cod_CONTA = ? WHERE ext_codigo = ?";

        try {
            PreparedStatement stp = minhaConexao.prepareStatement(sql);

            stp.setInt(1, extrato.getExt_codigo());
            stp.setString(2, extrato.getExt_tipo());
            stp.setString(3, extrato.getExt_descricao());
            stp.setDouble(4, extrato.getExt_valor());
            stp.setDouble(5, extrato.getExt_cod_conta());

            stp.executeUpdate();

        } catch (SQLException err) {
            System.err.println("Erro ao atualizar o objeto: " + err.getMessage());
        }
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM TBL_EXTRATO WHERE ext_codigo = ?";

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

    public ArrayList<Extrato> recoverAll() {
        String sql = "SELECT * FROM TBL_EXTRATO";
        ArrayList<Extrato> extratos = new ArrayList<Extrato>();

        try {
            PreparedStatement stp = minhaConexao.prepareStatement(sql);

            ResultSet result = stp.executeQuery();

            while (result.next()) {
                Extrato extrato = new Extrato();

                extrato.setExt_codigo(result.getInt("ext_codigo"));
                extrato.setExt_tipo(result.getString("ext_tipo"));
                extrato.setExt_descricao(result.getString("ext_descricao"));
                extrato.setExt_valor(result.getDouble("ext_valor"));
                extrato.setExt_cod_conta(result.getInt("ext_cod_ALUNO"));

                extratos.add(extrato);
            }
            return extratos;

        } catch (SQLException ex) {

            System.err.println(ex.getMessage());
        }
        return null;
    }
}
