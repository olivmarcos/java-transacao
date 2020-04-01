/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author marco
 */
public class Extrato {

    private int ext_codigo;
    private String ext_tipo;
    private String ext_descricao;
    private Double ext_valor;
    private int ext_cod_conta;
    private String ext_data;

    public int getExt_codigo() {
        return ext_codigo;
    }

    public void setExt_codigo(int ext_codigo) {
        this.ext_codigo = ext_codigo;
    }

    public String getExt_tipo() {
        return ext_tipo;
    }

    public void setExt_tipo(String ext_tipo) {
        this.ext_tipo = ext_tipo;
    }

    public String getExt_descricao() {
        return ext_descricao;
    }

    public void setExt_descricao(String ext_descricao) {
        this.ext_descricao = ext_descricao;
    }

    public Double getExt_valor() {
        return ext_valor;
    }

    public void setExt_valor(Double ext_valor) {
        this.ext_valor = ext_valor;
    }

    public int getExt_cod_conta() {
        return ext_cod_conta;
    }

    public void setExt_cod_conta(int ext_cod_conta) {
        this.ext_cod_conta = ext_cod_conta;
    }

    public String getExt_data()
    {
        return ext_data;
    }

    public void setExt_data(Date ext_data){
        String data = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(ext_data);
        this.ext_data = data;
    }


    public String[] toVetor() {
        String vetor[] = new String[4];
        vetor[0] = String.valueOf(getExt_codigo());
        vetor[1] = getExt_tipo();
        vetor[2] = getExt_descricao();
        vetor[3] = String.valueOf(getExt_valor());
        vetor[4] = String.valueOf(getExt_cod_conta());

        return vetor;
    }

    public void vetorTo(String[] dados) {
        this.setExt_codigo(Integer.parseInt(dados[0]));
        this.setExt_descricao(dados[1]);
        this.setExt_tipo(dados[2]);
        this.setExt_valor(Double.parseDouble(dados[3]));
        this.setExt_cod_conta(Integer.parseInt(dados[4]));
    }
}
