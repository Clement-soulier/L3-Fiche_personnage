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
    private Button ViewPersonnage;
    @FXML
    private Button ClosePageButton;


    @FXML
    public void initialiserVue() {
        // Associe les boutons à leur comportement
        DeleteFromListButton.setOnAction(_ -> handleDeleteFromListButtonAction());
        EditFromListButton.setOnAction(_ -> handleEditFromListButtonAction());
        AddToListButton.setOnAction(_ -> handleAddToListButtonAction());
        ClosePageButton.setOnAction(_ -> handleClosePageButtonAction());
        ViewPersonnage.setOnAction(_ -> handleViewPersonnageActioon());

        // Set configure et initialise la comboBox
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
        this.ComboBoxListe.getItems().addAll(compte.personnages);
    }

    /**
     * Crée un nouveau personnage avec comme nom celui entré dans le champ de texte.
     * @return void
     */
    @FXML
    private void handleAddToListButtonAction() {
        Personnage newPersonnage = new Personnage();
        mainViewController.personnage = newPersonnage;
        this.mainViewController.chargerFichePersonnage();
    }

    /**
     * Supprime le personnage sélectionné dans la liste déroulante.
     * @return void
     */
    @FXML
    private void handleDeleteFromListButtonAction() {
        Personnage personnage = this.ComboBoxListe.getSelectionModel().getSelectedItem();
        if(personnage == null){
            return;
        }
        this.compte.supprimer_personnage(personnage);
        this.ComboBoxListe.getItems().remove(personnage);
    }

    @FXML
    private void handleViewPersonnageActioon() {
        Personnage personnage = this.ComboBoxListe.getSelectionModel().getSelectedItem();
        if(personnage == null){
            return;
        }
        this.mainViewController.personnage = personnage;
        this.mainViewController.chargerAffichageFichePersonnage();
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
        Personnage personnage = this.ComboBoxListe.getSelectionModel().getSelectedItem();
        if(personnage == null){
            return;
        }
        mainViewController.personnage = personnage;
        this.mainViewController.chargerFichePersonnage();
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
