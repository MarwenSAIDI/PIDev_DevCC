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
    private int ID_User;
    private LocalDate Date_C;
    private LocalDate Date_U;
    private String Status_panier;

    public Panier() {
    }

    public Panier(int ID_Panier, int ID_User, LocalDate Date_C, LocalDate Date_U, String Status_panier) {
        this.ID_Panier = ID_Panier;
        this.ID_User = ID_User;
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

    public int getID_User() {
        return ID_User;
    }

    public void setID_User(int ID_User) {
        this.ID_User = ID_User;
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
        return "Panier{" + "ID_Panier=" + ID_Panier + ", ID_User=" + ID_User + ", Date_C=" + Date_C + ", Date_U=" + Date_U + ", Status_panier=" + Status_panier + "}\n";
    }

    
    
    
    
}
