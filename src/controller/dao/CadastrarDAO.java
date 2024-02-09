/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.dao;

import controller.connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;
import javax.swing.JOptionPane;
import model.classe.Livro;

/**
 *
 * @author Codeiro
 */
public class CadastrarDAO {
    
    private Connection conn = null;
    
    public CadastrarDAO(){
        conn = ConnectionFactory.getConnection();
    }
    
    public boolean save (Livro l){
        boolean result = true;
        
        String sql = "INSERT INTO livros (LV_TITULO, LV_AUTOR, LV_EDITORA, LV_NUM_PAG, LV_CATEGORIA, LV_SITUACAO) VALUES (?, ?, ?, ?, ?, ?)";
        
        PreparedStatement stmt = null;
        
        try{
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, l.getTitulo());
            stmt.setString(2, l.getAutor());
            stmt.setString(3, l.getEditora());
            stmt.setInt(4, l.getNroPaginas());
            stmt.setString(5, l.getCategoria());
            stmt.setString(6, l.getSituacao());
            
            stmt.executeUpdate();
            
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro DAO: " + ex);
            
            result = false;
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        } 
                
        return result;
    }
    
}
