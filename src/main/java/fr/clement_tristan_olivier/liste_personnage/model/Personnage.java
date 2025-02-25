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
    // protected image portrait
    protected String biographie;
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
     * Retourne false si la compétence est déjà présente, true sinon
     * @param competence Competence
     * @return boolean
     */
    public boolean ajoute_competence(Competence competence){
        if(this.competences.contains(competence)){
            return false;
        }
        this.competences.add(competence);
        return true;
    }

    /**
     * Supprime une compétence du personnage
     * Retourne false si la compétence n'est pas présente, true sinon
     * @param competence Competence
     * @return boolean
     */
    public boolean supprime_competence(Competence competence){
        if(!this.competences.contains(competence)){
            return false;
        }
        this.competences.removeIf(c -> c.equals(competence));
        return true;
    }

    /**
     * Ajoute un équipement au personnage
     * Retourne false si l'équipement est déjà présent, true sinon
     * @param equipement Equipement
     * @return boolean
     */
    public boolean ajoute_equipement(Equipement equipement){
        if(this.equipements.contains(equipement)){
            return false;
        }
        this.equipements.add(equipement);
        return true;
    }

    /**
     * Supprime un équipement du personnage
     * Retourne false si l'équipement n'est pas présent, true sinon
     * @param equipement Equipement
     * @return boolean
     */
    public boolean supprime_equipement(Equipement equipement){
        if(!this.equipements.contains(equipement)){
            return false;
        }
        this.equipements.removeIf(e -> e.equals(equipement));
        return true;
    }

    /**
     * Ajoute une statistique au personnage
     * Retourne false si la statistique existe déjà, true sinon
     * @param statistique Statistique
     * @param valeur int
     * @return boolean
     */
    public boolean ajoute_statistique (Statistique statistique, int valeur){
        if(this.statistiques.containsKey(statistique)){
            return false;
        }
        this.statistiques.put(statistique, valeur);
        return true;
    }

    /**
     * Modifie la valeur d'une statistique du personnage, ajoute la statistique et retourne false si elle n'existe pas
     * @param statistique Statistique
     * @param valeur int
     * @return boolean
     */
    public boolean modifie_statistique(Statistique statistique, int valeur){
        if(this.statistiques.containsKey(statistique)){
            this.statistiques.replace(statistique, valeur);
        } else {
            this.statistiques.put(statistique, valeur);
            return false;
        }
        return true;
    }

    /**
     * Supprime une statistique du personnage
     * Retourne false si le personnage n'a pas la statistique, sinon retourne true
     * @param statistique Statistique
     * @return boolean
     */
    public boolean supprime_statistique(Statistique statistique){
        if(!this.statistiques.containsKey(statistique)){
            return false;
        }
        this.statistiques.remove(statistique);
        return true;
    }

    /**
     * Retourne la valeur d'une statistique du personnage
     * Si le personnage n'a pas la statistique, retourne -1
     * @param statistique Statistique
     * @return int
     */
    public int get_statistique_valeur(Statistique statistique){
        if(!this.statistiques.containsKey(statistique)){
            return -1;
        }
        return this.statistiques.get(statistique);
    }

    /**
     * Modifie le portrait du personnage
     * @param portrait Image
     * @return boolean
     */
    /*public boolean modifie_portrait(Image portrait){
        if(this.portrait.equals(portrait)){
            return false;
        }
        this.portrait = portrait;
        return true;
    }*/

    /**
     * Modifie le nom du personnage
     * Retourne false si le nom est le même, true sinon
     * @param nom String
     * @return boolean
     */
    public boolean modifie_nom(String nom){
        if(this.nom.equals(nom)){
            return false;
        }
        this.nom = nom;
        return true;
    }


    /**
     * Modifie la biographie du personnage
     * Retourne false si la biographie est la même, true sinon
     * @param biographie String
     * @return boolean
     */
    public boolean modifie_biographie(String biographie){
        if(this.biographie.equals(biographie)){
            return false;
        }
        this.biographie = biographie;
        return true;
    }

    /**
     * Renvoie un string représentant l'objet pour affichage
     * @return String
     */
    public String ToString(){
        return nom + ": " + biographie;
    }
}