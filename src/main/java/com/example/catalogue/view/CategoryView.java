package com.example.catalogue.view;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * Classe de base pour les vues d'une categorie. Definit la mise en page
 * commune : a gauche les demonstrations, a droite la zone d'information
 * (description et exemple de code du composant survole / clique).
 *
 * Chaque sous-classe (BoutonsView, SaisieView, ...) doit construire ses
 * demonstrations dans la methode {@link #construireDemos(VBox)}.
 */
public abstract class CategoryView {

    private final SplitPane root = new SplitPane();
    private final VBox demos = new VBox(15);
    private final Label titreInfo = new Label("Documentation");
    private final TextArea zoneInfo = new TextArea();

    protected CategoryView() {
        construireSqueletteCommun();
        construireDemos(demos);
    }

    private void construireSqueletteCommun() {
        demos.setPadding(new Insets(15));

        ScrollPane scroll = new ScrollPane(demos);
        scroll.setFitToWidth(true);

        titreInfo.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");
        zoneInfo.setEditable(false);
        zoneInfo.setWrapText(true);
        zoneInfo.setText("Cliquez sur un composant pour voir sa description et un exemple de code.");

        VBox panneauInfo = new VBox(10, titreInfo, zoneInfo);
        panneauInfo.setPadding(new Insets(15));
        VBox.setVgrow(zoneInfo, javafx.scene.layout.Priority.ALWAYS);

        root.getItems().addAll(scroll, panneauInfo);
        root.setDividerPositions(0.6);
    }

    protected abstract void construireDemos(VBox conteneur);

    public Region getRoot() { return root; }
    public TextArea getZoneInfo() { return zoneInfo; }
    public Label getTitreInfo() { return titreInfo; }

    /** Aide : enveloppe un composant de demo dans un bloc avec son nom. */
    protected Node bloc(String titre, Node... composants) {
        Label l = new Label(titre);
        l.setStyle("-fx-font-weight: bold; -fx-text-fill: #335;");
        VBox v = new VBox(6, l);
        for (Node n : composants) v.getChildren().add(n);
        v.setStyle("-fx-padding: 8; -fx-background-color: #f7f7fa; -fx-border-color: #ccd; -fx-border-radius: 4;");
        return v;
    }
}
