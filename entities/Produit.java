/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author aymen romdhani
 */
public class Produit {
    private int ID;
    private String nom;
    private String type;
    private int quantite;
    private float prix;

    public Produit(int ID, String nom, String type, int quantite, float prix) {
        this.ID =ID;
        this.nom =nom;
        this.type =type;
        this.quantite =quantite;
        this.prix =prix;
    }

    public Produit (){
         
       
    }

    public int getID() {
        return ID;
    }

    public String getNom() {
        return nom;
    }

    public String getType() {
        return type;
    }

    public int getQuantite() {
        return quantite;
    }

    public float getPrix() {
        return prix;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

  

    @Override
    public String toString() {
        return "Produit{" + "ID=" + ID + ", nom=" + nom + ", type=" + type + ", quantite=" + quantite + ", prix=" + prix + "}\n";
    }
    
    
}
