package fr.clement_tristan_olivier.liste_personnage.controller;

import fr.clement_tristan_olivier.liste_personnage.model.Compte;
import fr.clement_tristan_olivier.liste_personnage.model.Personnage;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;

public class ListePersonnageController {
    public MainViewController mainViewController;
    public Compte compte;

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


    @FXML
    private void initialiserVue() {
        // Agordi la cellFactory por montri toSimpleString()
        ComboBoxListe.setCellFactory(_ -> PersonnageCellule());
        // Agordi la buttonCell por montri toSimpleString() por la elektita objekto
        ComboBoxListe.setButtonCell(PersonnageCellule());
        setup_liste_personnages();
    }

    protected void setModele(Compte model) {
        this.compte = model;
    }

    /**
     * Importe la liste des personnages du compte et les ajoute à la liste déroulante.
     * @return void
     */
    public void setup_liste_personnages() {
        System.out.println(mainViewController.compte);
        System.out.println(mainViewController.compte.personnages.toString());
        this.ComboBoxListe.getItems().addAll(compte.personnages);
    }

    /**
     * Crée un nouveau personnage avec comme nom celui entré dans le champ de texte.
     * @return void
     */
    @FXML
    private void handleAddToListButtonAction() {
        // Personnage newPersonnage = new Personnage(null, null, null, null, null, null, null)
        // this.mainViewController.chargerFichePersonnage();
    }

    /**
     * Supprime le personnage sélectionné dans la liste déroulante.
     * @return void
     */
    @FXML
    private void handleDeleteFromListButtonAction() {
        Personnage personnage = this.ComboBoxListe.getSelectionModel().getSelectedItem();
        this.compte.supprimer_personnage(personnage);
        this.ComboBoxListe.getItems().remove(personnage);
    }

    /**
     * Ferme la page ListePersonnage.fxml.
     * @return void
     */
    @FXML
    private void handleClosePageButtonAction() {
        this.mainViewController.chargerLoginPage();
    }

    @FXML
    private void handleEditFromListButtonAction() {
        System.out.println("Displaying personnage");
        Personnage personnage = this.ComboBoxListe.getSelectionModel().getSelectedItem();
        // this.mainViewController.chargerFichePersonnage(personnage);
    }

    private ListCell<Personnage> PersonnageCellule() {
        return new ListCell<Personnage>() {
            @Override
            protected void updateItem(Personnage item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.toSimpleString());
            }
        };
    }
}
