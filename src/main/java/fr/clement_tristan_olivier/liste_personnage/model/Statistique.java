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
    public int valeur;

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
     * @param nouveau_nom String
    */
    public void renommer(String nouveau_nom)
    {
        this.nom = nouveau_nom;
    }

    /**
     * Change la valeur d'une statistique pour un personnage
     * @param personnage Personnage
     * @param valeur int
     */
    public void changer_valeur(Personnage personnage, int valeur)
    {
        personnage.modifie_statistique(this, valeur);
    }

    /**
     * Permet de changer la description de la statistique
     * @param nouvelle_description String
     */
    public void changer_description(String nouvelle_description)
    {
        this.description = nouvelle_description;
    }

    /**
     * Supprime la statistique de chaque personnage puis se supprime de la liste static
     * @param p Statistique
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

    /**
     * Retourne l'objet sous forme de String pour affichage
     * @return String
     */
    public String ToString(){
        return nom + ": " + valeur + ". " + description;
    }
}