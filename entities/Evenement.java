/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;
import java.util.Date;
import java.util.Objects;
import javafx.scene.image.ImageView;
/**
 *
 * @author user
 */

        
public class Evenement {
    private int id;
    private int id_organisateur;
    private String type;
    private String titre;
    private String lieu;
    private String description;
    private Date date_event; 
    private String image;

    
    public Evenement() {
    }


    public Evenement(int id, int id_organisateur, String type, String titre, String lieu, String description, Date date_event, String image) {
        this.id = id;
        this.id_organisateur = id_organisateur;
        this.type = type;
        this.titre = titre;
        this.lieu = lieu;
        this.description = description;
        this.date_event = date_event;
        this.image = image;
    }

    

    public int getId() {
        return id;
    }

    public int getId_organisateur() {
        return id_organisateur;
    }

    public String getType() {
        return type;
    }

    public String getTitre() {
        return titre;
    }

    public String getLieu() {
        return lieu;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate_event() {
        return date_event;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_organisateur(int id_organisateur) {
        this.id_organisateur = id_organisateur;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate_event(Date date_event) {
        this.date_event = date_event;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this.id;
        hash = 29 * hash + this.id_organisateur;
        hash = 29 * hash + Objects.hashCode(this.type);
        hash = 29 * hash + Objects.hashCode(this.titre);
        hash = 29 * hash + Objects.hashCode(this.lieu);
        hash = 29 * hash + Objects.hashCode(this.description);
        hash = 29 * hash + Objects.hashCode(this.date_event);
        hash = 29 * hash + Objects.hashCode(this.image);
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
        final Evenement other = (Evenement) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.id_organisateur != other.id_organisateur) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.titre, other.titre)) {
            return false;
        }
        if (!Objects.equals(this.lieu, other.lieu)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.image, other.image)) {
            return false;
        }
        if (!Objects.equals(this.date_event, other.date_event)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", id_organisateur=" + id_organisateur + ", type=" + type + ", titre=" + titre + ", lieu=" + lieu + ", description=" + description + ", date_event=" + date_event + ", image=" + image + '}';
    }

    public void setImage(ImageView emp0photo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}