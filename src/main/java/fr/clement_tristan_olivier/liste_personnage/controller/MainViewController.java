package fr.clement_tristan_olivier.liste_personnage.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

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
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import java.io.File;
import javafx.stage.FileChooser;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ButtonBar;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.stage.Modality;
import java.util.Optional;

public class MainViewController {
    private Base_de_donnees base_de_donnees;
    public Compte compte;
    public Personnage personnage;
    public String currentFile;

    @FXML
    private VBox rootPane;

    @FXML
    private AnchorPane conteneurCentre;

    @FXML
    private MenuBar menuBar;
    @FXML
    private MenuItem MenuBarNew;
    @FXML
    private MenuItem MenuBarOpen;
    @FXML
    private MenuItem MenuBarClose;
    @FXML
    private MenuItem MenuBarSave;
    @FXML
    private MenuItem MenuBarSaveAs;
    @FXML
    private MenuItem MenuBarQuit;
    @FXML
    private MenuItem MenuBarPersonnage;

    @FXML
    private void initialize() {
        // initialisation du modèle
        // Demander à l'utilisateur s'il souhaite charger une base de données
        boolean chargerDepuisFichier = demanderChargementBase();

        if (chargerDepuisFichier) {
            // Ouvrir un FileChooser pour sélectionner le fichier
            File fichier = ouvrirFileChooser();
            if (fichier != null) {
                // Charger les données depuis le fichier
                chargerModele(fichier.getAbsolutePath());
                // Enregistrer le chemin pour enregistrer sur ce fichier
                currentFile = fichier.getAbsolutePath();
            } else {
                // Si aucun fichier n'est sélectionné, initialiser une base vide
                base_de_donnees = new Base_de_donnees();
            }
        } else {
            // Initialiser une base vide
            base_de_donnees = new Base_de_donnees();
        }

        // Association des boutons de la menubar avec leur comportement
        MenuBarNew.setOnAction(_ -> MenuBarNewAction());
        MenuBarOpen.setOnAction(_ -> MenuBarOpenAction());
        MenuBarClose.setOnAction(_ -> MenuBarCloseAction());
        MenuBarSave.setOnAction(_ -> MenuBarSaveAction());
        MenuBarSaveAs.setOnAction(_ -> MenuBarSaveAsAction());
        MenuBarQuit.setOnAction(_ -> MenuBarQuitAction());
        MenuBarPersonnage.setOnAction(_ -> chargerListePersonnage());

        chargerLoginPage();

        // Ajout du gestionnaire lors de la fermeture de la fenêtre
        Platform.runLater(() -> {
            Stage stage = (Stage) rootPane.getScene().getWindow();
            stage.setOnCloseRequest(event -> {
                // Empêcher la fermeture par défaut
                event.consume();
                demanderEnregistrementAvantFermeture(stage);
            });
        });
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

    public void sauvegardeModele(String path){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(base_de_donnees);
            System.out.println("Modèle sauvegardé avec succès dans : " + path);
        } catch (IOException e){
            e.printStackTrace();
            System.out.println("Erreur lors de la sauvegarde du modlèle");
        }
    }

