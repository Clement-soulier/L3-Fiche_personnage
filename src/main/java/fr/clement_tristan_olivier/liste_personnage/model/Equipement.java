package fr.clement_tristan_olivier.liste_personnage.model;

import java.util.ArrayList;
import java.io.Serializable;

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
     * @param nom le nom de l'équipement
     * @param description la description de l'équipement
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
     * @param nouveau_nom le nouveau nom de l'équipement
     */
    public void renommer(String nouveau_nom)
    {
        this.nom = nouveau_nom;
    }

    /**
     * Change la description d'un équipement
     * @param nouvelle_description la nouvelle description
     */
    public void ajouter_description(String nouvelle_description)
    {
        this.description = nouvelle_description;
    }

    /**
     * Supprime un équipement de pour tous les personnages et de la liste globale
     * @param p l'équipement à supprimer
     */
    public static void supprime_globale(Equipement p)
    {
        for (Compte compte : Compte.liste_compte)
        {
            for (Personnage personnage : compte.liste_personnage)
            {
                personnage.supprime_equipement(p);
            }
        }
        liste_equipement.remove(p);
    }

    public static void ajoute_globale(Equipement p)
    {
        for(Compte compte : Compte.liste_compte)
        {
            for (Personnage personnage : compte.liste_personnage)
            {
                personnage.ajouter_equipement(p);
            }
        }
        liste_equipement.add(p);

    }

    /**
     * Renvoie un string représentant l'objet pour affichage
     * @return La stringre représentant l'objet
     */
    public String ToString()
    {
        return nom + ": " + description;
    }
}
