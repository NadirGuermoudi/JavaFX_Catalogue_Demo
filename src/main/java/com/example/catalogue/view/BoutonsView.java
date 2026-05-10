package com.example.catalogue.view;

import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/** Demonstrations des composants de type boutons. */
public class BoutonsView extends CategoryView {

    private Button boutonStandard;
    private ToggleButton toggle;
    private RadioButton radioA;
    private RadioButton radioB;
    private CheckBox caseACocher;
    private Hyperlink lien;

    @Override
    protected void construireDemos(VBox conteneur) {
        // Initialisation ici car super() appelle cette methode avant les
        // initialiseurs de champs de la sous-classe (ordre d'init Java).
        boutonStandard = new Button("Cliquer");
        toggle         = new ToggleButton("Activer");
        radioA         = new RadioButton("Option A");
        radioB         = new RadioButton("Option B");
        caseACocher    = new CheckBox("J'accepte les conditions");
        lien           = new Hyperlink("openjfx.io");

        ToggleGroup groupe = new ToggleGroup();
        radioA.setToggleGroup(groupe);
        radioB.setToggleGroup(groupe);
        radioA.setSelected(true);

        conteneur.getChildren().addAll(
            bloc("Button",       new HBox(10, boutonStandard)),
            bloc("ToggleButton", new HBox(10, toggle)),
            bloc("RadioButton",  new HBox(10, radioA, radioB)),
            bloc("CheckBox",     new HBox(10, caseACocher)),
            bloc("Hyperlink",    new HBox(10, lien))
        );
    }

    public Button getBoutonStandard() { return boutonStandard; }
    public ToggleButton getToggle()   { return toggle; }
    public RadioButton getRadioA()    { return radioA; }
    public RadioButton getRadioB()    { return radioB; }
    public CheckBox getCaseACocher()  { return caseACocher; }
    public Hyperlink getLien()        { return lien; }
}
