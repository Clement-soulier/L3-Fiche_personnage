package fr.clement_tristan_olivier.liste_personnage.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Cette classe représente une statistique pour un personage
 */
public class Statistique implements Serializable
{
    private static int get_id = 0;
    public int id;
    public String nom;
    public String description;

    public static ArrayList<Statistique> liste_stats = new ArrayList<>();

    /**
     *Créer une nouvelle instance de Statistique et l'ajoute à la liste de Statistiques
     *@param nom String
     *@param description String
     */
    public Statistique(String nom, String description)
    {
        this.id = get_id++;
        this.nom = nom;
        this.description = description;
        liste_stats.add(this);
    }

    /**
     * Permet de renommer une statistique
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
     * Permet de changer la description de la statistique
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
     * Supprime la statistique de chaque personnage puis se supprime de la liste static
     * @param p Statistique
     * @return boolean
     */
    public static boolean supprime_globale(Statistique p)
    {
        for(Compte compte : Compte.liste_compte)
        {
            for(Personnage personnage : compte.personnages)
            {
                personnage.supprimer_statistique(p);
                if(personnage.statistiques.containsKey(p)){
                    return false;
                }
            }
        }
        liste_stats.removeIf(s -> s.equals(p));
        return true;
    }

    /**
     * Retourne l'objet sous forme de String pour affichage
     * @return String
     */
    public String ToString(){
        return nom + ": " + description;
    }
}