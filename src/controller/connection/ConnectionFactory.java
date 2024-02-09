/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Codeiro
 */
public class ConnectionFactory {

    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/livraria";
    public static final String USER = "root";
    public static final String PASS = "";
    
    public static Connection getConnection()
    {
        //Connection conn = null;
        try{
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);

        }catch(ClassNotFoundException | SQLException ex){
            throw new RuntimeException("Erro na conexão", ex);
        }
        
    }
    
    public static void closeConnection(Connection conn)
    {
        if(conn != null)
            try {
                conn.close();
        } catch (SQLException ex) {                
            JOptionPane.showMessageDialog(null, "Erro close: " + ex);;
        }
    }
    
    public static void closeConnection(Connection conn, PreparedStatement stmt)
    {
        
        closeConnection(conn);   
        
        if(stmt != null){
                try {
                    stmt.close();
            } catch (SQLException ex) {                
                JOptionPane.showMessageDialog(null, "Erro close: " + ex);;
            }
        }
    }
    
    public static void closeConnection(Connection conn, PreparedStatement stmt, ResultSet rs)
    {

        closeConnection(conn, stmt);  
        
        if(rs != null){
                try {
                    rs.close();
            } catch (SQLException ex) {                
                JOptionPane.showMessageDialog(null, "Erro close: " + ex);;
            }
        }
    }
}

