package fr.clement_tristan_olivier.liste_personnage.controller;

import javafx.fxml.FXML;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import com.gluonhq.charm.glisten.control.Avatar;
import com.gluonhq.charm.glisten.control.TextField;

import fr.clement_tristan_olivier.liste_personnage.model.Personnage;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Spinner;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class FichePersonnageController {
    Personnage personnage;
    @FXML
    private ImageView equipementImage;

    public void setModele(Personnage personnage){
        this.personnage = personnage;
    }

    private void initialiserVue(){}


}