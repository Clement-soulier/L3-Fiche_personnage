package fr.clement_tristan_olivier.liste_personnage.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.collections.ObservableList;

import java.util.AbstractMap;
import java.util.Map;

import fr.clement_tristan_olivier.liste_personnage.model.*;

public class AddPropertyDialogController {
    public ObservableList<Map.Entry<Statistique, Integer>> statistiques;
    public ObservableList<Equipement> equipements;
    public ObservableList<Equipement> equipementsFromCaller;
    public ObservableList<Competence> skills;
    public ObservableList<Competence> skillsFromCaller;
    public ObservableList<Statistique> stats;
    public ObservableList<Map.Entry<Statistique, Integer>> statsFromCaller;
    public ObservableList<Race> raceFromCaller;
    public ObservableList<Classe> classeFromCaller;
    public FichePersonnageController callerController;

    @FXML
    public ComboBox<Equipement> comboBoxEquipement;
    @FXML
    public ComboBox<Statistique> comboBoxStat;
    @FXML
    public ComboBox<Competence> comboBoxSkill;
    @FXML
    public ComboBox<Classe> comboBoxClasse;
    @FXML
    public ComboBox<Race> comboBoxRace;
    @FXML
    private CheckBox checkBox;
    @FXML
    private Label labelName;
    @FXML
    private TextField textFieldName;
    @FXML
    private Label labelDesc;
    @FXML
    private TextField textFieldDesc;
    @FXML
    private Button btnAjouter;


    @FXML
    private void initialize() {
        // Rendre les éléments visible si la checkbox est cochée
        checkBox.selectedProperty().addListener((_, _, newValue) -> {
            labelName.setVisible(newValue);
            textFieldName.setVisible(newValue);
            labelDesc.setVisible(newValue);
            textFieldDesc.setVisible(newValue);
            if(newValue){
                labelName.setText("name");
                labelDesc.setText("Description");
            }
        });


        // ajouter l'évèneent au bouton
        btnAjouter.setOnAction(_ -> addProperty());
    }

    private void addProperty() {
        // récupération des champs
        String propertyName = textFieldName.getText();
        String propertyDesc = textFieldDesc.getText();

        // si checkbox est cohé et champs vide ne rien faire
        if(checkBox.isSelected() && (propertyName.isEmpty() || propertyDesc.isEmpty())){
            return;
        }
        if(equipements != null){
            if(checkBox.isSelected()){
                Equipement newEquip = new Equipement(propertyName, propertyDesc);
                equipementsFromCaller.add(newEquip);

                ((Stage) btnAjouter.getScene().getWindow()).close();
            } else {
                Equipement selectedEquipement = comboBoxEquipement.getSelectionModel().getSelectedItem();
                if(selectedEquipement != null) {
                    equipementsFromCaller.add(selectedEquipement);
                    ((Stage) btnAjouter.getScene().getWindow()).close();
                }
            }
        }
        if(stats != null){
            if(checkBox.isSelected()){
                Statistique newStat = new Statistique(propertyName, propertyDesc);
                AbstractMap.SimpleEntry<Statistique, Integer> newEntry = new AbstractMap.SimpleEntry<>(newStat, 0);
                statsFromCaller.add(newEntry);
                
                // set la comboBox sur la statistique ajoutée
                callerController.statsComboBox.getSelectionModel().select(newEntry);

                ((Stage) btnAjouter.getScene().getWindow()).close();
            } else {
                Statistique selectedStat = comboBoxStat.getSelectionModel().getSelectedItem();
                if(selectedStat != null) {
                    AbstractMap.SimpleEntry<Statistique, Integer> newEntry = new  AbstractMap.SimpleEntry<>(selectedStat, 0);
                    statsFromCaller.add(newEntry);
                    // set la comboBox sur la statistique ajoutée
                    callerController.statsComboBox.getSelectionModel().select(newEntry);
                    ((Stage) btnAjouter.getScene().getWindow()).close();
                }
            }
        }
        if(skills != null){
            if(checkBox.isSelected()){
                Competence newskill = new Competence(propertyName, propertyDesc);
                skillsFromCaller.add(newskill);

                ((Stage) btnAjouter.getScene().getWindow()).close();
            } else {
                Competence selectedSkill = comboBoxSkill.getSelectionModel().getSelectedItem();
                if(selectedSkill != null) {
                    skillsFromCaller.add(selectedSkill);
                    ((Stage) btnAjouter.getScene().getWindow()).close();
                }
            }
        }
        if(raceFromCaller != null) {
            if(propertyName.isEmpty()){
                return;
            }
            Race newRace = new Race(propertyName);
            raceFromCaller.add(newRace);

            // Selectionne la race ajoutée dans la comboBox
            callerController.raceCombobox.getSelectionModel().select(newRace);

            ((Stage) btnAjouter.getScene().getWindow()).close();
        }
        if(classeFromCaller != null) {
            if(propertyName.isEmpty()){
                return;
            }
            Classe newClasse = new Classe(propertyName);
            classeFromCaller.add(newClasse);

            // Selection de la nouvelle classe dans la bomboBox
            callerController.classCombobox.getSelectionModel().select(newClasse);

            ((Stage) btnAjouter.getScene().getWindow()).close();
        }
    }

