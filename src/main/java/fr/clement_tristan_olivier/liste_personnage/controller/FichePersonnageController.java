package fr.clement_tristan_olivier.liste_personnage.controller;

import javafx.fxml.FXML;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.collections.ObservableList;

import java.util.Observable;
import java.util.HashMap;
import java.util.Map;

import com.gluonhq.charm.glisten.control.Avatar;
import com.gluonhq.charm.glisten.control.TextField;

import fr.clement_tristan_olivier.liste_personnage.model.Competence;
import fr.clement_tristan_olivier.liste_personnage.model.Equipement;
import fr.clement_tristan_olivier.liste_personnage.model.Personnage;
import fr.clement_tristan_olivier.liste_personnage.model.Statistique;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Spinner;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class FichePersonnageController {
    private Personnage personnage;

    @FXML
    private ComboBox<Equipement> equipementsComboBox;
    @FXML
    private ComboBox<Competence> skillsComboBox;
    @FXML
    private ComboBox<Map.Entry<Statistique, Integer>> statsComboBox;
    @FXML
    private ImageView equipementImage;


    public void setModele(Personnage personnage){
        this.personnage = personnage;
        initialiserVue();
    }

    @FXML
    private void initialiserVue(){

        // ComboBox Equipements
        // Ajout des équipements dans la liste observable
        ObservableList<Equipement> equipementsComboBoxList = FXCollections.observableArrayList(this.personnage.equipements);
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
        ObservableList<Competence> skillsComboBoxList = FXCollections.observableArrayList(this.personnage.competences);
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
        ObservableList<Map.Entry<Statistique, Integer>> statsComboBoxList = FXCollections.observableArrayList(this.personnage.statistiques.entrySet());
        // for (Map.entry<Statistique, Integer> statistique : this.personnage.statistiques ) {
        //     statsComboBoxList.add(statistique);
        // }
        // lien entre liste et BOmboBOx
        statsComboBox.setItems(statsComboBoxList);
        // Utilisation de l'affichage personalisé
        statsComboBox.setCellFactory(lv -> statCellule());
        statsComboBox.setButtonCell(statCellule());
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
}