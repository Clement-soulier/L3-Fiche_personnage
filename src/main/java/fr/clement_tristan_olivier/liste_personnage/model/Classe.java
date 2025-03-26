package fr.clement_tristan_olivier.liste_personnage.model;
import java.util.ArrayList;

public class Classe {
    public String nom;

    public static ArrayList<Classe> classes;

    public Classe(String nom){
        this.nom = nom;
        Classe.classes.add(this);
    }

    @Override
    public String toString(){
        return this.nom;
    }
}