    public void setEquipement(ObservableList<Equipement> model){
        checkBox.setVisible(true);
        checkBox.setManaged(true);
        equipements = model;
        if(equipements != null){
            comboBoxEquipement.setVisible(true);
            comboBoxEquipement.setManaged(true);
        }
        // Set affichage de la combobox
        comboBoxEquipement.setCellFactory(_ -> equipementCellule());
        comboBoxEquipement.setButtonCell(equipementCellule());

        // mettre les éléments dans la combobox
        comboBoxEquipement.setItems(equipements);
    }

    public void setCompetence(ObservableList<Competence> model){
        checkBox.setVisible(true);
        checkBox.setManaged(true);
        skills = model;
        if(skills != null){
            comboBoxSkill.setVisible(true);
            comboBoxSkill.setManaged(true);
        }
        // Set affichage de la combobox
        comboBoxSkill.setCellFactory(new CompetenceCellFactory());
        comboBoxSkill.setButtonCell(new CompetenceCellFactory().call(null));

        // mettre les éléments dans la combobox
        comboBoxSkill.setItems(skills);
    }

    public void setStat(ObservableList<Statistique> model, FichePersonnageController controller){
        checkBox.setVisible(true);
        checkBox.setManaged(true);
        stats = model;
        callerController = controller;
        if(stats != null){
            comboBoxStat.setVisible(true);
            comboBoxStat.setManaged(true);
        }
        // Set affichage de la combobox
        comboBoxStat.setCellFactory(new StatistiqueCellFactory());
        comboBoxStat.setButtonCell(new StatistiqueCellFactory().call(null));

        // mettre les éléments dans la combobox
        comboBoxStat.setItems(stats);
    }

    public void setRace(ObservableList<Race> model, FichePersonnageController controller){
        labelName.setVisible(true);
        labelName.setText("Nom race");
        textFieldName.setVisible(true);
        raceFromCaller = model;
        callerController = controller;
    }

    public void setClasse(ObservableList<Classe> model, FichePersonnageController controller) {
        labelName.setVisible(true);
        labelName.setText("Nom classe");
        textFieldName.setVisible(true);
        classeFromCaller = model;
        callerController = controller;
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


    // Affichage personalisé pour statscomboBox
    private ListCell<Statistique> statCellule() {
        return new ListCell<Statistique>() {
            // Infobulle
            private final Tooltip tooltip = new Tooltip();
            
            @Override
            protected void updateItem(Statistique statistique, boolean empty){
                super.updateItem(statistique, empty);
                if(empty || statistique == null) {
                    setText(null);
                    setTooltip(null);
                } else {
                    setText(statistique.nom);
                    tooltip.setText(statistique.description);
                    setTooltip(tooltip);
                }
            }
        };
    }
}
