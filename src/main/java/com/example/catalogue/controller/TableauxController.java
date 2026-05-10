package com.example.catalogue.controller;

import com.example.catalogue.model.CatalogueModel;
import com.example.catalogue.view.TableauxView;
import javafx.scene.control.Label;

/** Reagit aux selections dans la ListView (usages), TableView (revetements), TreeView (hierarchie). */
public class TableauxController {

    public TableauxController(CatalogueModel model, TableauxView view, Label barreEtat) {
        view.getListView().getSelectionModel().selectedItemProperty().addListener((obs, a, n) -> {
            if (n != null) barreEtat.setText("Usage selectionne : " + n);
        });

        view.getTableView().getSelectionModel().selectedItemProperty().addListener((obs, a, n) -> {
            if (n != null) barreEtat.setText("Revetement : " + n.getType()
                    + " — " + n.getSupport()
                    + " — " + n.getPrixUnitaire() + " €/m²");
        });

        view.getTreeView().getSelectionModel().selectedItemProperty().addListener((obs, a, n) -> {
            if (n != null) barreEtat.setText("Selection arbre : " + n.getValue());
        });
    }
}
