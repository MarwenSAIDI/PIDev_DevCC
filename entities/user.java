/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

/**
 *
 * @author foura
 */
public class user {
 private String cin;   
private String  email;
private String  nom;
private String  prenom;
private String password;
private String  type;
private String numtel;
private String adresse;
private String image;

    public user() {
    }

    public user(String cin, String email, String nom, String prenom, String password, String type, String numtel, String adresse, String image) {
        this.cin = cin;
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.type = type;
        this.numtel = numtel;
        this.adresse = adresse;
        this.image = image;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumtel() {
        return numtel;
    }

    public void setNumtel(String numtel) {
        this.numtel = numtel;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "user{" + "nom=" + nom + ", prenom=" + prenom + '}';
    }

}
