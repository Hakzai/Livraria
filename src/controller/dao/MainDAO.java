/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.dao;

import model.classe.Livro;
import controller.connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Codeiro
 */
public class MainDAO {
    
    private Connection conn = null;
    
    public MainDAO(){
        conn = ConnectionFactory.getConnection();
    }
    
    public List<Livro> listar(){
        String sql = "SELECT * FROM livros WHERE lv_status = 'Y' ORDER BY lv_titulo";
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Livro> mainList = new ArrayList<>();
        
        try{
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()){
                Livro main = new Livro();
                
                main.setId(rs.getInt("LV_ID"));
                main.setTitulo(rs.getString("LV_TITULO"));
                main.setAutor(rs.getString("LV_AUTOR"));
                main.setEditora(rs.getString("LV_EDITORA"));
                main.setNroPaginas(rs.getInt("LV_NUM_PAG"));
                main.setCategoria(rs.getString("LV_CATEGORIA"));
                main.setSituacao(rs.getString("LV_SITUACAO"));
                
                mainList.add(main);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro DAO Listar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }
        
        return mainList;
    }
    
}
