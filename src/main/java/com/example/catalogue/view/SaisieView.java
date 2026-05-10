package com.example.catalogue.view;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Demonstrations des composants de saisie de texte.
 * Contexte projet : saisie d'identifiants, valeurs numeriques, descriptions de batiment.
 */
public class SaisieView extends CategoryView {

    private TextField    champId;
    private PasswordField champMotDePasse;
    private TextArea     zoneDescription;

    @Override
    protected void construireDemos(VBox conteneur) {
        champId          = new TextField();
        champMotDePasse  = new PasswordField();
        zoneDescription  = new TextArea();

        champId.setPromptText("ID Batiment (ex: B001)");
        champId.setPrefWidth(220);

        champMotDePasse.setPromptText("Mot de passe (acces application)");

        zoneDescription.setPromptText(
            "Description du batiment ou notes sur le devis...\n" +
            "Ex: Immeuble 3 niveaux, 6 appartements, facade bardage."
        );
        zoneDescription.setPrefRowCount(5);
        zoneDescription.setWrapText(true);

        conteneur.getChildren().addAll(
            bloc("TextField  —  Identifiant batiment / appartement / piece", new HBox(10, champId)),
            bloc("PasswordField  —  Authentification (optionnelle)",         new HBox(10, champMotDePasse)),
            bloc("TextArea  —  Description / notes sur le devis",            zoneDescription)
        );
    }

    public TextField     getChampId()          { return champId; }
    public PasswordField getChampMotDePasse()  { return champMotDePasse; }
    public TextArea      getZoneDescription()  { return zoneDescription; }
}
