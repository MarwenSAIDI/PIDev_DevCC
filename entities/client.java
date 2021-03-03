/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crudvfinal.entities;

/**
 *
 * @author yassi
 */
public class client extends User {

    public client() {
    }

    public client(int cin, String email, String nom, String prenom, String password) {
        super(cin, email, nom, prenom, password);
    }
    
    
}
