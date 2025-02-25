package fr.clement_tristan_olivier.liste_personnage.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class PersonnageTest {
    
    @Test
    public void test_Personnage() {
        Personnage personnage = new Personnage("Jean", "Personnage de test", new HashMap<Statistique, Integer>(), new ArrayList<Competence>(), new ArrayList<Equipement>(), "testeur");

        assertEquals("Jean", personnage.nom, "Le nom du personnage n'est pas valide");
        assertEquals("Personnage de test", personnage.biographie, "La biographie du personnage n'est pas valide");
        assertTrue(personnage.statistiques instanceof HashMap<Statistique, Integer>, "La liste de statistique n'est pas du bon type");
        assertTrue(personnage.competences instanceof ArrayList<Competence>, "La liste de compétence n'est pas du bon type");
        assertTrue(personnage.equipements instanceof ArrayList<Equipement>, "La liste d'équipement n'est pas du bon type");
        assertEquals("testeur", personnage.classe, "La classe du personnage n'est pas valide");
    }

    @Test
    public void test_ajoute_competence() {
        Personnage personnage = new Personnage("Jean", "Personnage de test", new HashMap<Statistique, Integer>(), new ArrayList<Competence>(), new ArrayList<Equipement>(), "testeur");
        Competence competence = new Competence("Rugissement", "Le lanceur pousse un cri tout mimi pour tromper la vigilance de la cible et baisser son Attaque.");
        personnage.ajoute_competence(competence);

        assertTrue(personnage.competences.contains(competence), "La compétence n'as pas été ajouté au personnage");
    }

    @Test
    public void test_supprime_competence() {
        Personnage personnage = new Personnage("Jean", "Personnage de test", new HashMap<Statistique, Integer>(), new ArrayList<Competence>(), new ArrayList<Equipement>(), "testeur");
        Competence competence = new Competence("Rugissement", "Le lanceur pousse un cri tout mimi pour tromper la vigilance de la cible et baisser son Attaque.");
        Competence competence1 = new Competence("Danse Lames", "Une danse frénétique qui exalte l'esprit combatif. Augmente beaucoup l'Attaque du lanceur.");

        personnage.ajoute_competence(competence);
        personnage.ajoute_competence(competence1);
        personnage.supprime_competence(competence);

        assertTrue(!personnage.competences.contains(competence), "La compétence n'as pas été suprimé du personnage");
        assertTrue(personnage.competences.contains(competence1), "La mauvaise compétence à été supprimé");
    }

    @Test
    public void test_ajoute_equipement() {
        Personnage personnage = new Personnage("Jean", "Personnage de test", new HashMap<Statistique, Integer>(), new ArrayList<Competence>(), new ArrayList<Equipement>(), "testeur");
        Equipement equipement = new Equipement("épée", "Une épée très tranchante 😱");

        personnage.ajoute_equipement(equipement);
        assertTrue(personnage.equipements.contains(equipement), "L'équipement n'as pas été ajouté au personnage");

    }

    @Test
    public void test_supprime_equipement() {
        Personnage personnage = new Personnage("Jean", "Personnage de test", new HashMap<Statistique, Integer>(), new ArrayList<Competence>(), new ArrayList<Equipement>(), "testeur");
        Equipement equipement = new Equipement("épée", "Une épée très tranchante 😱");
        Equipement equipement1 = new Equipement("hache", "Une hache très tranchante 😱");

        personnage.ajoute_equipement(equipement);
        personnage.ajoute_equipement(equipement1);
        personnage.supprime_equipement(equipement);

        assertTrue(!personnage.equipements.contains(equipement), "L'équipement n'as pas été supprimé");
        assertTrue(personnage.equipements.contains(equipement1), "Le mauvais équipement a été supprimé");
    }

    @Test
    public void test_ajoute_statistique() {
        Personnage personnage = new Personnage("Jean", "Personnage de test", new HashMap<Statistique, Integer>(), new ArrayList<Competence>(), new ArrayList<Equipement>(), "testeur");
        Statistique statistique = new Statistique("Attaque", "Les dégâts bruts lors d'une attaque");

        personnage.ajoute_statistique(statistique, 15);

        assertTrue(personnage.statistiques.containsKey(statistique), "La statistique n'as pas été ajouté");
    }

    @Test
    public void test_modifie_statistique() {
        Personnage personnage = new Personnage("Jean", "Personnage de test", new HashMap<Statistique, Integer>(), new ArrayList<Competence>(), new ArrayList<Equipement>(), "testeur");
        Statistique statistique = new Statistique("Attaque", "Les dégâts bruts lors d'une attaque");

        personnage.ajoute_statistique(statistique, 15);
        personnage.modifie_statistique(statistique, 9);

        assertEquals(9, personnage.statistiques.get(statistique), "La statistique n'as pas été modifié");
    }

    @Test
    public void test_supprime_statistique() {
        Personnage personnage = new Personnage("Jean", "Personnage de test", new HashMap<Statistique, Integer>(), new ArrayList<Competence>(), new ArrayList<Equipement>(), "testeur");
        Statistique statistique = new Statistique("Attaque", "Les dégâts bruts lors d'une attaque");
        Statistique statistique1 = new Statistique("Défense", "La capacité à encaisser les attaquesz");

        personnage.ajoute_statistique(statistique, 90);
        personnage.ajoute_statistique(statistique1, 100);

        personnage.supprime_statistique(statistique);

        assertTrue(!personnage.statistiques.containsKey(statistique), "La statistique n'as pas été supprimée");
        assertTrue(personnage.statistiques.containsKey(statistique1), "La mauvaise statistique a été supprimée");
    }

    @Test
    public void test_modifier_nom() {
        Personnage personnage = new Personnage("Jean", "Personnage de test", new HashMap<Statistique, Integer>(), new ArrayList<Competence>(), new ArrayList<Equipement>(), "testeur");
        personnage.modifie_nom("Loïc");

        assertEquals("Loïc", personnage.nom, "Le nom n'as pas été modifié");
    }

    @Test
    public void test_modifier_biographie() {
        Personnage personnage = new Personnage("Jean", "Personnage de test", new HashMap<Statistique, Integer>(), new ArrayList<Competence>(), new ArrayList<Equipement>(), "testeur");
        personnage.modifie_biographie("Originaire de Khalos");

        assertEquals("Originaire de Khalos", personnage.biographie, "La biographie n'as pas été modifiée");
    }

    @Test
    public void test_toString() {
        Personnage personnage = new Personnage("Jean", "Personnage de test", new HashMap<Statistique, Integer>(), new ArrayList<Competence>(), new ArrayList<Equipement>(), "testeur");
        
        System.out.println(personnage.toString());
    }
}
