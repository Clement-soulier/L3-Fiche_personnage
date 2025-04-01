package fr.clement_tristan_olivier.liste_personnage.controller;

import javafx.scene.control.Label;
import fr.clement_tristan_olivier.liste_personnage.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.*;

public class EditPropertyController {
    private String[] properties = {"Classes", "Compétences", "Equipements", "Races", "Statistiques"};

    @FXML
    private ComboBox<String> comboBoxPropertySelect;
    @FXML
    private Label labelProperty;
    @FXML
    private ComboBox<Object> comboBoxProperty;
    @FXML
    private TextField textFieldName;
    @FXML
    private Label labelDesc;
    @FXML
    private TextField textFieldDesc;
    @FXML
    private Button btnValider;


    @FXML
    private void initialize(){
        // Set la comboBox des types de propriété
        comboBoxPropertySelect.getItems().setAll(properties);
        // Set le listener pour exécuter du code lors du changement
        comboBoxPropertySelect.getSelectionModel().selectedItemProperty().addListener((_, _, newValue) -> {
            propertySelectionChangedAction(newValue);
        });

        // Set le listener pour remplir les champs lors du changement de propriété
        comboBoxProperty.getSelectionModel().selectedItemProperty().addListener((_, _, newValue) -> {
            setTextFieldsForProperty(newValue);
        });

        // Set le comportement du bouton valider
        btnValider.setOnAction(_ -> handleValidateButton());
    }

    private void propertySelectionChangedAction(String property){
        comboBoxProperty.getSelectionModel().clearSelection();
        comboBoxProperty.getItems().clear();
        comboBoxProperty.setCellFactory(null);
        comboBoxProperty.setButtonCell(null);
        switch (property) {
            case "Classes":
                comboBoxProperty.setCellFactory(_ -> (ListCell<Object>) (ListCell<?>) classeCellule());
                comboBoxProperty.setButtonCell((ListCell<Object>) (ListCell<?>) classeCellule());
                comboBoxProperty.getItems().setAll(Classe.classes); 
                labelProperty.setText("Choisir la Classe à modifier");
                break;
            case "Compétences":
                comboBoxProperty.setCellFactory(_ -> (ListCell<Object>) (ListCell<?>) skillCellule());
                comboBoxProperty.setButtonCell((ListCell<Object>) (ListCell<?>) skillCellule());
                comboBoxProperty.getItems().setAll(Competence.liste_competence);
                labelProperty.setText("Choisir la compétence à modifier");
                break;
            case "Equipements":
                comboBoxProperty.setCellFactory(_ -> (ListCell<Object>) (ListCell<?>) equipementCellule());
                comboBoxProperty.setButtonCell((ListCell<Object>) (ListCell<?>) equipementCellule());
                comboBoxProperty.getItems().setAll(Equipement.liste_equipement); 
                labelProperty.setText("Choisir l'équipement à modifier");
                break;
            case "Races":
                comboBoxProperty.setCellFactory(_ -> (ListCell<Object>) (ListCell<?>) raceCellule());
                comboBoxProperty.setButtonCell((ListCell<Object>) (ListCell<?>) raceCellule());
                comboBoxProperty.getItems().setAll(Race.races);
                labelProperty.setText("Choisir la race à modifier");
                break;
            case "Statistiques":
                
                comboBoxProperty.setCellFactory(_ -> (ListCell<Object>) (ListCell<?>) statistiqueCellule());
                comboBoxProperty.setButtonCell((ListCell<Object>) (ListCell<?>) statistiqueCellule());
                comboBoxProperty.getItems().setAll(Statistique.liste_stats); 
                labelProperty.setText("Choisir la statistique à modifier");
                break;
            default:
                comboBoxProperty.getItems().setAll(); 
        }
    }

