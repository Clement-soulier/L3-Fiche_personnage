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

    @FXML TextField ConfirmPasswordText;

    @FXML
    private Button CreateUserButton;

    private Base_de_donnees base_de_donnees;
    public MainViewController mainViewController;

    @FXML
    public void initialize() {
        base_de_donnees = new Base_de_donnees();
    }

    public void setModel(Base_de_donnees base_de_donnees) {
        this.base_de_donnees = base_de_donnees;
    }
    

    @FXML
    private void handleCreateUserButton() {
        String username = CreateUserText.getText();
        String password = CreatePasswordText.getText();
        String confirmPassword = ConfirmPasswordText.getText();
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
        Compte newCompte = new Compte(username, password);
        if (base_de_donnees.ajouter_compte(newCompte)) {
            InfoLabel.setText("Account created successfully");
        } else {
            InfoLabel.setText("Account creation failed: Username already exists");
        }
        if (mainViewController != null) {
            mainViewController.chargerLoginPage();
        }
        System.out.println(username + " " + password);
    }
}
