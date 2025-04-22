package fr.clement_tristan_olivier.liste_personnage.controller;

import fr.clement_tristan_olivier.liste_personnage.model.*;
import fr.clement_tristan_olivier.liste_personnage.utils.ClasseCellFactory;
import fr.clement_tristan_olivier.liste_personnage.utils.CompetenceCellFactory;
import fr.clement_tristan_olivier.liste_personnage.utils.EquipementCellFactory;
import fr.clement_tristan_olivier.liste_personnage.utils.RaceCellFactory;
import fr.clement_tristan_olivier.liste_personnage.utils.StatistiqueMapEntryCellFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.io.IOException;
import java.util.AbstractMap;
import java.io.File;

import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.collections.ObservableList;

import javafx.scene.control.Alert;
import com.gluonhq.charm.glisten.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.stage.FileChooser;


public class FichePersonnageController {
    private Personnage personnage;
    public String profilePicture;
    public MainViewController mainViewController;
    private ObservableList<Equipement> equipementsListViewList;
    private ObservableList<Competence> skillsListViewList;
    private ObservableList<Map.Entry<Statistique, Integer>> statsComboBoxList;
    private ObservableList<Classe> classeComboBoxList;
    private ObservableList<Race> raceComboBoxList;

    @FXML
    private TextField nameTextField;
    @FXML
    private TextField bioTextField;
    @FXML
    private ListView<Equipement> equipementsListView;
    @FXML
    private Button addEquipementsButton;
    @FXML
    private Button removeEquipementsButton;
    @FXML
    private ListView<Competence> skillsListView;
    @FXML
    private Button addSkillsButton;
    @FXML
    private Button removeSkillsButton;
    @FXML
    public ComboBox<Map.Entry<Statistique, Integer>> statsComboBox;
    @FXML
    private Button addStatButton;
    @FXML
    private Button removeStatButton;
    @FXML
    private Spinner<Integer> statsSpinner;
    @FXML
    public ComboBox<Classe> classCombobox;
    @FXML
    private Button addClassButton;
    @FXML
    private Button removeclassButton;
    @FXML
    public ComboBox<Race> raceCombobox;
    @FXML
    private Button addRaceButton;
    @FXML
    private Button removeRaceButton;
    @FXML
    private Button ValidateButton;
    @FXML
    private Button CancelButton;
    @FXML
    private ImageView avatarImage;


    public void setModele(Personnage personnage){
        this.personnage = personnage;
        initialiserVue();
    }

