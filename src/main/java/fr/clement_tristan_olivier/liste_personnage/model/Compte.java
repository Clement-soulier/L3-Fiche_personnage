package fr.clement_tristan_olivier.liste_personnage.model;

import java.util.ArrayList;
import java.io.Serializable;

/**
 * Cette classe représente un compte pour un utilisateur
 */
public class Compte implements Serializable{
    static int get_id;
    protected int id;
    protected String pseudo;
    protected String password;
    protected Boolean active;
    protected ArrayList<Personnage> liste_personnage;

    /**
     * Constructeur pour Compte
     * @param pseudo
     * @param password
     */
    public Compte(String pseudo, String password) {
        this.pseudo = pseudo;
        this.id = get_id++;
        this.password = password;
        this.active = true;
        this.liste_personnage = new ArrayList<Personnage>();
    }

    /**
     * Ajoute un personnage au compte
     * @param personnage l'objet Personnage
     */
    public void ajouter_personnage(Personnage personnage) {
        this.liste_personnage.add(personnage);
    }

    /**
     * Supprime un personnage pour le compte
     * @param personnage l'instance à supprimer
     */
    public void supprimer_personnage(Personnage personnage) {
        this.liste_personnage.remove(personnage);
    }

    /**
     * Renvoie un String représentant l'objet pour affichage
     * @return La string représentant l'objet
     */
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