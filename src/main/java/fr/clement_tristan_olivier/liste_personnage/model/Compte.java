package fr.clement_tristan_olivier.liste_personnage.model;
import java.io.Serializable;
import java.util.ArrayList;

import fr.clement_tristan_olivier.liste_personnage.utils.passwordUtils;

/**
 * Cette classe représente un compte pour un utilisateur
 */
public class Compte implements Serializable{
    static int get_id = 0;
    protected int id;
    protected String pseudo;
    protected String password;
    protected Boolean active;
    protected ArrayList<Personnage> personnages;
    public static ArrayList<Compte> liste_compte = new ArrayList<>();

    /**
     * Constructeur pour Compte.
     * @param pseudo String
     * @param password String
     */
    public Compte(String pseudo, String password) {
        this.pseudo = pseudo;
        this.id = Compte.get_id++;
        this.password = passwordUtils.hashPassword(password);
        this.active = true;
        this.personnages = new ArrayList<>();
        liste_compte.add(this);
    }

    /**
     * Ajoute un personnage au compte.
     * Retourne false si le personnage est déjà présent, true sinon
     * @param personnage Personnage
     * @return boolean
     */
    public boolean ajouter_personnage(Personnage personnage) {
        if(this.personnages.contains(personnage)){
            return false;
        }
        this.personnages.add(personnage);
        return true;
    }

    /**
     * Supprime un personnage pour le compte
     * Retourne false si le personnage n'est pas présent, true sinon
     * @param personnage Personnage
     * @return boolean
     */
    public boolean supprimer_personnage(Personnage personnage) {
        if(!this.personnages.contains(personnage)){
            return false;
        }
        this.personnages.removeIf(p -> p.equals(personnage));
        return true;
    }

    /**
     * Retourne un personnage du compte d'après son id.
     * Retourne null si le personnage n'est pas trouvé
     * @param id int
     * @return Personnage ou null
     */
    public Personnage get_personnage_by_id(int id) {
        for(Personnage personnage : this.personnages){
            if(personnage.id == id){
                return personnage;
            }
        }
        return null;
    }

    /**
     * Renvoie un String représentant l'objet pour affichage.
     * @return String
     */
    @Override public String toString(){
        return "Compte{" +
                "id=" + id +
                ", pseudo='" + pseudo + '\'' +
                ", password='" + password + '\'' +
                ", active=" + active +
                ", personnages=" + personnages +
                '}';
    }
}