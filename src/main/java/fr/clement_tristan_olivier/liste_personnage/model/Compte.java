package fr.clement_tristan_olivier.liste_personnage.model;

import java.util.ArrayList;
import java.io.Serializable;

public class Compte implements Serializable{
    static int get_id;
    protected int id;
    protected String pseudo;
    protected String password;
    protected Boolean active;
    protected ArrayList<Personnage> liste_personnage;


    public Compte(String pseudo, String password) {
        this.pseudo = pseudo;
        this.id = get_id++;
        this.password = password;
        this.active = true;
        this.liste_personnage = new ArrayList<Personnage>();
    }

    public void ajouter_personnage(Personnage personnage) {
        this.liste_personnage.add(personnage);
    }

    public void supprimer_personnage(Personnage personnage) {
        this.liste_personnage.remove(personnage);
    }

    public String toString(){
        return "Compte{" +
                "id=" + id +
                ", pseudo='" + pseudo + '\'' +
                ", password='" + password + '\'' +
                ", active=" + active +
                ", liste_personnage=" + liste_personnage +
                '}';
    }
}