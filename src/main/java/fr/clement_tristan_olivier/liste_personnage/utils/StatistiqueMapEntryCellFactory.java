package fr.clement_tristan_olivier.liste_personnage.utils;

import java.util.Map;

import fr.clement_tristan_olivier.liste_personnage.model.Statistique;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;
import javafx.util.Callback;

public class StatistiqueMapEntryCellFactory implements Callback<ListView<Map.Entry<Statistique, Integer>>, ListCell<Map.Entry<Statistique, Integer>>> {
    @Override
    public ListCell<Map.Entry<Statistique, Integer>> call(ListView<Map.Entry<Statistique, Integer>> param) {
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