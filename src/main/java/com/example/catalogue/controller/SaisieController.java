package com.example.catalogue.controller;

import com.example.catalogue.model.CatalogueModel;
import com.example.catalogue.view.SaisieView;
import javafx.scene.control.Label;

/** Reagit aux saisies texte et met a jour la barre d'etat. */
public class SaisieController {

    public SaisieController(CatalogueModel model, SaisieView view, Label barreEtat) {
        view.getChampId().textProperty().addListener((obs, ancien, nouveau) ->
            barreEtat.setText("ID saisi : " + nouveau));

        view.getChampId().setOnAction(e -> {
            String id = view.getChampId().getText().trim();
            model.ajouterAuJournal("ID batiment valide : " + id);
            barreEtat.setText("ID valide : " + id);
        });

        view.getChampMotDePasse().textProperty().addListener((obs, a, n) ->
            barreEtat.setText("PasswordField : " + n.length() + " caracteres"));

        view.getZoneDescription().textProperty().addListener((obs, a, n) ->
            barreEtat.setText("Description : " + n.split("\n", -1).length + " ligne(s)"));
    }
}
