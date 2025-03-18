package fr.clement_tristan_olivier.liste_personnage.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.Parent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import fr.clement_tristan_olivier.liste_personnage.model.*;

public class MainViewController {
    private Base_de_donnees base_de_donnees;
    private Compte compte;

    @FXML
    private VBox rootPane;

    @FXML
    private AnchorPane conteneurCentre;

    @FXML
    private void initialize() {
        chargerFichePersonnage();
    }

    private void chargerFichePersonnage(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/clement_tristan_olivier/liste_personnage/view/FichePersonnage.fxml"));
            Parent vue = loader.load();

            FichePersonnageController controller = loader.getController();
            
            HashMap<Statistique, Integer> statistiques = new HashMap<>();
            Statistique stat1 = new Statistique("attaque", "capacité à faire mal");
            Statistique stat2 = new Statistique("défense", "capacité à encaisser les coûts");
            statistiques.put(stat1, 20);
            statistiques.put(stat2, 40);

            ArrayList<Competence> competences = new ArrayList<>();
            Competence comp1 =new Competence("quoi", "feur");
            Competence comp2 =new Competence("brulure", "ça brule");
            competences.add(comp1);
            competences.add(comp2);

            ArrayList<Equipement> equipements = new ArrayList<>();
            Equipement equip1 =new Equipement("épée", "Un épée qui coupe");
            Equipement equip2 =new Equipement("Bouclier", "Un bouclier qui protège de tout");
            equipements.add(equip1);
            equipements.add(equip2);

            Personnage personnage = new Personnage("Robert", "Un personnage de test très badass", statistiques, competences, equipements, "Barbare");

            controller.setModele(personnage);
            conteneurCentre.getChildren().setAll(vue);
            

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void chargerLoginPage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/clement_tristan_olivier/liste_personnage/view/LoginPage.fxml"));
            Parent vue = loader.load();

            LoginPageController controller = loader.getController();
            controller.setModel(base_de_donnees);
            
            rootPane.getChildren().setAll(vue);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

private void chargerCreateUserPage() {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/clement_tristan_olivier/liste_personnage/view/CreateUserPage.fxml"));
        Parent vue = loader.load();

        CreateUserPageController controller = loader.getController();
        controller.setModel(base_de_donnees);

        rootPane.getChildren().setAll(vue);

    } catch (IOException e) {
        e.printStackTrace();
    }
}
}
    // private void chargerVueSecondaire(String cheminFXML) {
    //     try {
    //         FXMLLoader loader = new FXMLLoader(getClass().getResource(cheminFXML));
    //         AnchorPane vue = loader.load();
    //         conteneurCentre.getChildren().setAll(vue);

    //         // Récupérer le contrôleur de la vue secondaire si nécessaire
    //         // ControleurSecondaire controleur = loader.getController();
    //         // controleur.setControleurPrincipal(this);

    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    // }
