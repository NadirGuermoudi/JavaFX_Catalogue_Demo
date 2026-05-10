package com.example.catalogue.controller;

import com.example.catalogue.model.CatalogueModel;
import com.example.catalogue.view.BoutonsView;
import javafx.scene.control.Label;

/** Branche les evenements des boutons aux mises a jour du modele / barre d'etat. */
public class BoutonsController {

    public BoutonsController(CatalogueModel model, BoutonsView view, Label barreEtat) {
        view.getBoutonStandard().setOnAction(e -> {
            model.ajouterAuJournal("Button clique");
            barreEtat.setText("Button : clic detecte");
        });

        view.getToggle().setOnAction(e ->
            barreEtat.setText("ToggleButton : " + (view.getToggle().isSelected() ? "active" : "inactif")));

        view.getRadioA().setOnAction(e -> barreEtat.setText("RadioButton : Option A selectionnee"));
        view.getRadioB().setOnAction(e -> barreEtat.setText("RadioButton : Option B selectionnee"));

        view.getCaseACocher().selectedProperty().addListener((obs, ancien, nouveau) ->
            barreEtat.setText("CheckBox : " + (nouveau ? "cochee" : "decochee")));

        view.getLien().setOnAction(e -> barreEtat.setText("Hyperlink : voir https://openjfx.io"));
    }
}
