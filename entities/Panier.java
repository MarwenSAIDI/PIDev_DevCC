/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.entities;
import java.time.LocalDate;


/**
 *
 * @author Occurence
 */
public class Panier {
    
    private int ID_Panier;
    private int ID_Commande;
    private LocalDate Date_C;
    private LocalDate Date_U;
    private String Status_panier;

    public Panier() {
    }

    public Panier(int ID_Panier, int ID_Commande, LocalDate Date_C, LocalDate Date_U, String Status_panier) {
        this.ID_Panier = ID_Panier;
        this.ID_Commande = ID_Commande;
        this.Date_C = Date_C;
        this.Date_U = Date_U;
        this.Status_panier = Status_panier;
    }

    public int getID_Panier() {
        return ID_Panier;
    }

    public void setID_Panier(int ID_Panier) {
        this.ID_Panier = ID_Panier;
    }

    public int getID_Commande() {
        return ID_Commande;
    }

    public void setID_Commande(int ID_Commande) {
        this.ID_Commande = ID_Commande;
    }

    public LocalDate getDate_C() {
        return Date_C;
    }

    public void setDate_C(LocalDate Date_C) {
        this.Date_C = Date_C;
    }

    public LocalDate getDate_U() {
        return Date_U;
    }

    public void setDate_U(LocalDate Date_U) {
        this.Date_U = Date_U;
    }

    public String getStatus_panier() {
        return Status_panier;
    }

    public void setStatus_panier(String Status_panier) {
        this.Status_panier = Status_panier;
    }

    @Override
    public String toString() {
        return "Panier{" + "ID_Panier=" + ID_Panier + ", ID_Commande=" + ID_Commande + ", Date_C=" + Date_C + ", Date_U=" + Date_U + ", Status_panier=" + Status_panier + "}\n";
    }
    
    
    
}
