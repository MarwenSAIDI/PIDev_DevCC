/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

/**
 *
 * @author foura
 */
import Entite.consultation;
import UtilsBD.DataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;


import java.util.ResourceBundle;
import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import Entite.consultation;




public class serviceconsultation {
    private Connection con;
    private Statement ste;

    public serviceconsultation() {
        DataBase db=new DataBase();
        con = db.getConnection();

    }


    public void ajouter1(consultation p) 
    {
    PreparedStatement pre;
        try {
            pre = con.prepareStatement("INSERT INTO  consultation(idtherapeute,titre, description,emplacement,image,prix) VALUES ( ?, ?, ?,?,?,?);");
                            pre.setInt(1, p.getIdtherapeute());
            pre.setString(2, p.getTitre());
    pre.setString(3, p.getDescription());
        pre.setString(4, p.getEmplacement());
        pre.setString(5, p.getImage());
                pre.setDouble(6, p.getPrix());

    pre.executeUpdate();
        System.out.println("consultation ajouté");
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("problem");
        }
 
    }
    public void update(consultation p)
    {
        PreparedStatement pre;
        try {
            pre = con.prepareStatement( "UPDATE consultation SET idtherapeute=?,titre=?,description=?,emplacement=?,image=?,prix=? WHERE id=? ;");
                            pre.setInt(1, p.getIdtherapeute());
            pre.setString(2, p.getTitre());
    pre.setString(3, p.getDescription());
        pre.setString(4, p.getEmplacement());
                pre.setString(5, p.getImage());
                                pre.setDouble(6, p.getPrix());


        pre.setInt(7, p.getId());
        

    pre.executeUpdate();
        System.out.println("consultation modifié");
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("problem");
        }
        
    }
    public void delete(consultation p)
    {
          PreparedStatement pre;
        try {
            pre = con.prepareStatement( "DELETE FROM consultation  WHERE id=? ;");
        pre.setInt(1, p.getId());
        

    pre.executeUpdate();
        System.out.println("consultation supprimé");
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("problem");
        }
        
    }
        public List<consultation> Listconsultation() {
        List<consultation> Mylist = new ArrayList<>();
        try {
            String requete = "select * from consultation";
            PreparedStatement pst = con.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
           /*     p.setId(rs.getInt("id));
                p.setId(rs.getInt("id"));*/
           
                consultation p = new consultation();
                p.setId(rs.getInt("id"));
                System.out.println(rs.getInt("id"));
                                p.setId(rs.getInt("idtherapeute"));
                System.out.println(rs.getInt("idtherapeute"));

                p.setTitre(rs.getString("titre"));
                System.out.println(rs.getString("titre"));
                
                p.setDescription(rs.getString("description"));
                System.out.println(rs.getString("description"));
                
                              p.setEmplacement(rs.getString("emplacement"));
                                              System.out.println(rs.getString("emplacement"));

                p.setImage(rs.getString("image"));
                                                              System.out.println(rs.getString("image"));

                                p.setPrix( rs.getDouble("prix"));
                                                              System.out.println(rs.getDouble("prix"));

                Mylist.add(p);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Mylist;
    }
    /*  @FXML 
    private void modifier() {
    String query = "UPDATE consultation SET titre='"+TitreField.getText()+"',description='"+descField.getText()+"',emplacement="+empField.getText()
            +" WHERE id="+idField.getText()+"";
    executeQuery(query);
	showconsultation();
    }
    
    @FXML
    private void deleteButton() {
      //  TableView.getItems().removeAll(TableView.getSelectionModel().getSelectedItem());
    	String query = "DELETE FROM consultation WHERE id="+idField.getText()+"";
    	executeQuery(query);
    	showconsultation();
    }
    
    public void executeQuery(String query) {
    	Connection conn = getConnection();
    	Statement st;
    	try {
			st = conn.createStatement();
			st.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    public void initialize(URL location, ResourceBundle resources) {
    	showconsultation();
    }
    
    public Connection getConnection() {
    	Connection conn;
    	try {
    		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projet","root","");
    		return conn;
    	}
    	catch (Exception e){
    		e.printStackTrace();
    		return null;
    	}
    }
    
    public ObservableList<consultation> getconsultationList(){
    	ObservableList<consultation> consultationList = FXCollections.observableArrayList();
    	Connection connection = getConnection();
    	String query = "SELECT * FROM consultation ";
    	Statement st;
    	ResultSet rs;
    	
    	try {
			st = connection.createStatement();
			rs = st.executeQuery(query);
			consultation consultations;
			while(rs.next()) {
				consultations = new consultation(rs.getInt("id"),rs.getString("titre"),rs.getString("description"),rs.getString("emplacement"));
				consultationList.add(consultations);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return consultationList;
    }
*/
}
