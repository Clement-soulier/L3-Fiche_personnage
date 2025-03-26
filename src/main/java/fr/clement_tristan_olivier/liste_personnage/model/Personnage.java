package fr.clement_tristan_olivier.liste_personnage.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Classe représentant un personnage
 */
public class Personnage implements Serializable{
    static int get_id = 0;
    public int id;
    public String nom;
    // protected image portrait
    public String biographie;
    public HashMap<Statistique, Integer> statistiques;
    public ArrayList<Competence> competences;
    public ArrayList<Equipement> equipements;
    public Classe classe;
    public Race race;

    /**
     * Constructeur principal pour Personnage.
     * @param nom String
     * @param biographie String 
     * @param statistiques HashMap<Statistique, Integer>
     * @param competences ArrayList<Competence>
     * @param equipements ArrayList<Equipement>
     * @param classe Classe 
     * @param race Race
     */
    public Personnage(String nom, String biographie, HashMap<Statistique, Integer> statistiques, ArrayList<Competence> competences, ArrayList<Equipement> equipements, Classe classe, Race race){
        this.id = Personnage.get_id++;
        this.nom = nom;
        this.biographie = biographie; 
        this.statistiques =  statistiques;
        this.competences = competences;
        this.equipements = equipements;
        this.classe = classe;
        this.race = race;
    }

    /**
     * Constructeur pour Personnage.
     * Ne requiert que le nom du personnage, à utiliser dans ListePersonnage.
     * @param nom
     */
    public Personnage(String nom){
        this.id = Personnage.get_id++;
        this.nom = nom;
        this.biographie = "";
        this.statistiques = new HashMap<>();
        this.competences = new ArrayList<>();
        this.equipements = new ArrayList<>();
        this.classe = null;
        this.race = null;
    }

    /**
     * Ajoute une compétence au personnage.
     * Retourne false si la compétence est déjà présente, true sinon
     * @param competence Competence
     * @return boolean
     */
    public boolean ajouter_competence(Competence competence){
        if(this.competences.contains(competence)){
            return false;
        }
        this.competences.add(competence);
        return true;
    }

    /**
     * Supprime une compétence du personnage.
     * Retourne false si la compétence n'est pas présente, true sinon
     * @param competence Competence
     * @return boolean
     */
    public boolean supprimer_competence(Competence competence){
        if(!this.competences.contains(competence)){
            return false;
        }
        this.competences.removeIf(c -> c.equals(competence));
        return true;
    }

    /**
     * Ajoute un équipement au personnage.
     * Retourne false si l'équipement est déjà présent, true sinon
     * @param equipement Equipement
     * @return boolean
     */
    public boolean ajouter_equipement(Equipement equipement){
        if(this.equipements.contains(equipement)){
            return false;
        }
        this.equipements.add(equipement);
        return true;
    }

    /**
     * Supprime un équipement du personnage.
     * Retourne false si l'équipement n'est pas présent, true sinon
     * @param equipement Equipement
     * @return boolean
     */
    public boolean supprimer_equipement(Equipement equipement){
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
    public boolean ajouter_statistique (Statistique statistique, int valeur){
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
    public boolean modifier_statistique(Statistique statistique, int valeur){
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
    public boolean supprimer_statistique(Statistique statistique){
        if(!this.statistiques.containsKey(statistique)){
            return false;
        }
        this.statistiques.remove(statistique);
        return true;
    }

    /**
     * Retourne la valeur d'une statistique du personnage.
     * Si le personnage n'a pas la statistique, retourne -1
     * @param statistique Statistique
     * @return int
     */
    public int get_valeur_statistique(Statistique statistique){
        if(!this.statistiques.containsKey(statistique)){
            return -1;
        }
        return this.statistiques.get(statistique);
    }

    /**
     * Modifie le portrait du personnage.
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
     * Modifie le nom du personnage.
     * Retourne false si le nom est le même, true sinon.
     * @param nom String
     * @return boolean
     */
    public boolean modifier_nom(String nom){
        if(this.nom.equals(nom)){
            return false;
        }
        this.nom = nom;
        return true;
    }


    /**
     * Modifie la biographie du personnage.
     * Retourne false si la biographie est la même, true sinon.
     * @param biographie String
     * @return boolean
     */
    public boolean modifier_biographie(String biographie){
        if(this.biographie.equals(biographie)){
            return false;
        }
        this.biographie = biographie;
        return true;
    }

    /**
     * Modifie la classe du personnage.
     * Retourne false si la classe est la même, true sinon.
     * @param classe Classe
     * @return boolean
     */
    public boolean modifier_classe(Classe classe){
        if(this.classe.equals(classe)){
            return false;
        }
        this.classe = classe;
        return true;
    }

    /**
     * Modifie la race du personnage.
     * Retourne false si la classe est la même, true sinon.
     * @param race Race
     * @return boolean
     */
    public boolean modifier_race(Race race){
        if(this.race.equals(race)){
            return false;
        }
        this.race = race;
        return true;
    }

    /**
     * Renvoie un string représentant l'objet pour affichage.
     * @return String
     */
    public String ToString(){
        String ret = this.nom + ": " + this.biographie + "\nClasse: " + this.classe.toString() + "\nRace: " + this.race.toString() + "\nStatistiques: {";
        for(Statistique statistique : this.statistiques.keySet()){
            ret += "\n" + statistique.ToString() + ": " + this.statistiques.get(statistique);
        }
        ret += "\n}\nCompétences: {";
        for(Competence competence : this.competences){
            ret += "\n" + competence.ToString();
        }
        ret += "\n}\nEquipements: {";
        for(Equipement equipement : this.equipements){
            ret += "\n" + equipement.ToString();
        }
        ret += "\n}";
        return ret;
    }
}