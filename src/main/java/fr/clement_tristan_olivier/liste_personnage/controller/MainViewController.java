package fr.clement_tristan_olivier.liste_personnage.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import fr.clement_tristan_olivier.liste_personnage.model.Base_de_donnees;
import fr.clement_tristan_olivier.liste_personnage.model.Competence;
import fr.clement_tristan_olivier.liste_personnage.model.Compte;
import fr.clement_tristan_olivier.liste_personnage.model.Equipement;
import fr.clement_tristan_olivier.liste_personnage.model.Personnage;
import fr.clement_tristan_olivier.liste_personnage.model.Statistique;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MainViewController {
    private Base_de_donnees base_de_donnees;
    private Compte compte;

    @FXML
    private VBox rootPane;

    @FXML
    private AnchorPane conteneurCentre;

    @FXML
    private void initialize() {
        chargerFichePersonnage();
    }

    private void chargerFichePersonnage(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/clement_tristan_olivier/liste_personnage/view/FichePersonnage.fxml"));
            Parent vue = loader.load();

            FichePersonnageController controller = loader.getController();
            
            HashMap<Statistique, Integer> statistiques = new HashMap<>();
            Statistique stat1 = new Statistique("attaque", "capacité à faire mal");
            Statistique stat2 = new Statistique("défense", "capacité à encaisser les coups");
            statistiques.put(stat1, 20);
            statistiques.put(stat2, 40);

            ArrayList<Competence> competences = new ArrayList<>();
            Competence comp1 =new Competence("quoi", "feur");
            Competence comp2 =new Competence("brulure", "ça brule");
            competences.add(comp1);
            competences.add(comp2);

            ArrayList<Equipement> equipements = new ArrayList<>();
            Equipement equip1 =new Equipement("épée", "Un épée qui coupe");
            Equipement equip2 =new Equipement("Bouclier", "Un bouclier qui protège de tout");
            equipements.add(equip1);
            equipements.add(equip2);

            Personnage personnage = new Personnage("Robert", "Un personnage de test très badass", statistiques, competences, equipements, "Barbare", "Orc");

            controller.setModele(personnage);
            conteneurCentre.getChildren().setAll(vue);
            

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void chargerLoginPage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/clement_tristan_olivier/liste_personnage/view/LoginPage.fxml"));
            Parent vue = loader.load();

            LoginPageController controller = loader.getController();
            controller.setModel(base_de_donnees);
            controller.mainViewController=this;

            rootPane.getChildren().setAll(vue);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

public void chargerCreateUserPage() {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/clement_tristan_olivier/liste_personnage/view/CreateUserPage.fxml"));
        Parent vue = loader.load();
        base_de_donnees = new Base_de_donnees();
        CreateUserPageController controller = loader.getController();
        controller.setModel(base_de_donnees);
        controller.mainViewController=this;

        rootPane.getChildren().setAll(vue);

    } catch (IOException e) {
        e.printStackTrace();
    }
}
    // private void chargerVueSecondaire(String cheminFXML) {
    //     try {
    //         FXMLLoader loader = new FXMLLoader(getClass().getResource(cheminFXML));
    //         AnchorPane vue = loader.load();
    //         conteneurCentre.getChildren().setAll(vue);

    //         // Récupérer le contrôleur de la vue secondaire si nécessaire
    //         // ControleurSecondaire controleur = loader.getController();
    //         // controleur.setControleurPrincipal(this);

    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    // }

    public void chargerListePersonnage(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/clement_tristan_olivier/liste_personnage/view/ListePersonnage.fxml"));
            Parent vue = loader.load();

            ListePersonnageController controller = loader.getController();
            controller.setModele(compte);
            controller.mainViewController=this;

            conteneurCentre.getChildren().setAll(vue);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}