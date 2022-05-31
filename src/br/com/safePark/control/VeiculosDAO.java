/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.safePark.control;


import br.com.safePark.factory.ConnectionFactory02;
import br.com.safePark.model.Estacionamento;
import br.com.safePark.model.MarcaMoto;
import br.com.safePark.model.Marcacarro;
import br.com.safePark.model.Veiculo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrador
 */
public class VeiculosDAO {
    
    
    Connection conn;
    
    public VeiculosDAO() {
        conn = ConnectionFactory02.getConnection();
    }
    
    public void cadastrar(Veiculo p) {
        Estacionamento e = new Estacionamento();
        Marcacarro mc = new Marcacarro();
        MarcaMoto mm = new MarcaMoto();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement("INSERT INTO veiculo (cpf,placa,cor,tipo,combustivel,idEstacionamento,idmarcaCarro,idMarcaMoto)VALUES(?,?,?,?,?,?,?,?)");
            stmt.setString(1, p.getCpf());
            stmt.setString(2, p.getPlaca());
            stmt.setString(3, p.getCor());
            stmt.setString(4, p.getTipo());
            stmt.setString(5, p.getCombustivel());
            stmt.setInt(6, e.getId());
            stmt.setInt(7, mc.getId());
            stmt.setInt(8, mm.getId());
            

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory02.closeConnection(conn, stmt);
        }

    }
    
    public List<Veiculo> read() {

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Veiculo> veiculos = new ArrayList<>();
       

        try {
            stmt = conn.prepareStatement("SELECT * FROM veiculo");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Veiculo veiculo = new Veiculo();
                Estacionamento estacionamento = new Estacionamento();
                MarcaMoto marcamoto = new MarcaMoto();
                Marcacarro marcacarro = new Marcacarro();
                
                veiculo.setId(rs.getInt("id"));
                veiculo.setCpf(rs.getString("cpf"));
                veiculo.setPlaca(rs.getString("placa"));
                veiculo.setCor(rs.getString("cor"));
                veiculo.setTipo(rs.getString("tipo"));
                veiculo.setCombustivel(rs.getString("combustivel"));
                marcacarro.setId(rs.getInt("idMarcaCarro"));
                marcamoto.setId(rs.getInt("idMarcaMoto"));
                estacionamento.setId(rs.getInt("idEstacionamento"));
                

                veiculos.add(veiculo);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory02.closeConnection(conn, stmt, rs);
        }

        return veiculos;

    }
    
  
    public void update(Veiculo p) {
        Estacionamento e = new Estacionamento();
        MarcaMoto mm = new MarcaMoto();
        Marcacarro mc = new Marcacarro();

        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement("UPDATE veiculo SET cpf = ?,placa = ?,cor = ?,tipo = ?,combustivel = ?,idEstacionamento = ?,idMarcaCarro = ?, idMarcaMoto = ? WHERE id");
            
            stmt.setString(1, p.getCpf());
            stmt.setString(2, p.getPlaca());
            stmt.setString(3, p.getCor());
            stmt.setString(4, p.getTipo());
            stmt.setString(5, p.getCombustivel());
            stmt.setInt(6, e.getId());
            stmt.setInt(8, mm.getId());
            stmt.setInt(7, mc.getId());
            
            stmt.setInt(9, e.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            ConnectionFactory02.closeConnection(conn, stmt);
        }

    }
    public void delete(Veiculo p) {

        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement("DELETE FROM veiculo WHERE id = ?");
            stmt.setInt(1, p.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            ConnectionFactory02.closeConnection(conn, stmt);
        }

    }
}
