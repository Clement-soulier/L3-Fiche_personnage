package fr.clement_tristan_olivier.liste_personnage.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import fr.clement_tristan_olivier.liste_personnage.model.Base_de_donnees;
import fr.clement_tristan_olivier.liste_personnage.model.Classe;
import fr.clement_tristan_olivier.liste_personnage.model.Competence;
import fr.clement_tristan_olivier.liste_personnage.model.Compte;
import fr.clement_tristan_olivier.liste_personnage.model.Equipement;
import fr.clement_tristan_olivier.liste_personnage.model.Personnage;
import fr.clement_tristan_olivier.liste_personnage.model.Race;
import fr.clement_tristan_olivier.liste_personnage.model.Statistique;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MainViewController {
    private Base_de_donnees base_de_donnees;
    public Compte compte;
    public Personnage personnage;

    @FXML
    private VBox rootPane;

    @FXML
    private AnchorPane conteneurCentre;

    @FXML
    private MenuBar menuBar;

    @FXML
    private void initialize() {
        // initialisation du modèle
        // ajouter code pour récupérer la sérialisation
        base_de_donnees = new Base_de_donnees();
        chargerLoginPage();
    }

    public void chargerFichePersonnage(){
        try {
            // Chargement de la vue
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/clement_tristan_olivier/liste_personnage/view/FichePersonnage.fxml"));
            Parent vue = loader.load();

            // Récupération du controleur
            FichePersonnageController controller = loader.getController();

            // Passage du modèle et du controler principal au controleur de la vue
            controller.setModele(personnage);
            controller.mainViewController = this;

            // Afficher la vue dans la fenêtre
            conteneurCentre.getChildren().setAll(vue);
            // afficher la menu bar
            menuBar.setVisible(true);
            menuBar.setManaged(true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void chargerLoginPage() {
        try {
            // chargement de la vue
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/clement_tristan_olivier/liste_personnage/view/LoginPage.fxml"));
            Parent vue = loader.load();

            // Récuparation du controller
            LoginPageController controller = loader.getController();

            // passage du modèle et du controleur principal au controleur de la vue
            controller.setModel(base_de_donnees);
            controller.mainViewController=this;

            // afficher la vue dans la fenêtre
            conteneurCentre.getChildren().setAll(vue);

            // masquer la menu Bar
            menuBar.setVisible(false);
            menuBar.setManaged(false);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void chargerCreateUserPage() {
        try {
            // charger la vue
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/clement_tristan_olivier/liste_personnage/view/CreateUserPage.fxml"));
            Parent vue = loader.load();

            // Récupération du controleur
            CreateUserPageController controller = loader.getController();

            // Passage du modèle et du controleur principal au controleur
            controller.setModel(base_de_donnees);
            controller.mainViewController=this;

            // Affichage de la vue dans la fenêtre
            conteneurCentre.getChildren().setAll(vue);

            // Masquer la menu bar
            menuBar.setVisible(false);
            menuBar.setManaged(false);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void chargerListePersonnage() {
        try {
            // charger la vue
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/clement_tristan_olivier/liste_personnage/view/ListePersonnage.fxml"));
            Parent vue = loader.load();

            // Récupération du controleur
            ListePersonnageController controller = loader.getController();

            // passage du modèle et du controleur principal au controleur
            controller.setModele(compte);
            controller.mainViewController = this;

            controller.initialiserVue();

            // affichage de la vue dans la fenêtre
            conteneurCentre.getChildren().setAll(vue);

            // Afficher la menu bar
            menuBar.setVisible(true);
            menuBar.setManaged(true);

            // rootPane.getChildren().setAll(vue);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("エラーが発生しました！FXMLが見つかりません！");
        }
    }
}