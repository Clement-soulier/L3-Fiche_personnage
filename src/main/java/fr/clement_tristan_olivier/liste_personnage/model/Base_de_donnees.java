package fr.clement_tristan_olivier.liste_personnage.model;

import java.util.ArrayList;
import java.io.Serializable;

public class Base_de_donnees implements Serializable{
    public ArrayList<Compte> utilisateurs;


    public Base_de_donnees(){
        this.utilisateurs = new ArrayList<Compte>();
    }

    public String hash_password(String password){

    }

    public Compte authenticate(String password){}

    public void ajouter_compte(Compte compte){
        this.utilisateurs.add(compte);
    }

    public void supprimer_compte(Compte compte){
        this.utilisateurs.remove(compte);
    }

    public void desactive_compte(Compte compte){
        compte.active = false;
    }

    public void active_compte(Compte compte){
        compte.active = true;
    }

    public String toString(){
        String output = "Base_de_donnees{\n";
        for(Compte compte : this.utilisateurs){
            output += compte.toString() + ",\n";
        }
        output += "}";
        return output;
    }
}