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
    private Label label;

    @FXML
    private TextField Login;

    @FXML
    private Label label0;

    @FXML
    private PasswordField Password;

    @FXML
    private CheckBox checkBox;

    @FXML
    private Button button;

    @FXML
    private Hyperlink hyperlink;

    private Base_de_donnees base_de_donnees;

    @FXML
    public void initialize() {
        base_de_donnees = new Base_de_donnees();
    }

    public void setModel(Base_de_donnees base_de_donnees) {
        this.base_de_donnees = base_de_donnees;
    }

    private void handleLoginButton() {
        String username = Login.getText();
        String password = Password.getText();
        Compte compte = base_de_donnees.authenticate(username, password);
        if (compte != null) {
            System.out.println("Login successful");
            label.setText("Login successful");
        } else {
            System.out.println("Login failed");
            label.setText("Login failed");
        }
    }
}