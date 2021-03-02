/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pidev.entities.Payment;
import pidev.tools.MyConnection;

/**
 *
 * @author Occurence
 */
public class PaymentCRUD {
    private Connection cnx;
    private PreparedStatement ste;

    public PaymentCRUD() {
        cnx = MyConnection.getInstance().getConnection();
    }
    
    public void Ajouter(Payment pay){
        String sql = "INSERT INTO payments (Prix_F, Mode_Payment) VALUES( ?, ?)";
        try {
            ste = cnx.prepareStatement(sql);
            ste.setDouble(1, pay.getPrix_F());
            ste.setString(2, pay.getMode_Payment());
            ste.executeUpdate();
            System.out.println("Payment ajoutée");
            
        } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());
        }
    }
    
    public List<Payment> AfficherPayment() throws SQLException {
        String query = "SELECT * FROM payments";
        ste = cnx.prepareStatement(query);
        
        ResultSet result = ste.executeQuery(query);
            
        List<Payment> payments = new ArrayList<>();
        while(result.next()){
            Payment pay = new Payment();
            pay.setID_Payment(result.getInt("ID_Payment"));
            pay.setPrix_F(result.getDouble("Prix_F"));
            pay.setMode_Payment(result.getString("Mode_Payment"));
            payments.add(pay);
        }
       
        return payments;
    }
    
    
    public void Modifier(Payment pay){
       
        String sql = "UPDATE payments SET Prix_F = ?, Mode_Payment = ? WHERE ID_Payment = ?";
        try {
            ste = cnx.prepareStatement(sql);
            ste.setDouble(1, pay.getPrix_F());
            ste.setString(2, pay.getMode_Payment());
            ste.setInt(3,pay.getID_Payment());
            ste.executeUpdate();
            System.out.println("Payment Modifier");
            
        } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());
        }
    }
    
    
    public void Supprimer(int ID){
        
        String sql = "DELETE FROM payments WHERE ID_Payment = ?";
        try {
            ste = cnx.prepareStatement(sql);
            ste.setInt(1, ID);
            
            ste.executeUpdate();
            System.out.println("Payment Supprimer");
            
        } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());
        }
    }
    
    
}
