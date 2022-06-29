/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.parking.model;

/**
 *
 * @author Administrador
 */
public class Veiculo {
    private int id;
    private String cpf;
    private String modelo;
    private String placa;
    private String cor;
    private String tipo;
    private String combustivel;
    private Estacionamento estacionamento;
    private MarcaMoto marcaMoto;
    private Marcacarro marcaCarro;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(String combustivel) {
        this.combustivel = combustivel;
    }

    public Estacionamento getEstacionamento() {
        return estacionamento;
    }

    public void setEstacionamento(Estacionamento estacionamento) {
        this.estacionamento = estacionamento;
    }

    public MarcaMoto getMarcaMoto() {
        return marcaMoto;
    }

    public void setMarcaMoto(MarcaMoto marcaMoto) {
        this.marcaMoto = marcaMoto;
    }

    public Marcacarro getMarcaCarro() {
        return marcaCarro;
    }

    public void setMarcaCarro(Marcacarro marcaCarro) {
        this.marcaCarro = marcaCarro;
    }
}