    @FXML
    private void initialiserVue(){
        // Chargement du placholder pour l'avatar
        if(personnage.avatar != null && !personnage.avatar.isEmpty()){
            Image image = new Image(personnage.avatar);
            avatarImage.setImage(image);
        } else {
            Image placeholder = new Image(getClass().getResource("/fr/clement_tristan_olivier/liste_personnage/image/AVATAR_PLACEHOLDER.png").toExternalForm());
            avatarImage.setImage(placeholder);
        }
        
        // Ajouter les handler aux éléments cliquables
        avatarImage.setOnMouseClicked(_ -> addAvatar());
        addEquipementsButton.setOnAction(_ -> addEquipements());
        addSkillsButton.setOnAction(_ -> addCompetences());
        addStatButton.setOnAction(_ -> addStatistique());
        addRaceButton.setOnAction(_ -> addRace());
        addClassButton.setOnAction(_ -> addClasse());
        removeEquipementsButton.setOnAction(_ -> removeEquipement());
        removeSkillsButton.setOnAction(_ -> removeCompetence());
        removeStatButton.setOnAction(_ -> removeStatistique());
        removeclassButton.setOnAction(_ -> removeClasse());
        removeRaceButton.setOnAction(_ -> removeRace());
        ValidateButton.setOnAction(_ -> Validate());
        CancelButton.setOnAction(_ -> cancel());

        // Set des informations de la fenêtre
        nameTextField.setText(personnage.nom);
        bioTextField.setText(personnage.biographie);
        // ComboBox Equipements
        // Ajout des équipements dans la liste observable
        equipementsListViewList = FXCollections.observableArrayList(this.personnage.equipements);
        // lien entre la liste et la comboBox
        equipementsListView.setItems(equipementsListViewList);
        // utilisation de l'afichage personalisé
        equipementsListView.setCellFactory(new EquipementCellFactory());

        // ComboBox Competences
        // Ajout des compétences dans la liste observable
        skillsListViewList = FXCollections.observableArrayList(this.personnage.competences);
        // Lien entre la liste et la comboBox
        skillsListView.setItems(skillsListViewList);
        // Utilisation de l'affichage personalisé
        skillsListView.setCellFactory(new CompetenceCellFactory());

        // ComboBox class
        // Ajout des classes dans la liste observable
        classeComboBoxList = FXCollections.observableArrayList(Classe.classes);
        // Lien entre la liste et la comboBox
        classCombobox.setItems(classeComboBoxList);
        //sélectione la class du pêrsoonnage
        classCombobox.setValue(personnage.classe);
        // Utilisation de l'affichage personnalisé
        classCombobox.setCellFactory(new ClasseCellFactory());
        classCombobox.setButtonCell(new ClasseCellFactory().call(null));

        // ComboBox race
        // Ajout des éléments dans la liste observable
        raceComboBoxList = FXCollections.observableArrayList(Race.races);
        // Lien entre la liste et la comboBox
        raceCombobox.setItems(raceComboBoxList);
        //sélectionne la race du personnage
        raceCombobox.setValue(personnage.race);
        // Utilisation de l'affichage personnalisé
        raceCombobox.setCellFactory(new RaceCellFactory());
        raceCombobox.setButtonCell(new RaceCellFactory().call(null));

        // Combobox Statistiques
        // Ajout des éléments dans la liste observable
        statsComboBoxList = FXCollections.observableArrayList(this.personnage.statistiques.entrySet());
        // lien entre liste et BOmboBOx
        statsComboBox.setItems(statsComboBoxList);
        // Utilisation de l'affichage personalisé
        statsComboBox.setCellFactory(new StatistiqueMapEntryCellFactory());
        statsComboBox.setButtonCell(new StatistiqueMapEntryCellFactory().call(null));

        // Paramétrage du spinner des stats
        statsSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 99, 0));

        // Listener pour mettre à jour le spinner en fonction de la comboBox
        statsComboBox.getSelectionModel().selectedItemProperty().addListener((_, _, newStat) -> {
            if(newStat != null) {
                statsSpinner.getValueFactory().setValue(newStat.getValue());
            }
        });

        // Listener pour mettre à jour l'observableList des stats
        statsSpinner.valueProperty().addListener((_, _, newValue) -> {
            Map.Entry<Statistique, Integer> selectedEntry = statsComboBox.getSelectionModel().getSelectedItem();
            if(selectedEntry != null) {
                for (int i = 0; i < statsComboBoxList.size(); i++) {
                if (statsComboBoxList.get(i).getKey().equals(selectedEntry.getKey())) {  
                    // Mettre à jour la valeur de la statistique correspondante
                    AbstractMap.SimpleEntry<Statistique, Integer> newEntry = new AbstractMap.SimpleEntry<>(selectedEntry.getKey(), newValue);
                    statsComboBoxList.set(i, newEntry);

                    // Restaurer la sélection de la combo Box
                    statsComboBox.getSelectionModel().select(newEntry);
                    break; // Sortir de la boucle après la modification
                }
            }
            }
        });
    }

    private void addEquipements() {
        try{
            // Chargement de la fenêtre de pop-up
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/clement_tristan_olivier/liste_personnage/view/AddPropertyDialog.fxml"));
            Stage stage = new Stage();

            // Paramétrage de la fenêtre de pop-up
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Ajouter Equipement");
            stage.setResizable(false);
            stage.setScene(new Scene(loader.load()));
            
            // Récupération du controleur
            AddPropertyDialogController controller = loader.getController();

            // algo pour récupérer seulement les equipement qui ne sont pas affecté au personnage
            ArrayList<Equipement> equipementToAdd = new ArrayList<>();
            for(Equipement equip : Equipement.liste_equipement){
                if(!equipementsListViewList.contains(equip)){
                    equipementToAdd.add(equip);
                }
            }

            // Création de la liste observable depuis la liste des éléments à ajouter
            ObservableList<Equipement> equipementsToAddObservable = FXCollections.observableArrayList(equipementToAdd);

            // Passage du modèle à la nouvelle fenêtre
            controller.equipementsFromCaller = equipementsListViewList;
            controller.setEquipement(equipementsToAddObservable);
            
            // Ouvrir la fenêtre et bloqué la fenêtre courante
            stage.showAndWait();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private void addCompetences() {
        try{
            // Chargement de la fenêtre de pop-up
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/clement_tristan_olivier/liste_personnage/view/AddPropertyDialog.fxml"));
            Stage stage = new Stage();

            // Paramétrage de la fenêtre de pop-up
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.setTitle("Ajouter Compétence");
            stage.setScene(new Scene(loader.load()));
            
            // Récupération du controleur
            AddPropertyDialogController controller = loader.getController();

            // algo pour récupérer seulement les compétences qui ne sont pas affecté au personnage
            ArrayList<Competence> skillsToAdd = new ArrayList<>();
            for(Competence comp : Competence.liste_competence){
                if(!skillsListViewList.contains(comp)){
                    skillsToAdd.add(comp);
                }
            } 
            // Création de la liste observable depuis la liste des éléments à ajouter
            ObservableList<Competence> skillsToAddObservable = FXCollections.observableArrayList(skillsToAdd);

            // Passage du modèle à la nouvelle fenêtre
            controller.skillsFromCaller = skillsListViewList;
            controller.setCompetence(skillsToAddObservable);

            // Ouvrir la fenêtre et bloqué la fenêtre courante
            stage.showAndWait();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private void addRace() {
        try{
            // Chargement de la fenêtre de pop-up
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/clement_tristan_olivier/liste_personnage/view/AddPropertyDialog.fxml"));
            Stage stage = new Stage();

            // Paramétrage de la fenêtre de pop-up
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.setTitle("Ajouter Race");
            stage.setScene(new Scene(loader.load()));

            // Récupération du controleur
            AddPropertyDialogController controller = loader.getController();

            // Passage du modèle à la nouvelle fenêtre
            controller.setRace(raceComboBoxList, this);

            // Ouvrir la fenêtre et bloqué la fenêtre courante
            stage.showAndWait();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private void addClasse() {
        try{
            // Chargement de la fenêtre de pop-up
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/clement_tristan_olivier/liste_personnage/view/AddPropertyDialog.fxml"));
            Stage stage = new Stage();

            // Paramétrage de la fenêtre de pop-up
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.setTitle("Ajouter Classe");
            stage.setScene(new Scene(loader.load()));

            // Récupération du controleur
            AddPropertyDialogController controller = loader.getController();

            // Passage du modèle à la nouvelle fenêtre
            controller.setClasse(classeComboBoxList, this);

            // Ouvrir la fenêtre et bloqué la fenêtre courante
            stage.showAndWait();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private void addStatistique() {
        try{
            // Chargement de la fenêtre de pop-up
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/clement_tristan_olivier/liste_personnage/view/AddPropertyDialog.fxml"));
            Stage stage = new Stage();

            // Paramétrage de la fenêtre de pop-up
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.setTitle("Ajouter Statistique");
            stage.setScene(new Scene(loader.load()));
            
            // Récupération du controleur
            AddPropertyDialogController controller = loader.getController();

            // algo pour récupérer seulement les statistiques qui ne sont pas affecté au personnage
            ArrayList<Statistique> statsToAdd = new ArrayList<>();
            for(Statistique stat : Statistique.liste_stats){
                if(!statsComboBoxList.stream().anyMatch(entry -> entry.getKey().equals(stat))){
                    statsToAdd.add(stat);
                }
            }

            // Création de la liste observable depuis la liste des éléments à ajouter
            ObservableList<Statistique> statsToAddObservable = FXCollections.observableArrayList(statsToAdd);

            // Passage du modèle à la nouvelle fenêtre
            controller.statsFromCaller = statsComboBoxList;
            controller.setStat(statsToAddObservable, this);

            // Ouvrir la fenêtre et bloqué la fenêtre courante
            stage.showAndWait();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private void removeEquipement(){
        Equipement equip = equipementsListView.getSelectionModel().getSelectedItem();
        equipementsListViewList.remove(equip);
    }

    private void removeCompetence(){
        Competence comp = skillsListView.getSelectionModel().getSelectedItem();
        skillsListViewList.remove(comp);
    }

    private void removeStatistique(){
        Map.Entry<Statistique, Integer> stat = statsComboBox.getSelectionModel().getSelectedItem();
        statsComboBoxList.remove(statsComboBoxList.indexOf(stat));
    }

    private void removeClasse() {
        Classe cla = classCombobox.getSelectionModel().getSelectedItem();
        classeComboBoxList.remove(classeComboBoxList.indexOf(cla));
    }

    private void removeRace() {
        Race ra = raceCombobox.getSelectionModel().getSelectedItem();
        raceComboBoxList.remove(raceComboBoxList.indexOf(ra));
    }

    private void addAvatar(){
        // Paramétrage du fileChooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");

        // Filtrer pour afficher uniquement les fichiers image
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );

        // Ouvrir la boîte de dialogue
        File file = fileChooser.showOpenDialog(new Stage());

        if (file != null) {
            // Chargement de l'image dans l'interface
            Image image = new Image(file.toURI().toString());
            avatarImage.setImage(image);

            // Mettre à jour la variable de l'avatar
            profilePicture = file.toURI().toString();
        }
    }

    private void Validate(){
        // Si c'est un ajout de personnage le rajouter au compte
        if(!nameTextField.getText().isEmpty()){
            mainViewController.compte.ajouter_personnage(personnage);
        }
        // Vérification que le personnage à au moins un nom pour pouvoir l'enregistrer
        boolean nameVerif = !nameTextField.getText().isEmpty();
        Classe classVerif = classCombobox.getSelectionModel().getSelectedItem();
        Race raceVerif = raceCombobox.getSelectionModel().getSelectedItem();
        
        if (!nameVerif || classVerif == null || raceVerif == null) {
            dataValidationWarning();
            return;
        }
        // mise à jour du personnage
        personnage.modifier_nom(nameTextField.getText());
        personnage.modifier_biographie(bioTextField.getText());
        personnage.competences = new ArrayList<>(skillsListViewList);
        personnage.equipements = new ArrayList<>(equipementsListViewList);
        HashMap<Statistique, Integer> newHash = new HashMap<>();
        for(Map.Entry<Statistique, Integer> entry : statsComboBoxList){
            newHash.put(entry.getKey(), entry.getValue());
        }
        personnage.statistiques = newHash;
        Classe cla = classCombobox.getSelectionModel().getSelectedItem();
        personnage.classe = cla;
        Race ra = raceCombobox.getSelectionModel().getSelectedItem();
        personnage.race = ra;
        if(profilePicture != null){
            personnage.avatar = profilePicture;
        }

        // Changement de vue
        mainViewController.chargerListePersonnage();
    }

    private void cancel(){
        mainViewController.chargerListePersonnage();
    }

    private void dataValidationWarning(){
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle( "Information");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez renseigner au moins un nom, une classe et une race avant d'enregistrer le personage");

        alert.showAndWait();
    }
}
