package com.example.catalogue.view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

/** Demonstrations des conteneurs (layouts) JavaFX. */
public class ConteneursView extends CategoryView {

    @Override
    protected void construireDemos(VBox conteneur) {

        VBox vbox = new VBox(5, new Button("A"), new Button("B"), new Button("C"));

        HBox hbox = new HBox(5, new Button("1"), new Button("2"), new Button("3"));

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(new Label("Haut"));
        borderPane.setBottom(new Label("Bas"));
        borderPane.setLeft(new Label("Gauche"));
        borderPane.setRight(new Label("Droite"));
        borderPane.setCenter(new Label("Centre"));
        borderPane.setStyle("-fx-border-color: #aac; -fx-padding: 6;");

        GridPane grid = new GridPane();
        grid.setHgap(6); grid.setVgap(6);
        for (int r = 0; r < 2; r++)
            for (int c = 0; c < 3; c++)
                grid.add(new Button("(" + r + "," + c + ")"), c, r);

        StackPane stack = new StackPane(
            new Label("Fond"),
            new Label("\n\nAu-dessus")
        );
        stack.setStyle("-fx-background-color: #def; -fx-padding: 10;");
        stack.setPrefSize(200, 60);

        conteneur.getChildren().addAll(
            bloc("VBox (vertical)", vbox),
            bloc("HBox (horizontal)", hbox),
            bloc("BorderPane (5 zones)", borderPane),
            bloc("GridPane (grille)", grid),
            bloc("StackPane (empilement)", stack)
        );
    }
}
