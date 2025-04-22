package fr.clement_tristan_olivier.liste_personnage.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class EquipementTest {
    
    @Test
    public void test_Equipement() {
        Equipement equipement = new Equipement("épée", "Une épée très tranchante 😱");

        assertEquals("épée", equipement.nom, "Le nom de l'équipement n'as pas été enregistré");
        assertEquals("Une épée très tranchante 😱", equipement.description, "La descriptoin de l'équipement n'as pas été enregistrée");
        assertTrue(Equipement.liste_equipement.contains(equipement));
    }

    @Test
    public void test_renommer() {
        Equipement equipement = new Equipement("épée", "Une épée très tranchante 😱");
        equipement.renommer("Hache");

        assertEquals("Hache", equipement.nom, "Le nom de l'équipement n'as pas été modifié pour l'objet");
        assertEquals("Hache", Equipement.liste_equipement.get(Equipement.liste_equipement.indexOf(equipement)).nom, "Le nom de l'équipement n'as pas été changé dans la liste statique");
    }

    @Test
    public void test_changer_description() {
        Equipement equipement = new Equipement("épée", "Une épée très tranchante 😱");
        equipement.changer_description("une hache tres tranchante 😱");

        assertEquals("une hache tres tranchante 😱", equipement.description, "Le nom de l'équipement n'as pas été modifié pour l'objet");
        assertEquals("une hache tres tranchante 😱", Equipement.liste_equipement.get(Equipement.liste_equipement.indexOf(equipement)).description, "Le nom de l'équipement n'as pas été changé dans la liste statique");
    }

    @Test
    public void test_toString() {
        Equipement equipement = new Equipement("épée", "Une épée très tranchante 😱");

        System.out.println(equipement.toString());
    }
}
