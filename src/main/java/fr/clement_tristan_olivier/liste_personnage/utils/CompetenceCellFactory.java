package fr.clement_tristan_olivier.liste_personnage.utils;

import fr.clement_tristan_olivier.liste_personnage.model.Competence;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;
import javafx.util.Callback;

public class CompetenceCellFactory implements Callback<ListView<Competence>, ListCell<Competence>> {
    @Override
    public ListCell<Competence> call(ListView<Competence> param) {
        return new ListCell<Competence>() {
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
}
