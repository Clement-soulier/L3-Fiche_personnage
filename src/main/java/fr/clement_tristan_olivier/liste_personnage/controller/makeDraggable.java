package fr.clement_tristan_olivier.liste_personnage.controller;

import javafx.scene.Node;

public class makeDraggable {
    
    public static void makeDraggable(Node node) {
        final Delta dragDelta = new Delta();

        node.setOnMousePressed(event -> {
            dragDelta.x = event.getSceneX() - node.getLayoutX();
            dragDelta.y = event.getSceneY() - node.getLayoutY();
        });

        node.setOnMouseDragged(event -> {
            node.setLayoutX(event.getSceneX() - dragDelta.x);
            node.setLayoutY(event.getSceneY() - dragDelta.y);
        });
    }

    private static class Delta {
        double x, y;
    }
    
}
