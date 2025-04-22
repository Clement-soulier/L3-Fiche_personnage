package fr.clement_tristan_olivier.liste_personnage.model;

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
    public void test_toString() {
        Statistique statistique = new Statistique("Attaque", "Les dégâts bruts lors d'une attaque");

        System.out.println(statistique.toString());
    }
}