    private void setTextFieldsForProperty(Object value){
        if(value instanceof Classe) {
            // Cacher la partie descriptioon
            labelDesc.setVisible(false);
            textFieldDesc.setVisible(false);

            Classe classe = (Classe) comboBoxProperty.getSelectionModel().getSelectedItem();
            textFieldName.setText(classe.nom);
        } else if(value instanceof Competence){
            // Rendre visible la partie descriptioon
            labelDesc.setVisible(true);
            textFieldDesc.setVisible(true);

            Competence competence = (Competence) comboBoxProperty.getSelectionModel().getSelectedItem();
            textFieldName.setText(competence.nom);
            textFieldDesc.setText(competence.description);
        } else if(value instanceof Equipement) {
            // Rendre visible la partie descriptioon
            labelDesc.setVisible(true);
            textFieldDesc.setVisible(true);

            Equipement equipement = (Equipement) comboBoxProperty.getSelectionModel().getSelectedItem();
            textFieldName.setText(equipement.nom);
            textFieldDesc.setText(equipement.description);
        } else if (value instanceof Race){
            // Cacher la partie descriptioon
            labelDesc.setVisible(false);
            textFieldDesc.setVisible(false);

            Race race = (Race) comboBoxProperty.getSelectionModel().getSelectedItem();
            textFieldName.setText(race.nom);
        } else if(value instanceof Statistique){
            // Rendre visible la partie descriptioon
            labelDesc.setVisible(true);
            textFieldDesc.setVisible(true);

            Statistique statistique = (Statistique) comboBoxProperty.getSelectionModel().getSelectedItem();
            textFieldName.setText(statistique.nom);
            textFieldDesc.setText(statistique.description);
        }
    }

    private void handleValidateButton(){
        // Récupération des property
        String nom = textFieldName.getText();
        String desc = textFieldDesc.getText();

        // Validation des champs non nul avant de continuer
        if(!nom.isEmpty() && !desc.isEmpty()){
            // récupérer la sélectioon
            Object var = comboBoxProperty.getSelectionModel().getSelectedItem();
            if(var instanceof Classe){
                System.out.println("Classe mise à jour");
                Classe classe = (Classe) var;
                classe.nom = nom;
            } else if(var instanceof Competence) {
                Competence comp = (Competence) var;
                comp.nom = nom;
                comp.description = desc;
            } else if(var instanceof Equipement) {
                Equipement equipement = (Equipement) var;
                equipement.nom = nom;
                equipement.description = desc;
            } else if(var instanceof Race) {
                Race race = (Race) var;
                race.nom = nom;
            } else if(var instanceof Statistique){
                Statistique statistique = (Statistique) var;
                statistique.nom = nom;
                statistique.description = desc;
            }
        }
    }

    // Affichage personalisé pour equipementcomboBox
    private ListCell<Equipement> equipementCellule() {
        return new ListCell<Equipement>() {
            // Infobulle
            private final Tooltip tooltip = new Tooltip();

            @Override
            protected void updateItem(Equipement equipement, boolean empty){
                super.updateItem(equipement, empty);
                if(empty || equipement == null) {
                    setText(null);
                    setTooltip(null);
                } else {
                    setText(equipement.nom);
                    tooltip.setText(equipement.description);
                    setTooltip(tooltip);
                }
            }
        };
    }

    // Affichage personalisé pour skillscomboBox
    private ListCell<Competence> skillCellule() {
        return new ListCell<Competence>() {
            // Infobulle
            private final Tooltip tooltip = new Tooltip();
            
            @Override
            protected void updateItem(Competence competence, boolean empty){
                super.updateItem(competence, empty);
                if(empty || competence == null) {
                    setText(null);
                    setTooltip(null);
                } else {
                    setText(competence.nom);
                    tooltip.setText(competence.description);
                    setTooltip(tooltip);
                }
            }
        };
    }

    // Affichage personalisé pour classe
    private ListCell<Classe> classeCellule() {
        return new ListCell<Classe>() {
            
            @Override
            protected void updateItem(Classe cla, boolean empty){
                super.updateItem(cla, empty);
                if(empty || cla == null) {
                    setText(null);
                } else {
                    setText(cla.nom);
                }
            }
        };
    }

    // Affichage personalisé pour race
    private ListCell<Race> raceCellule() {
        return new ListCell<Race>() {
            
            @Override
            protected void updateItem(Race race, boolean empty){
                super.updateItem(race, empty);
                if(empty || race == null) {
                    setText(null);
                } else {
                    setText(race.nom);
                }
            }
        };
    }

    // Affichage personalisé pour Statistique
    private ListCell<Statistique> statistiqueCellule() {
        return new ListCell<Statistique>() {
            // Infobull
            private final Tooltip tooltip = new Tooltip();

            @Override
            protected void updateItem(Statistique stat, boolean empty){
                super.updateItem(stat, empty);
                if(empty || stat == null){
                    setText(null);
                    setTooltip(null);
                } else {
                    setText(stat.nom);
                    tooltip.setText(stat.description);
                    setTooltip(tooltip);
                }
            }
        };
    }
}
