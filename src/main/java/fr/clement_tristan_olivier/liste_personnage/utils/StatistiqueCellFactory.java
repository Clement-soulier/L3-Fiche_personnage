package fr.clement_tristan_olivier.liste_personnage.utils;

import fr.clement_tristan_olivier.liste_personnage.model.Statistique;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;
import javafx.util.Callback;

public class StatistiqueCellFactory implements Callback<ListView<Statistique>, ListCell<Statistique>> {
    @Override
    public ListCell<Statistique> call(ListView<Statistique> param) {
        return new ListCell<Statistique>() {
            private final Tooltip tooltip = new Tooltip();

            @Override
            protected void updateItem(Statistique statistique, boolean empty) {
                super.updateItem(statistique, empty);
                if (empty || statistique == null) {
                    setText(null);
                    setTooltip(null);
                } else {
                    setText(statistique.nom);
                    tooltip.setText(statistique.description);
                    setTooltip(tooltip);
                }
            }
        };
    }
}

