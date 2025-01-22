package fr.clement_tristan_olivier.liste_personnage.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Competence implements Serializable
{
    private static int get_id = 0;
    public int id;
    public String nom;
    public String description;
    public static ArrayList<Competence> liste_competence = new ArrayList<>();
    
    public Competence(String nom, String description)
    {
        this.id = get_id++;
        this.nom = nom;
        this.description = description;
        liste_competence.add(this);
    }

    public int renommer(String nouveau_nom)
    {
        this.nom = nouveau_nom;
        return 0;
    }

    public int ajouter_description(String nouvelle_description)
    {
        this.description = nouvelle_description;
        return 0;
    }

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

    public static void ajoute_globale(Competence p)
    {
        for(Compte compte : Compte.liste_compte)
        {
            for(Personnage personnage : compte.liste_personnage)
            {
                personnage.ajoute_competence(p);
            }
        }
        liste_competence.add(p);
    }

    public String ToString()
    {
        return nom + ": " + description;
    }
}