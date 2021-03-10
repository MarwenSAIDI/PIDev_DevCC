/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UtilsBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author foura
 */
public class DataBase {
    

    
      public Connection getConnection() {
    	Connection conn;
    	try {
    		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projettest","root","");
    		return conn;
    	}
    	catch (Exception e){
    		e.printStackTrace();
    		return null;
    	}
    }
      
}