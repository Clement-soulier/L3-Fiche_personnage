package fr.clement_tristan_olivier.liste_personnage.controller;

import javafx.fxml.FXML;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.collections.ObservableList;

import java.util.Observable;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ArrayList;

import com.gluonhq.charm.glisten.control.Avatar;
import com.gluonhq.charm.glisten.control.TextField;

import fr.clement_tristan_olivier.liste_personnage.model.Classe;
import fr.clement_tristan_olivier.liste_personnage.model.Competence;
import fr.clement_tristan_olivier.liste_personnage.model.Equipement;
import fr.clement_tristan_olivier.liste_personnage.model.Personnage;
import fr.clement_tristan_olivier.liste_personnage.model.Statistique;
import fr.clement_tristan_olivier.liste_personnage.model.Race;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.AbstractMap;

public class FichePersonnageController {
    private Personnage personnage;
    private ObservableList<Equipement> equipementsComboBoxList;
    private ObservableList<Competence> skillsComboBoxList;
    private ObservableList<Map.Entry<Statistique, Integer>> statsComboBoxList;

    @FXML
    private TextField nameTextField;
    @FXML
    private TextField bioTextField;
    @FXML
    private ComboBox<Equipement> equipementsComboBox;
    @FXML
    private Button addEquipementsButton;
    @FXML
    private Button removeEquipementsButton;
    @FXML
    private ComboBox<Competence> skillsComboBox;
    @FXML
    private Button addSkillsButton;
    @FXML
    private Button removeSkillsButton;
    @FXML
    private ComboBox<Map.Entry<Statistique, Integer>> statsComboBox;
    @FXML
    private Button addStatButton;
    @FXML
    private Button removeStatButton;
    @FXML
    private Spinner<Integer> statsSpinner;
    @FXML
    private ComboBox<Classe> classComboBox;
    @FXML
    private Button addClassButton;
    @FXML
    private Button removeClassButton;
    @FXML
    private ComboBox<Race> raceComboBox;
    @FXML
    private Button ValidateButton;
    @FXML
    private Button CancelButton;
    @FXML
    private ImageView equipementImage;


    public void setModele(Personnage personnage){
        this.personnage = personnage;
        initialiserVue();
    }

