package fr.clement_tristan_olivier.liste_personnage.model;

import java.util.ArrayList;
import java.io.Serializable;

/**
 * Cette classe représente une statistique pour un personage
 */
public class Statistique implements Serializable
{
    private static int get_id = 0;
    public int id;
    public String nom;
    public String description;
    public int valeur;

    public static ArrayList<Statistique> liste_stats = new ArrayList<>();

    /**
     *Créer une nouvelle instance de Statistique et l'ajoute à la liste de Statistiques
     *@param description description de la statistique
     *@param valeur valeur de la statistique
     */
    public Statistique(String description, int valeur)
    {
        this.id = get_id++;
        this.description = description;
        this.valeur = valeur;
        liste_stats.add(this);
    }

    /**
     * Permet de renommer une statistique
     * @param nouveau_nom le nouveau nom de la statistique
    */
    public int renommer(String nouveau_nom)
    {
        this.nom = nouveau_nom;
        return 0;
    }

    /**
     * Change la valeur d'une statistique pour un personnage
     * @param personnage le personnage pour qui la statistique va être changer
     */
    public void change_valeur(Personnage personnage, int valeur)
    {
        personnage.modifier_statistique(this, valeur);
    }

    /**
     * Permet de changer la description de la statistique
     * @param nouvelle_description la nouvelle descrition de la statistique
     */
    public void ajouter_description(String nouvelle_description)
    {
        this.description = nouvelle_description;
    }

    /**
     * Supprime la statistique de chaque personnage puis se supprime de la liste static
     * @param p la statistique à supprimer
     */
    public static void supprime_globale(Statistique p)
    {
        for(Compte compte : Compte.liste_compte)
        {
            for(Personnage personnage : compte.liste_personnage)
            {
                personnage.supprime_statistique(p);
            }
        }
        liste_stats.remove(p);
    }

    public static void ajoute_globale(Statistique p)
    {
        for(Compte compte : Compte.liste_compte)
        {
            for(Personnage personnage : compte.liste_personnage)
            {
                personnage.ajouter_statistique(p);
            }
        }
        liste_stats.add(p);
    }

    /**
     * Retourne l'objet sous forme de String pour affichage
     * @return La String décrivant l'objet
     */
    public String ToString(){
        return nom + ": " + valeur + ". " + description;
    }
}