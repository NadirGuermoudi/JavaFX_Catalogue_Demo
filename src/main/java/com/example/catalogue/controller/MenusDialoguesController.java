package com.example.catalogue.controller;

import com.example.catalogue.model.CatalogueModel;
import com.example.catalogue.view.MenusDialoguesView;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.List;
import java.util.Optional;

/** Affiche les dialogues en contexte "Devis estimatif d'un batiment". */
public class MenusDialoguesController {

    public MenusDialoguesController(CatalogueModel model, MenusDialoguesView view, Label barreEtat) {

        // Alert INFO : resultat du devis
        view.getBtnAlertDevis().setOnAction(e -> {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Devis estimatif");
            a.setHeaderText("Batiment B001 — Immeuble 3 niveaux, 6 appartements");
            a.setContentText(
                "Surface totale revetements : 1 350 m²\n" +
                "  - Peinture (murs)   :   8 640 €\n" +
                "  - Carrelage (sols)  :  15 750 €\n" +
                "  - Parquet (chambres):  12 600 €\n" +
                "  - Bardage (facade)  :  11 760 €\n" +
                "──────────────────────────────\n" +
                "Devis total          :  48 750 €"
            );
            a.showAndWait();
            view.getResultatDialogue().setText("Resultat : devis calcule — 48 750 €");
        });

        // Alert ERROR : erreur de saisie
        view.getBtnAlertErreur().setOnAction(e -> {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Erreur de saisie");
            a.setHeaderText("Hauteur sous plafond invalide");
            a.setContentText("La valeur saisie (0.5 m) est hors de la plage autorisee [2.0 - 4.0 m].");
            a.showAndWait();
            view.getResultatDialogue().setText("Resultat : erreur signalee.");
        });

        // TextInputDialog : saisir l'identifiant du batiment
        view.getBtnSaisirId().setOnAction(e -> {
            TextInputDialog d = new TextInputDialog("B001");
            d.setTitle("Nouveau batiment");
            d.setHeaderText("Identifiant du batiment :");
            d.setContentText("ID :");
            Optional<String> rep = d.showAndWait();
            view.getResultatDialogue().setText("ID saisi : " + rep.orElse("(annule)"));
        });

        // ChoiceDialog : choisir le type de batiment
        view.getBtnChoisirType().setOnAction(e -> {
            ChoiceDialog<String> d = new ChoiceDialog<>(
                "Immeuble", List.of("Maison individuelle", "Immeuble")
            );
            d.setTitle("Type de batiment");
            d.setHeaderText("Choisissez le type de batiment :");
            Optional<String> rep = d.showAndWait();
            view.getResultatDialogue().setText("Type choisi : " + rep.orElse("(annule)"));
        });

        // FileChooser : ouvrir le catalogue de revetements
        view.getBtnOuvrirCatalogue().setOnAction(e -> {
            FileChooser fc = new FileChooser();
            fc.setTitle("Ouvrir catalogue de revetements");
            fc.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Fichiers texte (*.txt)", "*.txt")
            );
            File f = fc.showOpenDialog(view.getBtnOuvrirCatalogue().getScene().getWindow());
            view.getResultatDialogue().setText("Catalogue : " + (f != null ? f.getName() : "(annule)"));
        });

        // FileChooser : sauvegarder le devis
        view.getBtnSauvegarderDevis().setOnAction(e -> {
            FileChooser fc = new FileChooser();
            fc.setTitle("Sauvegarder le devis");
            fc.setInitialFileName("devis_B001.txt");
            fc.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Fichiers texte (*.txt)", "*.txt")
            );
            File f = fc.showSaveDialog(view.getBtnSauvegarderDevis().getScene().getWindow());
            view.getResultatDialogue().setText("Sauvegarde : " + (f != null ? f.getName() : "(annule)"));
        });
    }
}
