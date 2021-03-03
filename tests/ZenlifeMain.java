/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.Evenement;
import service.ServiceEvenement;
import utilis.DataBase;
import java.sql.*;
import java.util.List;
import java.util.Date;
import java.util.Calendar;


/**
 *
 * @author user
 */
public class ZenlifeMain {
    public static void main(String[] args) throws SQLException {
          DataBase db= DataBase.getInstance();

       Calendar c= Calendar.getInstance();
       Date dd = new Date();
       c.set(2021,02, 28);
       dd=c.getTime();
       
 ServiceEvenement ser = new ServiceEvenement();
//        Evenement e1= new Evenement(1,1,"meditation", "Apprendre la meditation","tunis","bonjour aujourd'hui nous allons a pprendre a mediter","f");
//        Evenement e1 = new Evenement(1,1,"meditation", "Apprendre la meditation","tunis","bonjour aujourd'hui nous allons a pprendre a mediter","f");
//         ser.ajouter1(e1);
//        Evenement e2 = new Evenement(29,65,"vxvfv", "Sortie velo","tunis","bonjour une sortie en velo est prevu","fdf") ;
//        ser.ajouter1(e2);
        

        
//ser.ModifierEvenement(e2);
        
//    ser.SupprimerEvenement(e1);
//    ser.SupprimerEvenement(e2);

//        try {
//            List<Evenement> list = ser.readAll();
//          
//            System.out.println(list);
//        } catch (SQLException ex) {
//            System.out.println(ex);
//        }
//        
        
    }
    
}