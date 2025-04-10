package fr.clement_tristan_olivier.liste_personnage.controller;


import java.util.Map;

import fr.clement_tristan_olivier.liste_personnage.model.Competence;
import fr.clement_tristan_olivier.liste_personnage.model.Equipement;
import fr.clement_tristan_olivier.liste_personnage.model.Personnage;
import fr.clement_tristan_olivier.liste_personnage.model.Statistique;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ListCell;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;

public class AffichageFichePersonnageController {
    private Personnage personnage;

    @FXML
    private ImageView Avatar;
    @FXML
    private Label Name;
    @FXML
    private Label Race;
    @FXML
    private Label Class;
    @FXML
    private Label Biographie;
    @FXML
    private ListView<Equipement> ListView1;
    @FXML
    private ListView<Map.Entry<Statistique, Integer>> ListView2;
    @FXML
    private ListView<Competence> ListView3;

    @FXML
    public void initialiserVue(){
        //chargement de l'avatar ou du placeholder
        if(personnage.avatar != null && !personnage.avatar.isEmpty()){
            Image image = new Image(personnage.avatar);
            Avatar.setImage(image);
        } else {
            Image placeholder = new Image(getClass().getResource("/fr/clement_tristan_olivier/liste_personnage/image/AVATAR_PLACEHOLDER.png").toExternalForm());
            Avatar.setImage(placeholder);
        }

        // Remplir la fiche avec les informations
        Name.setText(personnage.nom);
        Biographie.setText(personnage.biographie);
        Race.setText(personnage.race.toString());
        Class.setText(personnage.classe.toString());
        ListView1.setItems(javafx.collections.FXCollections.observableArrayList(personnage.equipements));
        ListView2.setItems(javafx.collections.FXCollections.observableArrayList(personnage.statistiques.entrySet()));
        ListView3.setItems(javafx.collections.FXCollections.observableArrayList(personnage.competences));

        // Set les affichages personalisé des listView
        ListView1.setCellFactory(_ -> equipementCellule());
        ListView2.setCellFactory(_ -> statCellule());
        ListView3.setCellFactory(_ -> skillCellule());


    }

    public void setModele(Personnage personnage){
        this.personnage = personnage;
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
                    setText(statistique.getKey().nom + ": " + statistique.getValue());
                    tooltip.setText(statistique.getKey().description);
                    setTooltip(tooltip);
                }
            }
        };
    }
}
