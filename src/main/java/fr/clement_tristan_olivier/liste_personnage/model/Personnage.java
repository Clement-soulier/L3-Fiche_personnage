package fr.clement_tristan_olivier.liste_personnage.model;

import java.util.ArrayList;
import java.io.Serializable;

/**
 * Classe représentant un personnage
 */
public class Personnage implements Serializable{
    static int get_id = 0;
    protected int id;
    protected String nom;
    protected String biographie;
    // protected image portrait
    protected ArrayList<Statistique> statistiques;
    protected ArrayList<Competence> competences;
    protected ArrayList<Equipement> equipements;
    protected String classe;

    /**
     * Constructeur pour Personnage
     * @param nom String
     * @param biographie String 
     * @param statistiques ArrayList
     * @param competences ArrayList
     * @param equipements ArrayList
     * @param classe String 
     */
    public Personnage(String nom, String biographie, ArrayList<Statistique> statistiques, ArrayList<Competence> competences, ArrayList<Equipement> equipements, String classe){
        this.id = get_id++;
        this.nom = nom;
        this.biographie = biographie; 
        this.statistiques =  statistiques;
        this.competences = competences;
        this.equipements = equipements;
        this.classe = classe;
    }

}