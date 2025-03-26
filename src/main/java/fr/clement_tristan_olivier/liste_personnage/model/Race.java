package fr.clement_tristan_olivier.liste_personnage.model;
import java.util.ArrayList;

public class Race {
    public String nom;

    public static ArrayList<Race> races;

    public Race(String nom){
        this.nom = nom;
        Race.races.add(this);
    }

    @Override
    public String toString(){
        return this.nom;
    }
}
