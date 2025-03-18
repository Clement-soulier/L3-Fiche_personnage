package fr.clement_tristan_olivier.liste_personnage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Hello world!
 *
 */
public class App extends Application
{
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/clement_tristan_olivier/liste_personnage/view/FichePersonnage.fxml"));
            Parent root = loader.load();

            // Créer la scnène avec le noeud racine
            Scene scene = new Scene(root);

            // Configuration et affichage de la fenêtre principale
            primaryStage.setTitle("Test app");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void main( String[] args )
    {
        System.out.println(System.getProperty("java.class.path"));
        launch(args);
    }
}
