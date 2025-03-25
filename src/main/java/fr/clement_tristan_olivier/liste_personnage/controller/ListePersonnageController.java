package fr.clement_tristan_olivier.liste_personnage.controller;

import fr.clement_tristan_olivier.liste_personnage.model.Compte;
import fr.clement_tristan_olivier.liste_personnage.model.Personnage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class ListePersonnageController {

    @FXML
    private ComboBox<Personnage> ComboBoxListe;

    @FXML
    private Button DeleteFromListButton;

    @FXML
    private TextField AddText;

    @FXML
    private Button AddToListButton;

    @FXML
    private Button ClosePageButton;

    private Compte model;

    protected void setModele(Compte model) {
        this.model = model;
        setup_liste_personnages();
    }

    /**
     * Importe la liste des personnages du compte et les ajoute à la liste déroulante.
     * @return void
     */
    private void setup_liste_personnages() {
        for (Personnage personnage : this.model.personnages) {
            this.ComboBoxListe.getItems().add(personnage);
        }
    }

    /**
     * Crée un nouveau personnage avec comme nom celui entré dans le champ de texte.
     * @return void
     */
    @FXML
    private void handleAddToListButtonAction() {
        if (!this.AddText.getText().trim().isEmpty()) {
            String nom_personnage = this.AddText.getText();
            Personnage personnage = new Personnage(nom_personnage, null, null, null, null, null, null);
            this.model.ajouter_personnage(personnage);
            this.ComboBoxListe.getItems().add(personnage);
        }
    }

    /**
     * Supprime le personnage sélectionné dans la liste déroulante.
     * @return void
     */
    @FXML
    private void handleDeleteFromListButtonAction() {
        Personnage personnage = this.ComboBoxListe.getSelectionModel().getSelectedItem();
        this.model.supprimer_personnage(personnage);
        this.ComboBoxListe.getItems().remove(personnage);
    }

    /**
     * Ferme la page ListePersonnage.fxml.
     * @return void
     */
    @FXML
    private void handleClosePageButtonAction() {
        ((javafx.stage.Stage)ClosePageButton.getScene().getWindow()).close();
    }
}
