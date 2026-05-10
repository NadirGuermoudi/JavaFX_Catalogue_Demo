package com.example.catalogue.view;

import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.time.LocalDate;

/**
 * Demonstrations des composants de choix / selection.
 * Contexte projet : type de batiment, revetements, hauteur sous plafond, nb niveaux.
 */
public class ChoixView extends CategoryView {

    private ChoiceBox<String>  choiceBoxType;
    private ComboBox<String>   comboBoxRevetement;
    private DatePicker         dateDevis;
    private ColorPicker        colorPicker;
    private Slider             sliderHauteur;
    private Spinner<Integer>   spinnerNiveaux;

    @Override
    protected void construireDemos(VBox conteneur) {
        // Type de batiment
        choiceBoxType = new ChoiceBox<>();
        choiceBoxType.getItems().addAll("Maison individuelle", "Immeuble");
        choiceBoxType.getSelectionModel().selectFirst();

        // Catalogue de revetements
        comboBoxRevetement = new ComboBox<>();
        comboBoxRevetement.getItems().addAll(
            "Peinture", "Carrelage", "Parquet",
            "Papier peint", "Bardage", "Isolant projete", "Isolant rapporte"
        );
        comboBoxRevetement.setEditable(true);
        comboBoxRevetement.getSelectionModel().selectFirst();
        comboBoxRevetement.setPrefWidth(200);

        // Date du devis
        dateDevis = new DatePicker(LocalDate.now());

        // Couleur de peinture
        colorPicker = new ColorPicker(Color.WHITE);

        // Hauteur sous plafond (2.0 - 4.0 m)
        sliderHauteur = new Slider(2.0, 4.0, 2.5);
        sliderHauteur.setShowTickLabels(true);
        sliderHauteur.setShowTickMarks(true);
        sliderHauteur.setMajorTickUnit(0.5);
        sliderHauteur.setBlockIncrement(0.1);

        // Nombre de niveaux
        spinnerNiveaux = new Spinner<>(1, 20, 1);
        spinnerNiveaux.setEditable(true);
        spinnerNiveaux.setPrefWidth(80);

        conteneur.getChildren().addAll(
            bloc("ChoiceBox  —  Type de batiment",          new HBox(10, choiceBoxType)),
            bloc("ComboBox  —  Type de revetement",         new HBox(10, comboBoxRevetement)),
            bloc("DatePicker  —  Date du devis",            new HBox(10, dateDevis)),
            bloc("ColorPicker  —  Couleur de peinture",     new HBox(10, colorPicker)),
            bloc("Slider  —  Hauteur sous plafond (m)",     sliderHauteur),
            bloc("Spinner  —  Nombre de niveaux",           new HBox(10, spinnerNiveaux))
        );
    }

    public ChoiceBox<String>  getChoiceBoxType()       { return choiceBoxType; }
    public ComboBox<String>   getComboBoxRevetement()  { return comboBoxRevetement; }
    public DatePicker         getDateDevis()           { return dateDevis; }
    public ColorPicker        getColorPicker()         { return colorPicker; }
    public Slider             getSliderHauteur()       { return sliderHauteur; }
    public Spinner<Integer>   getSpinnerNiveaux()      { return spinnerNiveaux; }
}
