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
public class consultation {
       private int id;
    private String titre;
    private String description;
        private String emplacement;
private String image;

    public consultation(int id, String titre, String description,String emplacement,String image) {
        this.id = id;
        this.titre = titre;
        this.description = description;
                this.emplacement = emplacement;
                this.image = image;
    }

    public consultation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmplacement() {
        return emplacement;
    }

    public void setEmplacement(String emplacement) {
        this.emplacement = emplacement;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "consultation{" + "id=" + id + ", titre=" + titre + ", description=" + description + ", emplacement=" + emplacement + ", image=" + image + '}';
    }

  

   
}
