package com.example.catalogue.view;

import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Demonstrations des menus, barres d'outils et boites de dialogue.
 * Contexte projet : menus Fichier / Devis, dialogues propres a l'application batiment.
 */
public class MenusDialoguesView extends CategoryView {

    private MenuBar menuBar;
    private ToolBar toolBar;
    private Button  btnAlertDevis;
    private Button  btnAlertErreur;
    private Button  btnSaisirId;
    private Button  btnChoisirType;
    private Button  btnOuvrirCatalogue;
    private Button  btnSauvegarderDevis;
    private Label   resultatDialogue;
    private Label   labelClicDroit;

    @Override
    protected void construireDemos(VBox conteneur) {
        menuBar            = new MenuBar();
        toolBar            = new ToolBar();
        btnAlertDevis      = new Button("Afficher devis (Alert INFO)");
        btnAlertErreur     = new Button("Erreur saisie (Alert ERROR)");
        btnSaisirId        = new Button("Saisir ID batiment");
        btnChoisirType     = new Button("Choisir type batiment");
        btnOuvrirCatalogue = new Button("Ouvrir catalogue...");
        btnSauvegarderDevis = new Button("Sauvegarder devis...");
        resultatDialogue   = new Label("Resultat : (aucun dialogue ouvert)");
        labelClicDroit     = new Label("  Clic droit sur une piece du plan 2D  ");

        // --- MenuBar ---
        Menu fichier = new Menu("Fichier");
        fichier.getItems().addAll(
            new MenuItem("Nouveau batiment"),
            new MenuItem("Ouvrir catalogue revetements..."),
            new SeparatorMenuItem(),
            new MenuItem("Sauvegarder devis..."),
            new SeparatorMenuItem(),
            new MenuItem("Quitter")
        );
        Menu menuDevis = new Menu("Devis");
        menuDevis.getItems().addAll(
            new MenuItem("Calculer devis"),
            new MenuItem("Afficher detail"),
            new SeparatorMenuItem(),
            new MenuItem("Exporter en PDF...")
        );
        Menu aide = new Menu("Aide");
        aide.getItems().add(new MenuItem("A propos"));
        menuBar.getMenus().addAll(fichier, menuDevis, aide);

        // --- ToolBar ---
        toolBar.getItems().addAll(
            new Button("Nouveau"), new Button("Ouvrir"), new Button("Enregistrer"),
            new Separator(),
            new Button("Calculer devis"),
            new Separator(),
            new Button("Plan 2D")
        );

        // --- ContextMenu ---
        labelClicDroit.setStyle("-fx-padding:10; -fx-background-color:#eaf0fb; -fx-border-color:#6c8ebf; -fx-border-radius:4;");
        ContextMenu contextMenu = new ContextMenu();
        contextMenu.getItems().addAll(
            new MenuItem("Modifier la piece"),
            new MenuItem("Choisir revetement"),
            new SeparatorMenuItem(),
            new MenuItem("Supprimer la piece")
        );
        labelClicDroit.setContextMenu(contextMenu);

        HBox row1 = new HBox(8, btnAlertDevis, btnAlertErreur);
        HBox row2 = new HBox(8, btnSaisirId, btnChoisirType);
        HBox row3 = new HBox(8, btnOuvrirCatalogue, btnSauvegarderDevis);

        conteneur.getChildren().addAll(
            bloc("MenuBar  —  Fichier / Devis / Aide", menuBar),
            bloc("ToolBar  —  Actions principales", toolBar),
            bloc("ContextMenu  —  Clic droit sur une piece (plan 2D)", labelClicDroit),
            bloc("Dialogues", row1, row2, row3, resultatDialogue)
        );
    }

    public Button  getBtnAlertDevis()        { return btnAlertDevis; }
    public Button  getBtnAlertErreur()       { return btnAlertErreur; }
    public Button  getBtnSaisirId()          { return btnSaisirId; }
    public Button  getBtnChoisirType()       { return btnChoisirType; }
    public Button  getBtnOuvrirCatalogue()   { return btnOuvrirCatalogue; }
    public Button  getBtnSauvegarderDevis()  { return btnSauvegarderDevis; }
    public Label   getResultatDialogue()     { return resultatDialogue; }
    public MenuBar getMenuBar()              { return menuBar; }
}
