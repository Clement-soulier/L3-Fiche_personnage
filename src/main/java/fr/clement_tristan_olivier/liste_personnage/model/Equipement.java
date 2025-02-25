package fr.clement_tristan_olivier.liste_personnage.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Cette classe représente un équipement pour un personnage
 */
public class Equipement implements Serializable
{
    private static int get_id = 0;
    public int id;
    public String nom;
    public String description;
    public static ArrayList<Equipement> liste_equipement = new ArrayList<>();

    /**
     * Constructeur pour la classe Equipement
     * @param nom String
     * @param description String
     */
    public Equipement(String nom, String description)
    {
        this.id = get_id++;
        this.nom = nom;
        this.description = description;
        liste_equipement.add(this);
    }

    /**
     * Renomme un équipement
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
     * Change la description d'un équipement
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
     * Supprime un équipement de pour tous les personnages et de la liste globale
     * @param p Equipement
     * @return boolean
     */
    public static boolean supprime_globale(Equipement p)
    {
        for (Compte compte : Compte.liste_compte)
        {
            for (Personnage personnage : compte.liste_personnage)
            {
                personnage.supprime_equipement(p);
                if(personnage.equipements.contains(p))
                {
                    return false;
                }
            }
        }
        liste_equipement.remove(p);
        return !liste_equipement.contains(p);
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
