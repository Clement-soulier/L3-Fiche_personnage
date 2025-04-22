package fr.clement_tristan_olivier.liste_personnage.model;

import java.io.Serializable;
import java.util.ArrayList;

import fr.clement_tristan_olivier.liste_personnage.utils.passwordUtils;

/**
 * Cette classe représente la base de données d'utilisateur
 */
public class Base_de_donnees implements Serializable{
    public ArrayList<Compte> utilisateurs;
    public ArrayList<Classe> classes;
    public ArrayList<Competence> competences;
    public ArrayList<Equipement> equipements;
    public ArrayList<Race> races;
    public ArrayList<Statistique> statistiques;

    /**
     * Constructeur pour Base_de_donnees.
     * Créer une base de donnée vide
     */
    public Base_de_donnees(){
        this.utilisateurs = new ArrayList<>();
    }

    /**
     * Authentifie un compte depuis un pseudo et un mot de passe
     * @param pseudo String
     * @param password String
     * @return Compte ou null
     */
    public Compte authenticate(String pseudo, String password){
        for(Compte compte : this.utilisateurs){
            if(compte.pseudo.equals(pseudo) && passwordUtils.verifyPassword(compte.password, password)){
                return compte;
            }
        }
        return null;
    }

    /**
     * Ajoute un Compte à la base de données.
     * Retourne false is le compte existe déjà, true sinon
     * @param compte Compte
     * @return boolean
     */
    public boolean ajouter_compte(Compte compte){
        for (Compte compteFromDb : utilisateurs) {
            if(compteFromDb.pseudo.equals(compte.pseudo)){
                return false;
            }
        }
        this.utilisateurs.add(compte);
        return true;
    }

    /**
     * Supprime un compte de la base de données.
     * Retourne false si le compte n'existe pas, true sinon
     * @param compte Compte
     * @return boolean
     */
    public boolean supprimer_compte(Compte compte){
        if(!this.utilisateurs.contains(compte)){
            return false;
        }
        this.utilisateurs.remove(compte);
        return !this.utilisateurs.contains(compte);
    }

    /**
     * Renvoie une string représentant l'objet pour affichage.
     * @return String
     */
    public String ToString(){
        String output = "Base_de_donnees{\n";
        for(Compte compte : this.utilisateurs){
            output += compte.toString() + ",\n";
        }
        output += "}";
        return output;
    }
}