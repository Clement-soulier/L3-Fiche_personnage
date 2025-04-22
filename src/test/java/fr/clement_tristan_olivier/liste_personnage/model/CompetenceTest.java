package fr.clement_tristan_olivier.liste_personnage.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;


public class CompetenceTest {

    @Test
    public void test_Competence() {
        Competence competence = new Competence("Rugissement", "Le lanceur pousse un cri tout mimi pour tromper la vigilance de la cible et baisser son Attaque.");

        assertEquals("Rugissement", competence.nom, "Le nom de la compétence n'est pas correct");
        assertEquals("Le lanceur pousse un cri tout mimi pour tromper la vigilance de la cible et baisser son Attaque.", competence.description, "La description de la compétence n'est pas correct");
        assertTrue(Competence.liste_competence.contains(competence), "La compétence n'a pas été ajouté à la liste statique");
    }

    @Test
    public void test_renommer() {
        Competence competence = new Competence("Rugissement", "Le lanceur pousse un cri tout mimi pour tromper la vigilance de la cible et baisser son Attaque.");
        competence.renommer("Hurlement");

        assertEquals("Hurlement", competence.nom, "Le nom de la compétence n'a pas été mis à jour pour la compétence");
        assertEquals("Hurlement", Competence.liste_competence.get(Competence.liste_competence.indexOf(competence)).nom, "Le nom de la compétence n'a pas été mis à jour dans la liste statique");
    }

    @Test
    public void test_changer_description() {
        Competence competence = new Competence("Rugissement", "Le lanceur pousse un cri tout mimi pour tromper la vigilance de la cible et baisser son Attaque.");
        competence.changer_description("Description plus courte");

        assertEquals("Description plus courte", competence.description, "La description de la compétence n'as pas été mis à jour");
        assertEquals("Description plus courte", Competence.liste_competence.get(Competence.liste_competence.indexOf(competence)).description, "La description de la compétence n'as pas été mis à jour pour la liste statique");
    }

    @Test
    public void test_toString() {
        Competence competence = new Competence("Rugissement", "Le lanceur pousse un cri tout mimi pour tromper la vigilance de la cible et baisser son Attaque.");

        System.out.println(competence.toString());
    }
}
