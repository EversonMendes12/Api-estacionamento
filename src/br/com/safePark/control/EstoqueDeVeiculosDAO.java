/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.safePark.control;

import br.com.safePark.control.ClienteDAO;
import br.com.safePark.factory.ConnectionFactory02;
import br.com.safePark.model.Estacionamento;
import br.com.safePark.model.EstoqueDeVeiculos;
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
public class EstoqueDeVeiculosDAO {
    
    Connection conn;
    
    public EstoqueDeVeiculosDAO() {
        conn = ConnectionFactory02.getConnection();
    }
    
    public void cadastrar(EstoqueDeVeiculos p) {
        Estacionamento e = new Estacionamento();
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement("INSERT INTO veiculos (codigo,placa,cor,tipo,modelo,statu,codEstacionamento)VALUES(?,?,?,?,?,?,?)");
            stmt.setInt(1, p.getId());
            stmt.setString(2, p.getPlaca());
            stmt.setString(3, p.getCor());
            stmt.setString(4, p.getTipo());
            stmt.setString(5, p.getModelo());
            stmt.setString(6, p.getStatus());
            stmt.setInt(7, e.getId());
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory02.closeConnection(conn, stmt);
        }

    }
    
    public List<EstoqueDeVeiculos> read() {

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<EstoqueDeVeiculos> veiculos = new ArrayList<>();
       

        try {
            stmt = conn.prepareStatement("SELECT * FROM veiculos");
            rs = stmt.executeQuery();

            while (rs.next()) {

                EstoqueDeVeiculos veiculo = new EstoqueDeVeiculos();
                Estacionamento estacionamento = new Estacionamento();
 
                veiculo.setId(rs.getInt("id"));
                veiculo.setPlaca(rs.getString("placa"));
                veiculo.setCor(rs.getString("cor"));
                veiculo.setTipo(rs.getString("tipo"));
                veiculo.setModelo(rs.getString("modelo"));
                veiculo.setStatus(rs.getString("status"));
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
    
    public List<EstoqueDeVeiculos> readForDesc(String desc) {

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<EstoqueDeVeiculos> estoqueDeVeiculos = new ArrayList<>();

        try {
            stmt = conn.prepareStatement("SELECT * FROM veiculos WHERE descricao LIKE ?");
            stmt.setString(1, "%"+desc+"%");
            
            rs = stmt.executeQuery();

            while (rs.next()) {

                EstoqueDeVeiculos estoqueDeVeiculo = new EstoqueDeVeiculos();

                estoqueDeVeiculo.setId(rs.getInt("id"));
                estoqueDeVeiculo.setCpf(rs.getString("cpf"));
                estoqueDeVeiculo.setPlaca(rs.getString("placa"));
                
            }

        } catch (SQLException ex) {
            Logger.getLogger(EstoqueDeVeiculosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory02.closeConnection(conn, stmt, rs);
        }

        return estoqueDeVeiculos;

    }
    
  
    public void update(EstoqueDeVeiculos p) {
        Estacionamento e = new Estacionamento();

        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement("UPDATE veiculos SET cpf = ?,nome = ?,placa = ?,cor = ?,tipo = ?,status = ?,idEstacionamento = ?,modelo = ? WHERE id");
            
            stmt.setString(1, p.getCpf());
            stmt.setString(2, p.getNome());
            stmt.setString(3, p.getPlaca());
            stmt.setString(4, p.getCor());
            stmt.setString(5, p.getTipo());
            stmt.setString(6, p.getStatus());
            stmt.setInt(7, e.getId());
            stmt.setString(8, p.getModelo());
            stmt.setInt(9, p.getId());

           

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            ConnectionFactory02.closeConnection(conn, stmt);
        }

    }
    public void delete(EstoqueDeVeiculos p) {

        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement("DELETE FROM veiculos WHERE id = ?");
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
