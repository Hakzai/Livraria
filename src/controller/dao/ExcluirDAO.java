/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.dao;

import controller.connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.classe.Livro;

/**
 *
 * @author Codeiro
 */
public class ExcluirDAO {
    
    private Connection conn = null;
    
    public ExcluirDAO(){
        conn = ConnectionFactory.getConnection();
    }
    
   
    // AQUI NÃO DELETAMOS DE FATO, APENAS TIRAMOS A VISIBILIDADE DELE NA APLICAÇÃO
    public boolean delete(int id){
        boolean result = true;
        
        String sql = "UPDATE livros SET LV_STATUS = 'N' WHERE LV_ID = ?";
        
        PreparedStatement stmt = null;
        
        try{
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro DAO Delete: " + ex);
            
            return result;
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }
        
        return result;
    }
    
}
