package fr.clement_tristan_olivier.liste_personnage.model;

import java.util.ArrayList;
import java.io.Serializable;

public class Equipement implements Serializable
{
    private static int get_id = 0;
    public int id;
    public String nom;
    public String description;
    public static ArrayList<Equipement> liste_equipement = new ArrayList<>();

    public Equipement(String nom, String description)
    {
        this.id = get_id++;
        this.nom = nom;
        this.description = description;
        liste_equipement.add(this);
    }

    public void renommer(String nouveau_nom)
    {
        this.nom = nouveau_nom;
    }

    public void ajouter_description(String nouvelle_description)
    {
        this.description = nouvelle_description;
    }

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

    public String ToString()
    {
        return nom + ": " + description;
    }
}
