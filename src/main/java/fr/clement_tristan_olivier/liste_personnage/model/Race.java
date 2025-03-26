package fr.clement_tristan_olivier.liste_personnage.model;
import java.util.ArrayList;

public class Race {
    public String nom;

    public static ArrayList<Race> races = new ArrayList<>();

    /**
     * Constructeur.
     * @param nom String
     */
    public Race(String nom){
        this.nom = nom;
        Race.races.add(this);
    }

    /**
     * Retourne un string correspondant à l'objet
     * @return String
     */
    @Override
    public String toString(){
        return this.nom;
    }

    /**
     * Retourne un objet Race à partir de son nom.
     * Crée un nouvel objet Race si le nom n'existe pas dans la liste.
     * @param nom String
     * @return Race
     */
    public static Race getRace(String nom){
        for(Race race: Race.races){
            if(race.nom.equals(nom)){
                return race;
            }
        }
        Race ret = new Race(nom);
        return ret;
    }
}
