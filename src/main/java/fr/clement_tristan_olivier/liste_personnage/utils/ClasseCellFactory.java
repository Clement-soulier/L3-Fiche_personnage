package fr.clement_tristan_olivier.liste_personnage.utils;

import fr.clement_tristan_olivier.liste_personnage.model.Classe;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class ClasseCellFactory implements Callback<ListView<Classe>, ListCell<Classe>> {
    @Override
    public ListCell<Classe> call(ListView<Classe> param) {
        return new ListCell<Classe>() {
            @Override
            protected void updateItem(Classe classe, boolean empty){
                super.updateItem(classe, empty);
                if(empty || classe == null) {
                    setText(null);
                } else {
                    setText(classe.nom);
                }
            }
        };
    }
}
