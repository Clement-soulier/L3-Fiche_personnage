package fr.clement_tristan_olivier.liste_personnage.model;
import java.util.ArrayList;

public class Classe {
    public String nom;

    public static ArrayList<Classe> classes = new ArrayList<>();

    /**
     * Constructeur.
     * @param nom String
     */
    public Classe(String nom){
        this.nom = nom;
        Classe.classes.add(this);
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
     * Retourne un objet Classe à partir de son nom.
     * Crée un nouvel objet Classe si le nom n'existe pas dans la liste.
     * @param nom String
     * @return Classe
     */
    public static Classe getClasse(String nom){
        for(Classe classe: Classe.classes){
            if(classe.nom.equals(nom)){
                return classe;
            }
        }
        Classe ret = new Classe(nom);
        return ret;
    }
}
