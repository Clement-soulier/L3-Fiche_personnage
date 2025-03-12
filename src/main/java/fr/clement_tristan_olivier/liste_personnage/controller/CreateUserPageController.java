package fr.clement_tristan_olivier.liste_personnage.controller;

import fr.clement_tristan_olivier.liste_personnage.model.Base_de_donnees;
import fr.clement_tristan_olivier.liste_personnage.model.Compte;
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
    private Label label;

    @FXML
    private TextField CreateUserText;

    @FXML
    private TextField CreatePasswordText;

    @FXML
    private Button CreateUserButton;

    private Base_de_donnees base_de_donnees;

    @FXML
    public void initialize() {
        base_de_donnees = new Base_de_donnees();
    }

    @FXML
    private void handleCreateUserButton() {
        String username = CreateUserText.getText();
        String password = CreatePasswordText.getText();
        Compte newCompte = new Compte(username, password);
        if (base_de_donnees.ajouter_compte(newCompte)) {
            label.setText("Account created successfully");
        } else {
            label.setText("Account creation failed: Username already exists");
        }
    }
}
