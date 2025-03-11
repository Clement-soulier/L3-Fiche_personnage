package fr.clement_tristan_olivier.liste_personnage.model;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class StatistiqueTest {
    
    @Test
    public void test_Statistique() {
        Statistique statistique = new Statistique("Attaque", "Les dégâts bruts lors d'une attaque");

        assertEquals("Attaque", statistique.nom, "Le nom de la statistique n'as pas été enregistré correctement");
        assertEquals("Les dégâts bruts lors d'une attaque", statistique.description, "La description de la statistique n'as pas été enregistré correctement");
        assertTrue(Statistique.liste_stats.contains(statistique), "La statistique n'as pas été ajouté à la liste globale");
    }

    @Test
    public void test_renommer() {
        Statistique statistique = new Statistique("Attaque", "Les dégâts bruts lors d'une attaque");
        statistique.renommer("dégâts");

        assertEquals("dégâts", statistique.nom, "Le nom de la statistique n'a pas été changé pour la statistique");
        assertEquals("dégâts", Statistique.liste_stats.get(Statistique.liste_stats.indexOf(statistique)).nom, "Le nom de la statistique n'a pas été changeé dans la liste globale");
    }

    @Test
    public void test_changer_description() {
        Statistique statistique = new Statistique("Attaque", "Les dégâts bruts lors d'une attaque");
        statistique.changer_description("ça fait mal 😥");

        assertEquals("ça fait mal 😥", statistique.description, "La description de la statistique n'as pas été mis à jour dans la statistique");
        assertEquals("ça fait mal 😥", Statistique.liste_stats.get(Statistique.liste_stats.indexOf(statistique)).description, "La description de la statistique n'as pas été mis à jour dans la liste globlae");
    }

    @Test
    public void test_supprime_globale() {
        Compte compte = new Compte("Jean", "Mot2p@ssTr3sSecuizer");
        Statistique statistique = new Statistique("Attaque", "Les dégâts bruts lors d'une attaque");
        Statistique statistique1 = new Statistique("Défense", "La capacité à encaisser les attaquesz");
        Personnage personnage = new Personnage("Jean", "Personnage de test", new HashMap<Statistique, Integer>(), new ArrayList<Competence>(), new ArrayList<Equipement>(), "testeur");
        Personnage personnage2 = new Personnage("Claude", "Personnage de test", new HashMap<Statistique, Integer>(), new ArrayList<Competence>(), new ArrayList<Equipement>(), "testeur");

        personnage.ajouter_statistique(statistique, 15);
        personnage2.ajouter_statistique(statistique, 20);
        personnage2.ajouter_statistique(statistique1, 15);
        compte.ajouter_personnage(personnage);
        compte.ajouter_personnage(personnage2);

        Statistique.supprime_globale(statistique);

        assertTrue(!personnage.statistiques.containsKey(statistique), "La statistique n'as pas été supprimé pour le premier personnage");
        assertTrue(!personnage2.statistiques.containsKey(statistique), "La statistique n'as pas été supprimé pour le deuxième personnage");
        assertTrue(!Statistique.liste_stats.contains(statistique), "La statistique n'as pas été supprimé pour la liste globale");
        assertTrue(personnage2.statistiques.containsKey(statistique1), "La mauvaise statistique à été supprimée");
    }

    @Test
    public void test_toString() {
        Statistique statistique = new Statistique("Attaque", "Les dégâts bruts lors d'une attaque");

        System.out.println(statistique.toString());
    }
}
