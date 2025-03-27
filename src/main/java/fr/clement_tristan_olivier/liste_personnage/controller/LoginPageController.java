package fr.clement_tristan_olivier.liste_personnage.controller;

import fr.clement_tristan_olivier.liste_personnage.model.Base_de_donnees;
import fr.clement_tristan_olivier.liste_personnage.model.Compte;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class LoginPageController {
    private Base_de_donnees base_de_donnees;
    public MainViewController mainViewController;

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private VBox vBox;
    @FXML
    private Label InfoLabel;
    @FXML
    private TextField Login;
    @FXML
    private PasswordField Password;
    @FXML
    private CheckBox CheckBox;
    @FXML
    private Button CreateUserButton;
    @FXML
    private Button ConnectionButton;
    @FXML
    private Hyperlink chartlink;


    @FXML
    public void initialize() {
        // Désactive le bouton de connexion
        ConnectionButton.setDisable(true);

        // Association des boutons à leur comportement
        ConnectionButton.setOnAction(_ -> handleLoginButton());
        CheckBox.setOnAction(_ -> handleCheckBoxAction());
        CreateUserButton.setOnAction(_ -> handleCreateAccountButton());

    }

    public void setModel(Base_de_donnees base_de_donnees) {
        this.base_de_donnees = base_de_donnees;
    }

    @FXML
    private void handleLoginButton() {
        // Récupération des informations de login dans les champs
        String username = Login.getText();
        String password = Password.getText();

        // Tentative d'authentification auprès de la base de données
        Compte compte = base_de_donnees.authenticate(username, password);

        // Si la connexion à réussi
        if (compte != null) {
            // Mise à jour du label d'information
            InfoLabel.setText("Login successful");

            // Passage du compte au controleur principale
            mainViewController.compte = compte;

            // Chargement de la prochaine vue
            mainViewController.chargerListePersonnage();
        } else {
            // Mise à jour du label d'information
            InfoLabel.setText("Login failed");
        }
    }

    @FXML
    private void handleCreateAccountButton() {
        mainViewController.chargerCreateUserPage();
    }

    @FXML
    private void handleHyperlinkAction() {
        try {
            // ouverture des conditions d'utilisation
            String videoUrl = "https://www.youtube.com/watch?v=hB7CDrVnNCs";
            java.awt.Desktop.getDesktop().browse(new java.net.URI(videoUrl));

            // Miseà jour du label d'information
            InfoLabel.setText("Please check the box to confirm you have been Rick Rolled.");
        } catch (Exception e) {
            e.printStackTrace();
            InfoLabel.setText("Failed to open the video. Please try again.");
        }
    }

    @FXML
    private void handleCheckBoxAction() {
        ConnectionButton.setDisable(!CheckBox.isSelected());
    }
}
