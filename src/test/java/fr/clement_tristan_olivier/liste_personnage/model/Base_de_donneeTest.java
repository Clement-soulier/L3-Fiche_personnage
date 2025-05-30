package fr.clement_tristan_olivier.liste_personnage.model;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class Base_de_donneeTest {
    
    @Test
    public void test_Base_de_donnees() {
        Base_de_donnees bd = new Base_de_donnees();

        assertTrue(bd.utilisateurs instanceof ArrayList<Compte>, "La liste d'utilisateur est du mauvais type");
        assertTrue(bd.utilisateurs.isEmpty(), "La liste d'utilisateur n'est pas vide");
    }

    @Test
    public void test_authenticate() {
        Base_de_donnees bd = new Base_de_donnees();
        Compte compte = new Compte("Jean", "Mot2p@ssTr3sSecuizer");
        bd.ajouter_compte(compte);

        Compte compte_depuis_bd = bd.authenticate("Jean", "Mot2p@ssTr3sSecuizer");
        assertEquals(compte_depuis_bd.id, compte.id, "L'id ne correspond pas");
        assertEquals(compte_depuis_bd.pseudo, compte.pseudo, "Le pseudo ne correspond pas");
        assertEquals(compte_depuis_bd.password, compte.password, "Le pseudo ne correspond pas");
        assertTrue(compte_depuis_bd.personnages.equals(compte.personnages), "La liste des personnage ne correspond pas");
    }

    @Test
    public void test_ajouter_compte() {
        Base_de_donnees bd = new Base_de_donnees();
        Compte compte = new Compte("Jean", "Mot2p@ssTr3sSecuizer");
        bd.ajouter_compte(compte);

        assertTrue(bd.utilisateurs.contains(compte), "Le compte n'as pas été ajouté à la base de données");
    }

    @Test
    public void test_supprimer_compte() {
        Base_de_donnees bd = new Base_de_donnees();
        Compte compte = new Compte("Jean", "Mot2p@ssTr3sSecuizer");
        Compte compte1 = new Compte("Bernard", "1234");
        bd.ajouter_compte(compte);
        bd.ajouter_compte(compte1);

        bd.supprimer_compte(compte);

        assertTrue(!bd.utilisateurs.contains(compte), "Le compte n'as pas été supprimé");
        assertTrue(bd.utilisateurs.contains(compte1), "Le mauvais compte a été supprimé");
    }

    @Test
    public void test_toString() {
        Base_de_donnees bd = new Base_de_donnees();
        Compte compte = new Compte("Jean", "Mot2p@ssTr3sSecuizer");
        Personnage personnage = new Personnage("Jean", "Personnage de test", new HashMap<>(), new ArrayList<>(), new ArrayList<>(), Classe.getClasse("testeur"), Race.getRace("elfe"));
        Equipement equipement = new Equipement("épée", "Une épée très tranchante 😱");
        Competence competence = new Competence("Rugissement", "Le lanceur pousse un cri tout mimi pour tromper la vigilance de la cible et baisser son Attaque.");
        Statistique statistique = new Statistique("Attaque", "Les dégâts bruts lors d'une attaque");


        personnage.ajouter_competence(competence);
        personnage.ajouter_equipement(equipement);
        personnage.ajouter_statistique(statistique, 15);

        compte.ajouter_personnage(personnage);
        bd.ajouter_compte(compte);

        System.out.println(bd.toString());
    }
}