    @FXML
    private void initialiserVue(){
        // Ajouter les handler aux boutons
        addEquipementsButton.setOnAction(event -> addEquipements());
        addSkillsButton.setOnAction(event -> addCompetences());
        addStatButton.setOnAction(event -> addStatistique());

        // ComboBox Equipements
        // Ajout des équipements dans la liste observable
        equipementsComboBoxList = FXCollections.observableArrayList(this.personnage.equipements);
        // for (Equipement equipement : this.personnage.equipements) {
        //     equipementsComboBoxList.add(equipement);
        // }
        // lien entre la liste et la comboBox
        equipementsComboBox.setItems(equipementsComboBoxList);
        // utilisation de l'afichage personalisé
        equipementsComboBox.setCellFactory(lv -> equipementCellule());
        equipementsComboBox.setButtonCell(equipementCellule());

        // ComboBox Competences
        // Ajout des compétences dans la liste observable
        skillsComboBoxList = FXCollections.observableArrayList(this.personnage.competences);
        // for (Competence competence : this.personnage.competences) {
        //     skillsComboBoxList.add(competence);
        // }
        // Lien entre la liste et la comboBox
        skillsComboBox.setItems(skillsComboBoxList);
        // Utilisation de l'affichage personalisé
        skillsComboBox.setCellFactory(lv -> skillCellule());
        skillsComboBox.setButtonCell(skillCellule());

        // Combobox Statistiques
        // Ajout des éléments dans la liste observable
        statsComboBoxList = FXCollections.observableArrayList(this.personnage.statistiques.entrySet());
        // for (Map.entry<Statistique, Integer> statistique : this.personnage.statistiques ) {
        //     statsComboBoxList.add(statistique);
        // }
        // lien entre liste et BOmboBOx
        statsComboBox.setItems(statsComboBoxList);
        // Utilisation de l'affichage personalisé
        statsComboBox.setCellFactory(lv -> statCellule());
        statsComboBox.setButtonCell(statCellule());

        // Paramétrage du spinner des stats
        statsSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 99, 1));

        // Listener pour mettre à jour le spinner en fonction de la comboBox
        statsComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldstat, newStat) -> {
            if(newStat != null) {
                statsSpinner.getValueFactory().setValue(newStat.getValue());
            }
        });

        // Listener pour mettre à jour l'observableList des stats
        statsSpinner.valueProperty().addListener((obs, oldValue, newValue) -> {
            Map.Entry<Statistique, Integer> selectedEntry = statsComboBox.getSelectionModel().getSelectedItem();
            if(selectedEntry != null) {
                for (int i = 0; i < statsComboBoxList.size(); i++) {
                if (statsComboBoxList.get(i).getKey().equals(selectedEntry.getKey())) {  
                    // Mettre à jour la valeur de la statistique correspondante
                    statsComboBoxList.set(i, new AbstractMap.SimpleEntry<>(selectedEntry.getKey(), newValue));
                    // comboBoxStats.refresh(); // Rafraîchir l'affichage
                    break; // Sortir de la boucle après la modification
                }
            }
            }
        });
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
    private ListCell<Map.Entry<Statistique, Integer>> statCellule() {
        return new ListCell<Map.Entry<Statistique, Integer>>() {
            // Infobulle
            private final Tooltip tooltip = new Tooltip();
            
            @Override
            protected void updateItem(Map.Entry<Statistique, Integer> statistique, boolean empty){
                super.updateItem(statistique, empty);
                if(empty || statistique == null) {
                    setText(null);
                    setTooltip(null);
                } else {
                    setText(statistique.getKey().nom);
                    tooltip.setText(statistique.getKey().description);
                    setTooltip(tooltip);
                }
            }
        };
    }

    private void addEquipements() {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/clement_tristan_olivier/liste_personnage/view/AddPropertyDialog.fxml"));
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Ajouter Equipement");
            stage.setScene(new Scene(loader.load()));
            
            // Passer la liste observable
            AddPropertyDialogController controller = loader.getController();
            // algo pour récupérer seulement les equipement qui ne sont pas encore là
            ArrayList<Equipement> equipementToAdd = Equipement.liste_equipement;
            Iterator<Equipement> iterator = equipementToAdd.iterator();
            while(iterator.hasNext()) {
                Equipement equip = iterator.next();
                if(equipementsComboBoxList.contains(equip)) {
                    iterator.remove();
                }
            }
            ObservableList<Equipement> equipementsToAddObservable = FXCollections.observableArrayList(equipementToAdd);
            controller.equipementsFromCaller = equipementsComboBoxList;
            controller.setEquipement(equipementsToAddObservable);
            
            stage.showAndWait();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private void addCompetences() {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/clement_tristan_olivier/liste_personnage/view/AddPropertyDialog.fxml"));
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Ajouter Compétence");
            stage.setScene(new Scene(loader.load()));
            
            // Passer la liste observable
            AddPropertyDialogController controller = loader.getController();
            // algo pour récupérer seulement les compétences qui ne sont pas encore là
            ArrayList<Competence> skillsToAdd = Competence.liste_competence;
            Iterator<Competence> iterator = skillsToAdd.iterator();
            while(iterator.hasNext()) {
                Competence comp = iterator.next();
                if(skillsComboBoxList.contains(comp)) {
                    iterator.remove();
                }
            }
            ObservableList<Competence> skillsToAddObservable = FXCollections.observableArrayList(skillsToAdd);
            controller.skillsFromCaller = skillsComboBoxList;
            controller.setCompetence(skillsToAddObservable);
            
            stage.showAndWait();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private void addStatistique() {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/clement_tristan_olivier/liste_personnage/view/AddPropertyDialog.fxml"));
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Ajouter Statistique");
            stage.setScene(new Scene(loader.load()));
            
            // Passer la liste observable
            AddPropertyDialogController controller = loader.getController();
            // algo pour récupérer seulement les compétences qui ne sont pas encore là
            ArrayList<Statistique> statsToAdd = Statistique.liste_stats;
            Iterator<Statistique> iterator = statsToAdd.iterator();
            while(iterator.hasNext()) {
                Statistique stat = iterator.next();
                boolean condition = statsComboBoxList.stream().anyMatch(entry -> entry.getKey().equals(stat));
                if(condition) {
                    iterator.remove();
                }
            }
            ObservableList<Statistique> statsToAddObservable = FXCollections.observableArrayList(statsToAdd);
            controller.statsFromCaller = statsComboBoxList;
            controller.setStat(statsToAddObservable);
            
            stage.showAndWait();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}