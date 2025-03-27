package fr.clement_tristan_olivier.liste_personnage.controller;

import fr.clement_tristan_olivier.liste_personnage.model.Compte;
import fr.clement_tristan_olivier.liste_personnage.model.Personnage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class ListePersonnageController {

    @FXML
    private ComboBox<Personnage> ComboBoxListe;

    @FXML
    private Button DeleteFromListButton;

    @FXML
    private Button EditFromListButton;

    @FXML
    private Button AddToListButton;

    @FXML
    private Button ClosePageButton;

    public MainViewController mainViewController;
    private Compte model;

    @FXML
    private void initialize() {
        System.out.println("Initialisation ListePersonnageController");
        ComboBoxListe.setCellFactory(lv -> new javafx.scene.control.ListCell<>() {
            @Override
            protected void updateItem(Personnage item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.toSimpleString());
            }
        });
    }

    protected void setModele(Compte model) {
        System.out.println("Setting compte to " + model.toString());
        this.model = model;
        setup_liste_personnages();
    }

    /**
     * Importe la liste des personnages du compte et les ajoute à la liste déroulante.
     * @return void
     */
    private void setup_liste_personnages() {
        System.out.println("Setting up liste personnage");
        this.ComboBoxListe.getItems().addAll(model.personnages);
    }

    /**
     * Crée un nouveau personnage avec comme nom celui entré dans le champ de texte.
     * @return void
     */
    @FXML
    private void handleAddToListButtonAction() {
        System.out.println("Adding personnage");
        // this.mainViewController.chargerFichePersonnage();
    }

    /**
     * Supprime le personnage sélectionné dans la liste déroulante.
     * @return void
     */
    @FXML
    private void handleDeleteFromListButtonAction() {
        Personnage personnage = this.ComboBoxListe.getSelectionModel().getSelectedItem();
        System.out.println("Deleting personnage " + personnage.toString());
        this.model.supprimer_personnage(personnage);
        this.ComboBoxListe.getItems().remove(personnage);
    }

    /**
     * Ferme la page ListePersonnage.fxml.
     * @return void
     */
    @FXML
    private void handleClosePageButtonAction() {
        System.out.println("Logging out...");
        this.mainViewController.chargerLoginPage();
    }

    @FXML
    private void handleEditFromListButtonAction() {
        System.out.println("Displaying personnage");
        Personnage personnage = this.ComboBoxListe.getSelectionModel().getSelectedItem();
        this.mainViewController.chargerFichePersonnage(personnage);
    }
}
