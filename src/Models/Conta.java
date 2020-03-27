/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author marco
 */
public class Conta {

    private int cnt_codigo;
    private String cnt_descricao;
    private Double cnt_saldo;

    public String getCnt_descricao() {
        return cnt_descricao;
    }

    public int getCnt_codigo() {
        return cnt_codigo;
    }

    public void setCnt_codigo(int cnt_codigo) {
        this.cnt_codigo = cnt_codigo;
    }

    public void setCnt_descricao(String cnt_descricao) {
        this.cnt_descricao = cnt_descricao;
    }

    public double getCnt_saldo() {
        return cnt_saldo;
    }

    public void setCnt_saldo(Double cnt_saldo) {
        this.cnt_saldo = cnt_saldo;
    }

    @Override
    public String toString() {
        return "Conta {" + "Código = " + cnt_codigo + "\nDescrição = " + cnt_descricao + ", \nSaldo = " + cnt_saldo + '}';
    }

    public String[] toVetor() {
        String vetor[] = new String[3];
        vetor[0] = String.valueOf(getCnt_codigo());
        vetor[1] = getCnt_descricao();
        vetor[2] = String.valueOf(getCnt_saldo());
        return vetor;
    }

    public void vetorTo(String[] dados) {
        this.setCnt_codigo(Integer.parseInt(dados[0]));
        this.setCnt_descricao(dados[1]);
        this.setCnt_saldo(Double.parseDouble(dados[2]));
    }
}
