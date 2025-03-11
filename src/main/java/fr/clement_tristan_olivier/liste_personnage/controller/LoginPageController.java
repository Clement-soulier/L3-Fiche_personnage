package fr.clement_tristan_olivier.liste_personnage.controller;

import fr.clement_tristan_olivier.liste_personnage.model.Base_de_donnees;

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
    private TextField textField;

    @FXML
    private Label label0;

    @FXML
    private PasswordField passwordField;

    @FXML
    private CheckBox checkBox;

    @FXML
    private Button button;

    @FXML
    private Hyperlink hyperlink;

    private UserModel userModel;

    @FXML
    public void initialize() {
        userModel = new UserModel();
    }

    private Base_de_donnees base_de_donnees;

    private void handleLoginButtonAction() {
        String username = textField.getText();
        String password = passwordField.getText();
        if (base_de_donnees.authenticate(username, password)!=null) {
            System.out.println("Login successful");
        } else {
            System.out.println("Login failed");
        }
    }

    

    
}