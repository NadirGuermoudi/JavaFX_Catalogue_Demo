package com.example.catalogue;

import com.example.catalogue.controller.MainController;
import com.example.catalogue.model.CatalogueModel;
import com.example.catalogue.view.MainView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Point d'entree de l'application JavaFX.
 * Architecture MVC :
 *  - Model      : donnees (CatalogueModel)
 *  - View       : interface graphique (MainView et vues par categorie)
 *  - Controller : logique applicative (MainController et controleurs par categorie)
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        CatalogueModel model = new CatalogueModel();
        MainView view = new MainView();
        new MainController(model, view);

        Scene scene = new Scene(view.getRoot(), 1000, 700);
        primaryStage.setTitle("Catalogue JavaFX - Demo MVC");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
