/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Objects;

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
    private String image;

    public Produit(int ID, String nom, String type, int quantite, float prix, String image) {
        this.ID =ID;
        this.nom =nom;
        this.type =type;
        this.quantite =quantite;
        this.prix =prix;
        this.image=image;
                
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Produit{" + "ID=" + ID + ", nom=" + nom + ", type=" + type + ", quantite=" + quantite + ", prix=" + prix + ", image=" + image + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + this.ID;
        hash = 47 * hash + Objects.hashCode(this.nom);
        hash = 47 * hash + Objects.hashCode(this.type);
        hash = 47 * hash + this.quantite;
        hash = 47 * hash + Float.floatToIntBits(this.prix);
        hash = 47 * hash + Objects.hashCode(this.image);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Produit other = (Produit) obj;
        if (this.ID != other.ID) {
            return false;
        }
        if (this.quantite != other.quantite) {
            return false;
        }
        if (Float.floatToIntBits(this.prix) != Float.floatToIntBits(other.prix)) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.image, other.image)) {
            return false;
        }
        return true;
    }

  

   
    
}
