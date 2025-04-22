package fr.clement_tristan_olivier.liste_personnage.controller;

import javafx.scene.control.Label;
import fr.clement_tristan_olivier.liste_personnage.model.*;
import fr.clement_tristan_olivier.liste_personnage.utils.ClasseCellFactory;
import fr.clement_tristan_olivier.liste_personnage.utils.CompetenceCellFactory;
import fr.clement_tristan_olivier.liste_personnage.utils.EquipementCellFactory;
import fr.clement_tristan_olivier.liste_personnage.utils.RaceCellFactory;
import fr.clement_tristan_olivier.liste_personnage.utils.StatistiqueCellFactory;
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
    private ComboBox<Classe> comboBoxPropertyClasse;
    @FXML
    private ComboBox<Competence> comboBoxPropertyCompetence;
    @FXML
    private ComboBox<Equipement> comboBoxPropertyEquipement;
    @FXML
    private ComboBox<Race> comboBoxPropertyRace;
    @FXML
    private ComboBox<Statistique> comboBoxPropertyStatistique;
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
        comboBoxPropertyClasse.getSelectionModel().selectedItemProperty().addListener((_, _, newValue) -> {
            setTextFieldsForProperty(newValue);
        });
        comboBoxPropertyClasse.setCellFactory(new ClasseCellFactory());
        comboBoxPropertyClasse.setButtonCell(new ClasseCellFactory().call(null));

        comboBoxPropertyCompetence.getSelectionModel().selectedItemProperty().addListener((_, _, newValue) -> {
            setTextFieldsForProperty(newValue);
        });
        comboBoxPropertyCompetence.setCellFactory(new CompetenceCellFactory());
        comboBoxPropertyCompetence.setButtonCell(new CompetenceCellFactory().call(null));

        comboBoxPropertyEquipement.getSelectionModel().selectedItemProperty().addListener((_, _, newValue) -> {
            setTextFieldsForProperty(newValue);
        });
        comboBoxPropertyEquipement.setCellFactory(new EquipementCellFactory());
        comboBoxPropertyEquipement.setButtonCell(new EquipementCellFactory().call(null));

        comboBoxPropertyRace.getSelectionModel().selectedItemProperty().addListener((_, _, newValue) -> {
            setTextFieldsForProperty(newValue);
        });
        comboBoxPropertyRace.setCellFactory(new RaceCellFactory());
        comboBoxPropertyRace.setButtonCell(new RaceCellFactory().call(null));

        comboBoxPropertyStatistique.getSelectionModel().selectedItemProperty().addListener((_, _, newValue) -> {
            setTextFieldsForProperty(newValue);
        });
        comboBoxPropertyStatistique.setCellFactory(new StatistiqueCellFactory());
        comboBoxPropertyStatistique.setButtonCell(new StatistiqueCellFactory().call(null));
        

        // Set le comportement du bouton valider
        btnValider.setOnAction(_ -> handleValidateButton());
    }

    private void propertySelectionChangedAction(String property){
        // Reset de toutes les comboBox
        comboBoxPropertyClasse.getSelectionModel().clearSelection();
        comboBoxPropertyClasse.getItems().clear();
        comboBoxPropertyCompetence.getSelectionModel().clearSelection();
        comboBoxPropertyCompetence.getItems().clear();
        comboBoxPropertyEquipement.getSelectionModel().clearSelection();
        comboBoxPropertyEquipement.getItems().clear();
        comboBoxPropertyRace.getSelectionModel().clearSelection();
        comboBoxPropertyRace.getItems().clear();
        comboBoxPropertyStatistique.getSelectionModel().clearSelection();
        comboBoxPropertyStatistique.getItems().clear();

        // Cacher les label et textfield desc
        labelDesc.setVisible(false);
        textFieldDesc.setVisible(false);

        // Cacher toutes les comboBox
        comboBoxPropertyClasse.setVisible(false);
        comboBoxPropertyClasse.setManaged(false);
        comboBoxPropertyCompetence.setVisible(false);
        comboBoxPropertyCompetence.setManaged(false);
        comboBoxPropertyEquipement.setVisible(false);
        comboBoxPropertyEquipement.setManaged(false);
        comboBoxPropertyRace.setVisible(false);
        comboBoxPropertyRace.setManaged(false);
        comboBoxPropertyStatistique.setManaged(false);

        switch (property) {
            case "Classes":
                comboBoxPropertyClasse.getItems().setAll(Classe.classes);
                comboBoxPropertyClasse.setVisible(true);
                comboBoxPropertyClasse.setManaged(true);
                labelProperty.setText("Choisir la Classe à modifier");
                break;
            case "Compétences":
                comboBoxPropertyCompetence.getItems().setAll(Competence.liste_competence);
                comboBoxPropertyCompetence.setVisible(true);
                comboBoxPropertyCompetence.setManaged(true);
                labelProperty.setText("Choisir la compétence à modifier");
                // Afficher les label et textField desc
                labelDesc.setVisible(true);
                textFieldDesc.setVisible(true);
                break;
            case "Equipements":
                comboBoxPropertyEquipement.getItems().setAll(Equipement.liste_equipement); 
                comboBoxPropertyEquipement.setVisible(true);
                comboBoxPropertyEquipement.setManaged(true);
                labelProperty.setText("Choisir l'équipement à modifier");
                // Afficher les label et textField desc
                labelDesc.setVisible(true);
                textFieldDesc.setVisible(true);
                break;
            case "Races":
                comboBoxPropertyRace.getItems().setAll(Race.races);
                comboBoxPropertyRace.setVisible(true);
                comboBoxPropertyRace.setManaged(true);
                labelProperty.setText("Choisir la race à modifier");
                break;
            case "Statistiques":
                comboBoxPropertyStatistique.getItems().setAll(Statistique.liste_stats); 
                comboBoxPropertyStatistique.setVisible(true);
                comboBoxPropertyStatistique.setManaged(true);
                labelProperty.setText("Choisir la statistique à modifier");
                // Afficher les label et textField desc
                labelDesc.setVisible(true);
                textFieldDesc.setVisible(true);
                break;
            default:
        }
    }

    private void setTextFieldsForProperty(Object value){
        if(value instanceof Classe) {
            // Cacher la partie descriptioon
            labelDesc.setVisible(false);
            textFieldDesc.setVisible(false);

            Classe classe = (Classe) comboBoxPropertyClasse.getSelectionModel().getSelectedItem();
            textFieldName.setText(classe.nom);
        } else if(value instanceof Competence){
            // Rendre visible la partie descriptioon
            labelDesc.setVisible(true);
            textFieldDesc.setVisible(true);

            Competence competence = (Competence) comboBoxPropertyCompetence.getSelectionModel().getSelectedItem();
            textFieldName.setText(competence.nom);
            textFieldDesc.setText(competence.description);
        } else if(value instanceof Equipement) {
            // Rendre visible la partie descriptioon
            labelDesc.setVisible(true);
            textFieldDesc.setVisible(true);

            Equipement equipement = (Equipement) comboBoxPropertyEquipement.getSelectionModel().getSelectedItem();
            textFieldName.setText(equipement.nom);
            textFieldDesc.setText(equipement.description);
        } else if (value instanceof Race){
            // Cacher la partie descriptioon
            labelDesc.setVisible(false);
            textFieldDesc.setVisible(false);

            Race race = (Race) comboBoxPropertyRace.getSelectionModel().getSelectedItem();
            textFieldName.setText(race.nom);
        } else if(value instanceof Statistique){
            // Rendre visible la partie descriptioon
            labelDesc.setVisible(true);
            textFieldDesc.setVisible(true);

            Statistique statistique = (Statistique) comboBoxPropertyStatistique.getSelectionModel().getSelectedItem();
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
            Classe classe = comboBoxPropertyClasse.getSelectionModel().getSelectedItem();
            Competence comp = comboBoxPropertyCompetence.getSelectionModel().getSelectedItem();
            Equipement equipement = comboBoxPropertyEquipement.getSelectionModel().getSelectedItem();
            Race race = comboBoxPropertyRace.getSelectionModel().getSelectedItem();
            Statistique statistique = comboBoxPropertyStatistique.getSelectionModel().getSelectedItem();
            if(classe !=null){
                classe.nom = nom;
            } else if(comp != null) {
                comp.nom = nom;
                comp.description = desc;
            } else if(equipement != null) {
                equipement.nom = nom;
                equipement.description = desc;
            } else if(race != null) {
                race.nom = nom;
            } else if(statistique != null){
                statistique.nom = nom;
                statistique.description = desc;
            }
        }
    }
}
