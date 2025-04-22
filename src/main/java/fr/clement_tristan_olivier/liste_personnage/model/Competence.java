package fr.clement_tristan_olivier.liste_personnage.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Cette classe représente une compétence pour un personnage
 */
public class Competence implements Serializable
{
    private static int get_id = 0;
    public int id;
    public String nom;
    public String description;
    public static ArrayList<Competence> liste_competence = new ArrayList<>();
    
    /**
     * Constructeur pour la classe Competence
     * @param nom String
     * @param description String
     */
    public Competence(String nom, String description)
    {
        this.id = get_id++;
        this.nom = nom;
        this.description = description;
        liste_competence.add(this);
    }

    /**
     * Renomme une compétence
     * Retourne false si le nom est le même, true sinon
     * @param nouveau_nom String
     * @return boolean
     */
    public boolean renommer(String nouveau_nom)
    {
        if(this.nom.equals(nouveau_nom)){
            return false;
        }
        this.nom = nouveau_nom;
        return true;
    }

    /**
     * Change la description d'une compétence
     * Retourne false si la description est la même, true sinon
     * @param nouvelle_description String
     * @return boolean
     */
    public boolean changer_description(String nouvelle_description)
    {
        if(this.description.equals(nouvelle_description)){
            return false;
        }
        this.description = nouvelle_description;
        return true;
    }

    /**
     * Renvoie un string représentant l'objet pour affichage
     * @return String
     */
    public String ToString()
    {
        return nom + ": " + description;
    }
}