    public void chargerModele(String path){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            base_de_donnees = (Base_de_donnees) ois.readObject();
            System.out.println("Données chargées avec succès depuis " + path);
        } catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
            System.out.println("Erreur lros du chargement des données.");
        }
    }

    private File ouvrirFileChooser() {
        // Paramétrage du FileChooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Sélectionner un fichier de base de données");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichiers sérialisés", "*.ser"));

        // Créer une nouvelle fenêtre (Stage) pour le FileChooser
        Stage fileChooserStage = new Stage();
        fileChooserStage.initModality(Modality.APPLICATION_MODAL); // Bloque l'interaction avec d'autres fenêtres
        fileChooserStage.setTitle("Sélectionner un fichier");

        // Ouvrir le FileChooser dans la nouvelle fenêtre
        return fileChooser.showOpenDialog(fileChooserStage);
    }

    private boolean demanderChargementBase() {
        // Paramétrage fenêtre de dialogue
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Chargement de la base de données");
        alert.setHeaderText("Souhaitez-vous charger une base de données existante ?");
        alert.setContentText("Cliquez sur 'Oui' pour charger une base existante ou sur 'Non' pour commencer avec une base vide.");
        ButtonType buttonOui = new ButtonType("Oui");
        ButtonType buttonNon = new ButtonType("Non", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonOui, buttonNon);

        // Afficher la fenêtre de dialogue et attendre la réponse de l'utilisateur
        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == buttonOui;
    }

    private void demanderEnregistrementAvantFermeture(Stage stage) {
        // Créer une fenêtre de dialogue de confirmation
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Quitter l'application");
        alert.setHeaderText("Voulez-vous enregistrer les modifications avant de quitter ?");
        alert.setContentText("Choisissez une option :");
        ButtonType buttonEnregistrer = new ButtonType("Enregistrer");
        ButtonType buttonNePasEnregistrer = new ButtonType("Ne pas enregistrer");
        ButtonType buttonAnnuler = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonEnregistrer, buttonNePasEnregistrer, buttonAnnuler);

        // Afficher la fenêtre de dialogue et attendre la réponse de l'utilisateur
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent()) {
            if (result.get() == buttonEnregistrer) {
                if(currentFile == null){
                    File fichier = choisirFichierEnregistrement();
                    if(fichier != null){
                        sauvegardeModele(fichier.getAbsolutePath());
                        stage.close();
                    } else {
                        return;
                    }
                } else {
                    // Enregistrer les modifications
                    sauvegardeModele(currentFile); // Remplacez par le chemin souhaité
                    stage.close(); // Fermer la fenêtre
                }
            } else if (result.get() == buttonNePasEnregistrer) {
                // Fermer la fenêtre sans enregistrer
                stage.close();
            } else {
                // Annuler la fermeture
                System.out.println("Fermeture annulée par l'utilisateur.");
            }
        }
    }

    private File choisirFichierEnregistrement() {
        // Paramétrage du FileChooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Enregistrer la base de données");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichiers sérialisés", "*.ser"));

        // Définir un nom de fichier par défaut
        fileChooser.setInitialFileName("base_de_donnees.ser");

        // Créer une nouvelle fenêtre (Stage) pour le FileChooser
        Stage fileChooserStage = new Stage();
        fileChooserStage.initModality(Modality.APPLICATION_MODAL); // Bloque l'interaction avec d'autres fenêtres
        fileChooserStage.setTitle("Enregistrer sous");

        // Ouvrir le FileChooser pour choisir ou créer un fichier
        return fileChooser.showSaveDialog(fileChooserStage);
    }

    private void MenuBarNewAction() {
        // Créer une fenêtre de dialogue de confirmation
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Enregistrement");
        alert.setHeaderText("Voulez-vous enregistrer les modifications");
        alert.setContentText("Choisissez une option :");
        ButtonType buttonEnregistrer = new ButtonType("Enregistrer");
        ButtonType buttonNePasEnregistrer = new ButtonType("Ne pas enregistrer");
        ButtonType buttonAnnuler = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonEnregistrer, buttonNePasEnregistrer, buttonAnnuler);

        // Afficher la fenêtre de dialogue et attendre la réponse de l'utilisateur
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent()) {
            if (result.get() == buttonEnregistrer) {
                if(currentFile == null){
                    File fichier = choisirFichierEnregistrement();
                    if(fichier != null){
                        sauvegardeModele(fichier.getAbsolutePath());
                        // Procédure pour l'ouverture d'une nouvelle base
                        fichier = choisirFichierEnregistrement();
                        if(fichier != null) {
                            currentFile = choisirFichierEnregistrement().getAbsolutePath();
                            base_de_donnees = new Base_de_donnees();
                            chargerLoginPage();
                        } else {
                            base_de_donnees = new Base_de_donnees();
                            chargerLoginPage();
                        }
                    } else {
                        return;
                    }
                } else {
                    // Enregistrer les modifications
                    sauvegardeModele(currentFile); // Remplacez par le chemin souhaité
                    // Procédure pour l'ouverture d'une nouvelle base
                    File fichier = choisirFichierEnregistrement();
                    if(fichier != null) {
                        currentFile = fichier.getAbsolutePath();
                        base_de_donnees = new Base_de_donnees();
                        chargerLoginPage();
                    } else {
                        base_de_donnees = new Base_de_donnees();
                        chargerLoginPage();
                    }
                }
            } else if (result.get() == buttonNePasEnregistrer) {
                // Fermer la fenêtre sans enregistrer
                    // Procédure pour l'ouverture d'une nouvelle base
                    File fichier = choisirFichierEnregistrement();
                    if(fichier != null) {
                        currentFile = fichier.getAbsolutePath();
                        base_de_donnees = new Base_de_donnees();
                        chargerLoginPage();
                    } else {
                        base_de_donnees = new Base_de_donnees();
                        chargerLoginPage();
                    }
            } else {
                // Annuler la fermeture
                System.out.println("Fermeture annulée par l'utilisateur.");
            }
        }
    }

    private void MenuBarOpenAction() {
        // Créer une fenêtre de dialogue de confirmation
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Enregistrement");
        alert.setHeaderText("Voulez-vous enregistrer les modifications");
        alert.setContentText("Choisissez une option :");
        ButtonType buttonEnregistrer = new ButtonType("Enregistrer");
        ButtonType buttonNePasEnregistrer = new ButtonType("Ne pas enregistrer");
        ButtonType buttonAnnuler = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonEnregistrer, buttonNePasEnregistrer, buttonAnnuler);

        // Afficher la fenêtre de dialogue et attendre la réponse de l'utilisateur
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent()) {
            if (result.get() == buttonEnregistrer) {
                if(currentFile == null){
                    File fichier = choisirFichierEnregistrement();
                    if(fichier != null){
                        sauvegardeModele(fichier.getAbsolutePath());
                        // Procédure pour l'ouverture d'une base existente
                        File newfichier = ouvrirFileChooser();
                        if (newfichier != null) {
                            // Charger les données depuis le fichier
                            chargerModele(newfichier.getAbsolutePath());
                            // Enregistrer le chemin pour enregistrer sur ce fichier
                            currentFile = newfichier.getAbsolutePath();
                            chargerLoginPage();
                        } else {
                            // Si aucun fichier n'est sélectionné, initialiser une base vide
                            base_de_donnees = new Base_de_donnees();
                            currentFile = null;
                            chargerLoginPage();
                        }
                    } else {
                        return;
                    }
                } else {
                    // Enregistrer les modifications
                    sauvegardeModele(currentFile); // Remplacez par le chemin souhaité
                    // Procédure pour l'ouverture d'une base existente
                    File newfichier = ouvrirFileChooser();
                    if (newfichier != null) {
                        // Charger les données depuis le fichier
                        chargerModele(newfichier.getAbsolutePath());
                            // Enregistrer le chemin pour enregistrer sur ce fichier
                            currentFile = newfichier.getAbsolutePath();
                            chargerLoginPage();
                    } else {
                        // Si aucun fichier n'est sélectionné, initialiser une base vide
                        currentFile = null;
                        base_de_donnees = new Base_de_donnees();
                        chargerLoginPage();
                    }
                }
            } else if (result.get() == buttonNePasEnregistrer) {
                // Fermer la fenêtre sans enregistrer
                // Procédure pour l'ouverture d'une base existente
                File newfichier = ouvrirFileChooser();
                if (newfichier != null) {
                    // Charger les données depuis le fichier
                    chargerModele(newfichier.getAbsolutePath());
                    // Enregistrer le chemin pour enregistrer sur ce fichier
                    currentFile = newfichier.getAbsolutePath();
                    chargerLoginPage();
                } else {
                    // Si aucun fichier n'est sélectionné, initialiser une base vide
                    currentFile = null;
                    base_de_donnees = new Base_de_donnees();
                    chargerLoginPage();
                }
            } else {
                // Annuler la fermeture
                System.out.println("Fermeture annulée par l'utilisateur.");
            }
        }
    }

    private void MenuBarCloseAction() {
        // Créer une fenêtre de dialogue de confirmation
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Quitter l'application");
        alert.setHeaderText("Voulez-vous enregistrer les modifications avant de quitter ?");
        alert.setContentText("Choisissez une option :");
        ButtonType buttonEnregistrer = new ButtonType("Enregistrer");
        ButtonType buttonNePasEnregistrer = new ButtonType("Ne pas enregistrer");
        ButtonType buttonAnnuler = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonEnregistrer, buttonNePasEnregistrer, buttonAnnuler);

        // Afficher la fenêtre de dialogue et attendre la réponse de l'utilisateur
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent()) {
            if (result.get() == buttonEnregistrer) {
                if(currentFile == null){
                    File fichier = choisirFichierEnregistrement();
                    if(fichier != null){
                        sauvegardeModele(fichier.getAbsolutePath());
                        currentFile = null;
                        base_de_donnees = new Base_de_donnees();
                        chargerLoginPage();
                    } else {
                        return;
                    }
                } else {
                    // Enregistrer les modifications
                    sauvegardeModele(currentFile); // Remplacez par le chemin souhaité
                    currentFile = null;
                    base_de_donnees = new Base_de_donnees();
                    chargerLoginPage();
                }
            } else if (result.get() == buttonNePasEnregistrer) {
                // Fermer la fenêtre sans enregistrer
                currentFile = null;
                base_de_donnees = new Base_de_donnees();
                chargerLoginPage();
            } else {
                // Annuler la fermeture
                System.out.println("Fermeture annulée par l'utilisateur.");
            }
        }
    }

    private void MenuBarSaveAction() {
        if(currentFile != null){
            sauvegardeModele(currentFile);
        } else {
            File fichier = choisirFichierEnregistrement();
            if(fichier != null){
                currentFile = fichier.getAbsolutePath();
                sauvegardeModele(currentFile);
            } else {
                return;
            }
        }
    }

    private void MenuBarSaveAsAction() {
        File fichier = choisirFichierEnregistrement();
        if(fichier != null){
            currentFile = fichier.getAbsolutePath();
            sauvegardeModele(currentFile);
        } else {
            return;
        }
    }

    private void MenuBarQuitAction() {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        demanderEnregistrementAvantFermeture(stage);
    }
}