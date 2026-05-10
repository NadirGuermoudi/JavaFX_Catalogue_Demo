package com.example.catalogue.controller;

import com.example.catalogue.model.CatalogueModel;
import com.example.catalogue.view.ChoixView;
import javafx.scene.control.Label;

/** Reagit aux selections / changements de valeur des composants de choix. */
public class ChoixController {

    public ChoixController(CatalogueModel model, ChoixView view, Label barreEtat) {
        view.getChoiceBoxType().getSelectionModel().selectedItemProperty().addListener((obs, a, n) ->
            barreEtat.setText("Type batiment : " + n));

        view.getComboBoxRevetement().valueProperty().addListener((obs, a, n) ->
            barreEtat.setText("Revetement choisi : " + n));

        view.getDateDevis().valueProperty().addListener((obs, a, n) ->
            barreEtat.setText("Date du devis : " + n));

        view.getColorPicker().setOnAction(e ->
            barreEtat.setText("Couleur peinture : " + view.getColorPicker().getValue()));

        view.getSliderHauteur().valueProperty().addListener((obs, a, n) ->
            barreEtat.setText("Hauteur sous plafond : " + String.format("%.1f", n.doubleValue()) + " m"));

        view.getSpinnerNiveaux().valueProperty().addListener((obs, a, n) ->
            barreEtat.setText("Nombre de niveaux : " + n));
    }
}
