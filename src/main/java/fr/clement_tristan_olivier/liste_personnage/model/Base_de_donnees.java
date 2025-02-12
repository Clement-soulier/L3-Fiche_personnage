package fr.clement_tristan_olivier.liste_personnage.model;

import java.util.ArrayList;
import java.io.Serializable;
import fr.clement_tristan_olivier.liste_personnage.utils.passwordUtils;

/**
 * Cette classe représente la base de données d'utilisateur
 */
public class Base_de_donnees implements Serializable{
    public ArrayList<Compte> utilisateurs;


    /**
     * Constructeur pour Base_de_donnees
     * Créer une base de donnée vide
     */
    public Base_de_donnees(){
        this.utilisateurs = new ArrayList<Compte>();
    }

    /**
     * Authentifie un compte depuis un pseudo et un mot de passe
     * @param pseudo
     * @param password
     * @return Le compte qui correspond au pseudo et mot de passe
     */
    public Compte authenticate(String pseudo, String password){
        for(Compte compte : this.utilisateurs){
            if(compte.pseudo.equals(pseudo) && passwordUtils.verifyPassword(compte.password, password)){
                return compte;
            }
        }
        //raise une erreur
    }

    /**
     * Ajout un Compte à la base de données
     * @param compte le Compte à ajouter
     */
    public void ajouter_compte(Compte compte){
        this.utilisateurs.add(compte);
    }

    /**
     * Supprime un compte de la base de données
     * @param compte Le compte à désactiver
     */
    public void supprimer_compte(Compte compte){
        this.utilisateurs.remove(compte);
    }

    /**
     * Désactive le compte
     * @param compte
     */
    public void desactive_compte(Compte compte){
        compte.active = false;
    }

    /**
     * Réactive un compte désactivé
     * @param compte
     */
    public void active_compte(Compte compte){
        compte.active = true;
    }

    /**
     * Renvoie une string représentant l'objet pour affichage
     * @return le string représentant l'objet
     */
    public String toString(){
        String output = "Base_de_donnees{\n";
        for(Compte compte : this.utilisateurs){
            output += compte.toString() + ",\n";
        }
        output += "}";
        return output;
    }
}