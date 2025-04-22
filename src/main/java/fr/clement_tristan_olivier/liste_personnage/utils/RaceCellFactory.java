package fr.clement_tristan_olivier.liste_personnage.utils;

import fr.clement_tristan_olivier.liste_personnage.model.Race;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class RaceCellFactory implements Callback<ListView<Race>, ListCell<Race>> {
    @Override
    public ListCell<Race> call(ListView<Race> param) {
        return new ListCell<Race>() {
            @Override
            protected void updateItem(Race race, boolean empty){
                super.updateItem(race, empty);
                if(empty || race == null) {
                    setText(null);
                } else {
                    setText(race.nom);
                }
            }
        };
    }
}
