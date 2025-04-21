package fr.clement_tristan_olivier.liste_personnage.model;

import javafx.scene.control.ListCell;
import javafx.scene.control.Tooltip;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class EquipementCellFactory implements Callback<ListView<Equipement>, ListCell<Equipement>> {
    @Override
    public ListCell<Equipement> call(ListView<Equipement> param) {
        return new ListCell<Equipement>() {
            // Infobulle
            private final Tooltip tooltip = new Tooltip();
            
            @Override
            protected void updateItem(Equipement equipement, boolean empty){
                super.updateItem(equipement, empty);
                if(empty || equipement == null) {
                    setText(null);
                    setTooltip(null);
                } else {
                    setText(equipement.nom);
                    tooltip.setText(equipement.description);
                    setTooltip(tooltip);
                }
            }
        };
    }
}
