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
    private Button ConnectionButton;

    @FXML
    private Hyperlink chartlink;

    private Base_de_donnees base_de_donnees;
    public MainViewController mainViewController;

    @FXML
public void initialize() {
    base_de_donnees = new Base_de_donnees();

    ConnectionButton.setDisable(true);

    CheckBox.setOnAction(event -> handleCheckBoxAction());
}

    public void setModel(Base_de_donnees base_de_donnees) {
        this.base_de_donnees = base_de_donnees;
    }

    @FXML
    private void handleLoginButton() {
        String username = Login.getText();
        String password = Password.getText();
        Compte compte = base_de_donnees.authenticate(username, password);
        if (compte != null) {
            System.out.println("Login successful");
            InfoLabel.setText("Login successful");
        } else {
            System.out.println("Login failed");
            InfoLabel.setText("Login failed");
        }
        // if (mainViewController != null) {
        //     mainViewController.chargerFichePersonnage();
        // }
    }
    @FXML
    private void handleCreateAccountButton() {
        if (mainViewController != null) {
            mainViewController.chargerCreateUserPage();
        }
    }
    @FXML
    private void handleHyperlinkAction() {
        try {
            String videoUrl = "https://www.youtube.com/watch?v=xvFZjo5PgG0";
            java.awt.Desktop.getDesktop().browse(new java.net.URI(videoUrl));
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
