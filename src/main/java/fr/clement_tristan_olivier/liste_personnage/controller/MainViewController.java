package fr.clement_tristan_olivier.liste_personnage.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.Parent;

import java.io.IOException;

import fr.clement_tristan_olivier.liste_personnage.model.Base_de_donnees;
import fr.clement_tristan_olivier.liste_personnage.model.Compte;
import fr.clement_tristan_olivier.liste_personnage.model.Personnage;

public class MainViewController {
    private Base_de_donnees base_de_donnees;
    private Compte compte;

    @FXML
    private VBox rootPane;

    @FXML
    private void initialize() {
        chargerFichePersonnage();
    }

    private void chargerFichePersonnage(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/clement_tristan_olivier/liste_personnage/view/FichePersonnage.fxml"));
            Parent vue = loader.load();

            FichePersonnageController controller = loader.getController();
            Personnage personnage = new Personnage(null, null, null, null, null, null);

            controller.setModele(personnage);
            rootPane.getChildren().setAll(vue);
            

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
