/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.dao;

import controller.connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.classe.Livro;

/**
 *
 * @author Codeiro
 */
public class EditarDAO {
    
    private Connection conn = null;
    
    public EditarDAO(){
        conn = ConnectionFactory.getConnection();
    }
    
    public boolean update(Livro l){
        boolean result = true;
        
        String sql = "UPDATE livros SET LV_TITULO = ?, LV_AUTOR = ?, LV_EDITORA = ?, LV_NUM_PAG = ?, LV_CATEGORIA = ?, LV_SITUACAO = ? WHERE LV_ID = ?";
        
        PreparedStatement stmt = null;
        
        try{
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, l.getTitulo());
            stmt.setString(2, l.getAutor());
            stmt.setString(3, l.getEditora());
            stmt.setInt(4, l.getNroPaginas());
            stmt.setString(5, l.getCategoria());
            stmt.setString(6, l.getSituacao());
            stmt.setInt(7, l.getId());
            
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro DAO Update: " + ex);
            
            return result;            
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }
        
        return result;
    }
    
    public Livro findById(int id){
        
        String sql = "SELECT * FROM Livros WHERE LV_ID = (?)";
                
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        Livro livro = null;
        
        try{
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                livro = new Livro();
                
                livro.setId(rs.getInt("LV_ID"));
                livro.setTitulo(rs.getString("LV_TITULO"));
                livro.setAutor(rs.getString("LV_AUTOR"));
                livro.setEditora(rs.getString("LV_EDITORA"));
                livro.setNroPaginas(rs.getInt("LV_NUM_PAG"));
                livro.setCategoria(rs.getString("LV_CATEGORIA"));
                livro.setSituacao(rs.getString("LV_SITUACAO"));
            }
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro DAO findById: " + ex);
        } finally{
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }
        
        return livro;
    }    
    
}
