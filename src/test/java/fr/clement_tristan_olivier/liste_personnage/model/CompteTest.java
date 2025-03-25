package fr.clement_tristan_olivier.liste_personnage.model;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import fr.clement_tristan_olivier.liste_personnage.utils.passwordUtils;


public class CompteTest {

    @Test
    public void test_Compte() {
        Compte compte = new Compte("Jean", "Mot2p@ssTr3sSecuizer");

        assertEquals("Jean", compte.pseudo, "Le pseudo du compte devrait être \"Jean\"");
        assertEquals(true, passwordUtils.verifyPassword(compte.password, "Mot2p@ssTr3sSecuizer"), "Le hashage du mot de passe est incorect");
        assertEquals(true, compte.active, "Le compte devrait être actif");
        assertTrue(compte.personnages instanceof ArrayList<Personnage>, "La liste de personnage n'est pas du bon type");
        assertTrue(compte.personnages.isEmpty(), "La liste des personnage devrait être vide");
        assertTrue(Compte.liste_compte.contains(compte), "Le compte n'as pas été ajouté à la liste des comptes");
    }

    @Test
    public void test_ajouter_personnage() {
        Compte compte = new Compte("Jean", "Mot2p@ssTr3sSecuizer");
        Personnage personnage = new Personnage("Bernard", "Personnage de test", new HashMap<>(), new ArrayList<>(), new ArrayList<>(), "Forgeron", "Nain");
        compte.ajouter_personnage(personnage);

        assertTrue(compte.personnages.contains(personnage), "Le personnage n'a pas été ajouté correctement");
    }

    @Test
    public void test_supprimer_personnage() {
        Compte compte = new Compte("Jean", "Mot2p@ssTr3sSecuizer");
        Personnage personnage = new Personnage("Bernard", "Personnage de test", new HashMap<>(), new ArrayList<>(), new ArrayList<>(), "Forgeron", "Nain");
        Personnage personnage2 = new Personnage("Roland", "Personnage de test", new HashMap<>(), new ArrayList<>(), new ArrayList<>(), "Guerrier", "Humain");
        compte.ajouter_personnage(personnage);
        compte.ajouter_personnage(personnage2);
        compte.supprimer_personnage(personnage);

        assertTrue(!compte.personnages.contains(personnage), "La suppression du personnage ne fonctionne pas");
    }

    @Test
    public void test_toString() {
        Compte compte = new Compte("Jean", "Mot2p@ssTr3sSecuizer");

        System.out.println(compte.toString());

    }
}
