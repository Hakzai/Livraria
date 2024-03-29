/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.dao;

import controller.connection.ConnectionFactory;
import javafx.collections.FXCollections;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.ObservableList;
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
    
    public ObservableList<String> getAllCategories()
    {
    	ObservableList<String> categoriesList = FXCollections.observableArrayList();
    	
		String sql = "SELECT DISTINCT lv_categoria FROM livros ORDER BY lv_categoria";
        
        PreparedStatement stmt = null;
        ResultSet result = null;
        
        try{
            stmt = conn.prepareStatement(sql);
            
            result = stmt.executeQuery();
            while(result.next())
            {
            	categoriesList.add(result.getString("lv_categoria"));
            }
            
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro DAO: " + ex);
            
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        } 
                
    	return categoriesList;
    }
    
}
