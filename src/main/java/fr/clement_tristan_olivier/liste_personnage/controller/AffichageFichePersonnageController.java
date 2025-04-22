package fr.clement_tristan_olivier.liste_personnage.controller;


import java.util.Map;


import fr.clement_tristan_olivier.liste_personnage.model.Competence;
import fr.clement_tristan_olivier.liste_personnage.model.Equipement;
import fr.clement_tristan_olivier.liste_personnage.model.Personnage;
import fr.clement_tristan_olivier.liste_personnage.model.Statistique;
import fr.clement_tristan_olivier.liste_personnage.utils.CompetenceCellFactory;
import fr.clement_tristan_olivier.liste_personnage.utils.EquipementCellFactory;
import fr.clement_tristan_olivier.liste_personnage.utils.StatistiqueMapEntryCellFactory;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
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
    private TextArea Biographie;
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
        ListView1.setCellFactory(new EquipementCellFactory());
        ListView2.setCellFactory(new StatistiqueMapEntryCellFactory());
        ListView3.setCellFactory(new CompetenceCellFactory());


    }

    public void setModele(Personnage personnage){
        this.personnage = personnage;
    }
}
