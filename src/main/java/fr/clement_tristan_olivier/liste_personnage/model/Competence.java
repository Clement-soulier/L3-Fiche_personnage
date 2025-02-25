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
     * @param nouveau_nom String
     */
    public void renommer(String nouveau_nom)
    {
        this.nom = nouveau_nom;
    }

    /**
     * Change la description d'une compétence
     * @param nouvelle_description String
     */
    public void changer_description(String nouvelle_description)
    {
        this.description = nouvelle_description;
    }

    /**
     * Supprime une compétence pour chaque personnage et pour la liste globale
     * @param p Competence
     */
    public static void supprime_globale(Competence p)
    {
        for(Compte compte : Compte.liste_compte)
        {
            for (Personnage personnage : compte.liste_personnage)
            {
                personnage.supprime_competence(p);
            }
        }
        liste_competence.remove(p);
    }

    /**
     * Ajoute une compétence à chaque personnage de la liste personnages
     * @param p Competence
     * @param personnages ArrayList<Personnage>
     */
    public static void ajoute_globale(Competence p, ArrayList<Personnage> personnages)
    {
        for(Personnage personnage : personnages)
        {
            personnage.ajoute_competence(p);
        }
        liste_competence.add(p);
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