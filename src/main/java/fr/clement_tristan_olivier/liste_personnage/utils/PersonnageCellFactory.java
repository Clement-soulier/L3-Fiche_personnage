package fr.clement_tristan_olivier.liste_personnage.utils;

import fr.clement_tristan_olivier.liste_personnage.model.Personnage;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class PersonnageCellFactory implements Callback<ListView<Personnage>, ListCell<Personnage>> {
    @Override
    public ListCell<Personnage> call(ListView<Personnage> param) {
        return new ListCell<Personnage>() {
            @Override
            protected void updateItem(Personnage item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.nom);
                }
            }
        };
    }
}
