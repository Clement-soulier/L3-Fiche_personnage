package fr.clement_tristan_olivier.liste_personnage.model;

import java.util.ArrayList;
import java.io.Serializable;

public class Statistique implements Serializable
{
    private static int get_id = 0;
    public int id;
    public String nom;
    public String description;
    public int valeur;

    public static ArrayList<Statistique> liste_stats = new ArrayList<>();

    public Statistique(String description, int valeur)
    {
        this.id = get_id++;
        this.description = description;
        this.valeur = valeur;
        liste_stats.add(this);
    }

    public int renommer(String nouveau_nom)
    {
        this.nom = nouveau_nom;
        return 0;
    }

    public void change_valeur(Personnage personnage, int valeur)
    {
        personnage.modifier_statistique(this, valeur);
    }

    public void ajouter_description(String nouvelle_description)
    {
        this.description = nouvelle_description;
    }

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

    public String ToString(){
        return nom + ": " + valeur + ". " + description;
    }
}