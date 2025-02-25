package fr.clement_tristan_olivier.liste_personnage.model;
import java.io.Serializable;
import java.util.ArrayList;

import fr.clement_tristan_olivier.liste_personnage.utils.passwordUtils;

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
    public static ArrayList<Compte> liste_compte = new ArrayList<>();

    /**
     * Constructeur pour Compte
     * @param pseudo String
     * @param password String
     */
    public Compte(String pseudo, String password) {
        this.pseudo = pseudo;
        this.id = get_id++;
        this.password = passwordUtils.hashPassword(password);
        this.active = true;
        this.liste_personnage = new ArrayList<>();
        liste_compte.add(this);
    }

    /**
     * Ajoute un personnage au compte
     * @param personnage Personnage
     * @return boolean
     */
    public boolean ajouter_personnage(Personnage personnage) {
        this.liste_personnage.add(personnage);
        return this.liste_personnage.contains(personnage);
    }

    /**
     * Supprime un personnage pour le compte
     * @param personnage Personnage
     * @return boolean
     */
    public boolean supprimer_personnage(Personnage personnage) {
        this.liste_personnage.remove(personnage);
        return !this.liste_personnage.contains(personnage);
    }

    /**
     * Renvoie un String représentant l'objet pour affichage
     * @return String
     */
    @Override public String toString(){
        return "Compte{" +
                "id=" + id +
                ", pseudo='" + pseudo + '\'' +
                ", password='" + password + '\'' +
                ", active=" + active +
                ", liste_personnage=" + liste_personnage +
                '}';
    }
}