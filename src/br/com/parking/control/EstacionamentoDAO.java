/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.parking.control;

import br.com.parking.factory.ConnectionFactory02;
import br.com.parking.model.Estacionamento;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrador
 */
public class EstacionamentoDAO {
    String mensagem;
    Connection conn;
    
    public EstacionamentoDAO() {
        conn = ConnectionFactory02.getConnection();
    }
    
    public void cadastrar(Estacionamento p) {
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement("INSERT INTO estacionamentos (codigo, nomeDaFilial,uf,cidade,endereco,numero,cep)VALUES(?,?,?,?,?,?,?)");
            stmt.setInt(1, p.getId());
            stmt.setString(2, p.getNomeEstacionamento());
            stmt.setString(3, p.getUf());
            stmt.setString(4, p.getCidade());
            stmt.setString(5, p.getEndereco());
            stmt.setString(6, p.getNumero());
            stmt.setString(7, p.getCep());

            stmt.executeUpdate();
            
            mensagem = ("SALVO COM SUCESSO!");
            
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory02.closeConnection(conn, stmt);
        }

    }

    public List<Estacionamento> read() {

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Estacionamento> estacionamentos = new ArrayList<>();

        try {
            stmt = conn.prepareStatement("SELECT * FROM estacionamentos");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Estacionamento estacionamento = new Estacionamento();

                estacionamento.setId(rs.getInt("codigo"));
                estacionamento.setUf(rs.getString("uf"));
                estacionamento.setCidade(rs.getString("cidade"));
                estacionamento.setEndereco(rs.getString("endereco"));
                estacionamento.setNumero(rs.getString("numero"));
                estacionamento.setCep(rs.getString("cep"));
                estacionamento.setNomeEstacionamento(rs.getString("nomeDaFilial"));
                estacionamentos.add(estacionamento);
            }

        } catch (SQLException ex) {
            Logger.getLogger(EstacionamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory02.closeConnection(conn, stmt, rs);
        }

        return estacionamentos;

    }
    public List<Estacionamento> readForDesc(String desc) {

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Estacionamento> estacionamentos = new ArrayList<>();

        try {
            stmt = conn.prepareStatement("SELECT * FROM estacionamentos WHERE cpf LIKE ?");
            stmt.setString(1, "%"+desc+"%");
            
            rs = stmt.executeQuery();

            while (rs.next()) {

                Estacionamento estacionamento = new Estacionamento();

                estacionamento.setId(rs.getInt("codigo"));
                estacionamento.setUf(rs.getString("uf"));
                estacionamento.setNomeEstacionamento(rs.getString("nomeDaFilial"));
                estacionamento.setCidade(rs.getString("cidade"));
                estacionamento.setCep(rs.getString("cep"));
                estacionamento.setEndereco(rs.getString("endereco"));
                estacionamento.setNumero(rs.getString("numero"));
                estacionamentos.add(estacionamento);
            }

        } catch (SQLException ex) {
            Logger.getLogger(EstacionamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory02.closeConnection(conn, stmt, rs);
        }

        return estacionamentos;

    }

    public void update(Estacionamento p) {

        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement("UPDATE estacionamentos SET uf = ? ,nomeDaFilial = ?,cidade = ?,endereco = ?,numero = ?, cep = ? WHERE codigo = ?");
            stmt.setString(1, p.getUf());
            stmt.setString(2, p.getNomeEstacionamento());
            stmt.setString(3, p.getCidade());
            stmt.setString(4, p.getEndereco());
            stmt.setString(5, p.getNumero());
            stmt.setString(6, p.getCep());
            stmt.setInt(7, p.getId());

            stmt.executeUpdate();
            
            mensagem = ("Atualizado com sucesso!");
        } catch (SQLException ex) {
            mensagem = ("Erro ao atualizar: " + ex);
        } finally {
            ConnectionFactory02.closeConnection(conn, stmt);
        }

    }
    public void delete(Estacionamento p) {

        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement("DELETE FROM estacionamentos WHERE codigo = ?");
            stmt.setInt(1, p.getId());

            stmt.executeUpdate();
            mensagem = ("Excluido com sucesso!");
        } catch (SQLException ex) {
            mensagem = ("Erro ao excluir:: " + ex);
        } finally {
            ConnectionFactory02.closeConnection(conn, stmt);
        }

    }   
    
    public static List<Estacionamento> listar(){
        String query = "SELECT codigo , nome "
                    + " FROM estacionamentos ORDER BY nomeEstacionamentos";
        List<Estacionamento> listaDeEstacionamento = new ArrayList<Estacionamento>(); 
        try {
            Class.forName(ConnectionFactory02.DRIVER);
            Connection conn = DriverManager.getConnection
                (ConnectionFactory02.conn, ConnectionFactory02.USUARIO, ConnectionFactory02.SENHA);
            Statement st = conn.createStatement();
            
            ResultSet rs = st.executeQuery(query);  
            
          
            while ( rs.next() ) {                
                Estacionamento c = new Estacionamento();
                c.setId(rs.getInt("codigo") );
                c.setNomeEstacionamento(rs.getString("nomeDaFilial"));
                
                listaDeEstacionamento.add( c );
                
            }  
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return listaDeEstacionamento;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    
}
