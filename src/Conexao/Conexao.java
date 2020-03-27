/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
public class Conexao {
 
    private static final String STR_DRIVER = "org.gjt.mm.mysql.Driver";  // definiÃ§Ã£o de qual banco serÃ¡ utilizado
    private static final String DATABASE = "db_banco"; // Nome do banco de dados         
    private static final String IP = "172.24.18.174";  // ip de conexao
    private static final String STR_CON = "jdbc:mysql://" + IP + ":3306/" + DATABASE; // string de conexao com o banco de dados
    private static final String USER = "root"; // Nome do usuÃ¡rio
    private static final String PASSWORD = "root"; // senha
    private static Connection conn = null;
 
    public Conexao() throws SQLException {
        try{
            Class.forName(STR_DRIVER);
            conn = DriverManager.getConnection(STR_CON, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException err) {   
            String errorMsg = "Driver nao encontrado: " + err.getMessage();    
            System.err.println("Erro na construção da Conexão: " + errorMsg);
        }      
    }
 
    public static Connection getConexaoSingleton() throws SQLException  {
        if (conn == null) {
            Conexao connGlobal = new Conexao();
        }
        return conn;
    }
    
    public static Connection getConexaoTransacional() {
        Connection conn = null;
        try {
            Class.forName(STR_DRIVER);
            conn = DriverManager.getConnection(STR_CON, USER, PASSWORD);
            
            conn.setAutoCommit(false);
            conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        } catch (Exception err) {
            System.err.println("Erro na construção da Conexão: " + err.getMessage());
        }
        return conn;
    }   
}