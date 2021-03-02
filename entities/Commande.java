/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.entities;

/**
 *
 * @author Occurence
 */
public class Commande {
    
    private int ID_Commande;
    private int ID_Produit;
    private int ID_Payment;
    private int Quantitee;
    private double prix;
    private String Status_commande;

    public Commande() {
    }

    public Commande(int ID_Commande, int ID_Produit, int ID_Payment, int Quantitee, double prix, String Status_commande) {
        this.ID_Commande = ID_Commande;
        this.ID_Produit = ID_Produit;
        this.ID_Payment = ID_Payment;
        this.Quantitee = Quantitee;
        this.prix = prix;
        this.Status_commande = Status_commande;
    }

    public int getID_Commande() {
        return ID_Commande;
    }

    public void setID_Commande(int ID_Commande) {
        this.ID_Commande = ID_Commande;
    }

    public int getID_Produit() {
        return ID_Produit;
    }

    public void setID_Produit(int ID_Produit) {
        this.ID_Produit = ID_Produit;
    }

    public int getID_Payment() {
        return ID_Payment;
    }

    public void setID_Payment(int ID_Payment) {
        this.ID_Payment = ID_Payment;
    }

    public int getQuantitee() {
        return Quantitee;
    }

    public void setQuantitee(int Quantitee) {
        this.Quantitee = Quantitee;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getStatus_commande() {
        return Status_commande;
    }

    public void setStatus_commande(String Status_commande) {
        this.Status_commande = Status_commande;
    }

    @Override
    public String toString() {
        return "Commande{" + "ID_Commande=" + ID_Commande + ", ID_Produit=" + ID_Produit + ", ID_Payment=" + ID_Payment + ", Quantitee=" + Quantitee + ", prix=" + prix + ", Status_commande=" + Status_commande + "}\n";
    }
    
    
    
}
