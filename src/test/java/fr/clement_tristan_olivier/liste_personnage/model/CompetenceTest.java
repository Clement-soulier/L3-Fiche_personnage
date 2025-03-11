package fr.clement_tristan_olivier.liste_personnage.model;

import java.util.ArrayList;
import java.util.HashMap;

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
    public void test_supprime_globale() {
        Compte compte = new Compte("Jean", "Mot2p@ssTr3sSecuizer");
        Competence competence = new Competence("Rugissement", "Le lanceur pousse un cri tout mimi pour tromper la vigilance de la cible et baisser son Attaque.");
        Competence competence2 = new Competence("Danse Lames", "Une danse frénétique qui exalte l'esprit combatif. Augmente beaucoup l'Attaque du lanceur.");
        Competence competence3 = new Competence("Charge", "Le lanceur charge l'ennemi et le percute de tout son poids.");
        Personnage personnage = new Personnage("Jean", "Personnage de test", new HashMap<Statistique, Integer>(), new ArrayList<Competence>(), new ArrayList<Equipement>(), "testeur");
        Personnage personnage2 = new Personnage("Claude", "Personnage de test", new HashMap<Statistique, Integer>(), new ArrayList<Competence>(), new ArrayList<Equipement>(), "testeur");

        personnage.ajouter_competence(competence);
        personnage2.ajouter_competence(competence);
        personnage2.ajouter_competence(competence2);
        personnage2.ajouter_competence(competence3);
        compte.ajouter_personnage(personnage);
        compte.ajouter_personnage(personnage2);

        Competence.supprime_globale(competence);

        assertTrue(!Competence.liste_competence.contains(competence), "La compétence n'as pas été supprimé de la liste globale");
        assertTrue(!personnage.competences.contains(competence), "La compétence n'as pas été supprimée du premier personnage");
        assertTrue(!personnage2.competences.contains(competence), "La compétence n'as pas été supprimée du deuxième personnage");
        assertTrue(personnage2.competences.contains(competence2), "La mauvaise compétence à été supprimée");
        assertTrue(personnage2.competences.contains(competence3), "La mauvaise compétence à été supprimée");
    }

    @Test
    public void test_toString() {
        Competence competence = new Competence("Rugissement", "Le lanceur pousse un cri tout mimi pour tromper la vigilance de la cible et baisser son Attaque.");

        System.out.println(competence.toString());
    }
}
