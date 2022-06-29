package br.com.safePark.control;

import br.com.safePark.factory.ConnectionFactory02;
import br.com.safePark.model.Cliente;
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
 * @author assparremberger
 */
public class ClienteDAO {

    Connection conn;
    
    public ClienteDAO() {
        conn = ConnectionFactory02.getConnection();
    }
    
    public void cadastrar(Cliente p) {
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement("INSERT INTO clientes (nome,telefone,email,nascimento,"
            + "cpf,rg,sexo,uf,cidade,senha,endereco,numero,complemento)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getTelefone());
            stmt.setString(3, p.getEmail());
            stmt.setString(4, p.getDataNascimento());
            stmt.setString(5, p.getCpf());
            stmt.setString(6, p.getRg());
            stmt.setString(7, p.getSexo());
            stmt.setString(8, p.getUf());
            stmt.setString(9, p.getCidade());
            stmt.setString(10, p.getSenha());
            stmt.setString(11, p.getEndereco());
            stmt.setString(12, p.getNumero());
            stmt.setString(13, p.getComplemento());
            

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConnectionFactory02.closeConnection(conn, stmt);
        }

    }
    
    public List<Cliente> read() {

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Cliente> clientes = new ArrayList<>();
       

        try {
            stmt = conn.prepareStatement("SELECT * FROM cliente");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setEmail(rs.getString("email"));
                cliente.setDataNascimento(rs.getString("dataNascimento"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setRg(rs.getString("rg"));
                cliente.setSexo(rs.getString("sexo"));
                cliente.setUf(rs.getString("uf"));
                cliente.setCidade(rs.getString("cidade"));
                cliente.setSenha(rs.getString("senha"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setNumero(rs.getString("numero"));
                cliente.setComplemento(rs.getString("complemento"));

                clientes.add(cliente);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory02.closeConnection(conn, stmt, rs);
        }

        return clientes;

    }  

    public void update(Cliente p) {

        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement("UPDATE veiculo SET nome = ?,telefone = ?,"
                    + "email = ?,dataNascimento = ?, cpf = ?, rg = ?, sexo = ?, "
                    + "uf = ?,cidade = ?, senha = ?,endereco = ?,numero = ?,complemento = ? WHERE id");
            
            stmt.setInt(1, p.getId());
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getTelefone());
            stmt.setString(3, p.getEmail());
            stmt.setString(4, p.getDataNascimento());
            stmt.setString(5, p.getCpf());
            stmt.setString(6, p.getRg());
            stmt.setString(7, p.getSexo());
            stmt.setString(8, p.getUf());
            stmt.setString(9, p.getCidade());
            stmt.setString(10, p.getSenha());
            stmt.setString(11, p.getEndereco());
            stmt.setString(12, p.getNumero());
            stmt.setString(13, p.getComplemento());
            stmt.setInt(14, p.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            ConnectionFactory02.closeConnection(conn, stmt);
        }

    }
    public void delete(Cliente p) {

        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement("DELETE FROM cliente WHERE id = ?");
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
