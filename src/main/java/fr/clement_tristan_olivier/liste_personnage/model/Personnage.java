package fr.clement_tristan_olivier.liste_personnage.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Classe représentant un personnage
 */
public class Personnage implements Serializable{
    static int get_id = 0;
    protected int id;
    protected String nom;
    protected String biographie;
    // protected image portrait
    protected HashMap<Statistique, Integer> statistiques;
    protected ArrayList<Competence> competences;
    protected ArrayList<Equipement> equipements;
    protected String classe;

    /**
     * Constructeur pour Personnage
     * @param nom String
     * @param biographie String 
     * @param statistiques HashMap<Statistique, Integer>
     * @param competences ArrayList<Competence>
     * @param equipements ArrayList<Equipement>
     * @param classe String 
     */
    public Personnage(String nom, String biographie, HashMap<Statistique, Integer> statistiques, ArrayList<Competence> competences, ArrayList<Equipement> equipements, String classe){
        this.id = get_id++;
        this.nom = nom;
        this.biographie = biographie; 
        this.statistiques =  statistiques;
        this.competences = competences;
        this.equipements = equipements;
        this.classe = classe;
    }

    /**
     * Ajoute une compétence au personnage
     * @param competence Competence
     */
    public void ajoute_competence(Competence competence){
        this.competences.add(competence);
    }

    /**
     * Supprime une compétence du personnage
     * @param competence Competence
     */
    public void supprime_competence(Competence competence){
        if(this.competences.contains(competence)){
            this.competences.remove(competence);
        }
    }

    /**
     * Ajoute un équipement au personnage
     * @param equipement Equipement
     */
    public void ajoute_equipement(Equipement equipement){
        this.equipements.add(equipement);
    }

    /**
     * Supprime un équipement du personnage
     * @param equipement Equipement
     */
    public void supprime_equipement(Equipement equipement){
        if(this.equipements.contains(equipement)){
            this.equipements.remove(equipement);
        }
    }

    /**
     * Ajoute une statistique au personnage
     * @param statistique Statistique
     */
    public void ajoute_statistique (Statistique statistique, int valeur){
        this.statistiques.put(statistique, valeur);
    }

    /**
     * Modifie la valeur d'une statistique du personnage, ajoute la statistique si elle n'existe pas
     * @param statistique Statistique
     * @param valeur int
     */
    public void modifie_statistique(Statistique statistique, int valeur){
        if(this.statistiques.containsKey(statistique)){
            this.statistiques.replace(statistique, valeur);
        } else {
            this.statistiques.put(statistique, valeur);
        }
    }

    /**
     * Supprime une statistique du personnage
     * @param statistique Statistique
     */
    public void supprime_statistique(Statistique statistique){
        if(this.statistiques.get(statistique) != null){
            this.statistiques.remove(statistique);
        }
    }

    /**
     * Modifie le portrait du personnage
     * @param portrait Image
     */
    /*public void modifier_portrait(Image portrait){
        this.portrait = portrait;
    }*/

    /**
     * Modifie le nom du personnage
     * @param nom String
     */
    public void modifier_nom(String nom){
        this.nom = nom;
    }


    /**
     * Modifie la biographie du personnage
     * @param biographie String
     */
    public void modifier_biographie(String biographie){
        this.biographie = biographie;
    }

    /**
     * Renvoie un string représentant l'objet pour affichage
     * @return String
     */
    public String ToString(){
        return nom + ": " + biographie;
    }
}