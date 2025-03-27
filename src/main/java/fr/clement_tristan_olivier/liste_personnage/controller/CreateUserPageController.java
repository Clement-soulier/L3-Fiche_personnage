package fr.clement_tristan_olivier.liste_personnage.controller;

import fr.clement_tristan_olivier.liste_personnage.model.Base_de_donnees;
import fr.clement_tristan_olivier.liste_personnage.model.Compte;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;


public class CreateUserPageController {
    private Base_de_donnees base_de_donnees;
    public MainViewController mainViewController;

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private VBox vBox;
    @FXML
    private Label InfoLabel;
    @FXML
    private TextField CreateUserText;
    @FXML
    private TextField CreatePasswordText;
    @FXML 
    TextField ConfirmPasswordText;
    @FXML
    private Button CreateUserButton;


    @FXML
    public void initialize() {
        // Association du bouton à son comportement
        CreateUserButton.setOnAction(event -> handleCreateUserButton());
    }

    public void setModel(Base_de_donnees base_de_donnees) {
        this.base_de_donnees = base_de_donnees;
    }

    @FXML
    private void handleCreateUserButton() {
        // Récupération des informations dans les champs
        String username = CreateUserText.getText();
        String password = CreatePasswordText.getText();
        String confirmPassword = ConfirmPasswordText.getText();

        // Validation des champs
        if (username == null || username.trim().isEmpty()) {
            InfoLabel.setText("Username must be provided");
            return;
        }
        if (password == null || password.trim().isEmpty()) {
            InfoLabel.setText("Password must be provided");
            return;
        }
        if (confirmPassword == null || confirmPassword.trim().isEmpty()) {
            InfoLabel.setText("Please confirm your password");
            return;
        }
        if (!password.equals(confirmPassword)) {
            InfoLabel.setText("Passwords do not match");
            return;
        }

        // Création du compte
        Compte newCompte = new Compte(username, password);

        // Tentative d'ajout du compte dans la base de données
        if (base_de_donnees.ajouter_compte(newCompte)) {
            InfoLabel.setText("Account created successfully");
            mainViewController.chargerLoginPage();
        } else {
            InfoLabel.setText("Account creation failed: Username already exists");
        }
    }
}
