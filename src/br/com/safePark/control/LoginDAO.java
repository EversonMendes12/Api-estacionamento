/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.safePark.control;

import br.com.safePark.factory.ConnectionFactory02;
import br.com.safePark.model.UserControll;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author evers
 */
public class LoginDAO {
    
    
        public void cadastrar(UserControll u) {
        
        Connection con = ConnectionFactory02.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean validaUser = false;
                                
                            //Verfifica se o usuário já existe no banco de dados.
                            try {
                             stmt = con.prepareStatement("SELECT * FROM users WHERE cpf = ?");
                             stmt.setString(1, u.getCpf());
                             rs = stmt.executeQuery();

                                    if (rs.next()) {
                                        validaUser = false;
                                        JOptionPane.showMessageDialog(null, "Usuário: " + u.getCpf() + " já cadastrado!");
                                    }else {
                                        validaUser = true;
                                    }

                            } catch (Exception e) {
                            }
                     
                    //Se usuário não existe, validaUser retorna TRUE.
                    if (validaUser) {
                            try {
                                stmt = con.prepareStatement("INSERT INTO users (user,name,email,telefone,endereco,cpf,rg,sexo,password)VALUES(?,?,?,?,?,?,?,?,?)");
                                stmt.setString(1, u.getUser());
                                stmt.setString(2, u.getName());
                                stmt.setString(3, u.getEmail());
                                stmt.setString(4, u.getTelefone());
                                stmt.setString(5, u.getEndereco());
                                stmt.setString(6, u.getCpf());
                                stmt.setString(7, u.getRg());
                                stmt.setString(8, u.getSexo());
                                stmt.setString(9, String.valueOf(u.getPassword()));

                                stmt.executeUpdate();
                                //System.out.println("Salvo com sucesso!");
                             JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
                            } catch (SQLException ex) {
                                System.out.println(ex);
                            } finally {
                                ConnectionFactory02.closeConnection(con, stmt);
                            }
                    }
        }
    
        public boolean checkLogin(String user, String password) {

        Connection conn = ConnectionFactory02.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        boolean check = false;

        try {

            stmt = conn.prepareStatement("SELECT * FROM users WHERE user = ? and password = ?");
            stmt.setString(1, user);
            stmt.setString(2, password);

            rs = stmt.executeQuery();

            if (rs.next()) {

                
                check = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory02.closeConnection(conn, stmt, rs);
        }

        return check;

    }
    
}
