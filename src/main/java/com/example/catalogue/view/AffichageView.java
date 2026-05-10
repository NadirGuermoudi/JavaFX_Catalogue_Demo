package com.example.catalogue.view;

import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Demonstrations des composants d'affichage.
 * Le Canvas dessine un exemple de plan 2D simplifie d'un niveau de batiment,
 * illustrant l'Etape 2 du projet (visualisation du plan par niveaux).
 */
public class AffichageView extends CategoryView {

    private Label            labelDevis;
    private ProgressBar      progressBar;
    private ProgressIndicator progressIndicator;
    private Canvas           canvas;

    @Override
    protected void construireDemos(VBox conteneur) {
        labelDevis        = new Label("Devis total : 48 750 €");
        progressBar       = new ProgressBar(0.0);
        progressIndicator = new ProgressIndicator();
        canvas            = new Canvas(480, 300);

        // Label : resultat du devis
        labelDevis.setStyle("-fx-font-size:18; -fx-font-weight:bold; -fx-text-fill:#1a5276;");
        Tooltip.install(labelDevis, new Tooltip("Resultat du calcul de devis (Etape 2)"));

        // ProgressIndicator : chargement catalogue
        progressIndicator.setProgress(ProgressIndicator.INDETERMINATE_PROGRESS);
        progressIndicator.setPrefSize(40, 40);

        HBox progress = new HBox(20, progressBar, progressIndicator);
        progress.setAlignment(Pos.CENTER_LEFT);

        // Canvas : plan 2D simplifie d'un niveau (comme l'exemple du sujet)
        dessinerPlan2D(canvas.getGraphicsContext2D());

        conteneur.getChildren().addAll(
            bloc("Label  —  Resultat du devis", labelDevis),
            bloc("ProgressBar / ProgressIndicator  —  Calcul / chargement catalogue", progress),
            bloc("Canvas  —  Plan 2D d'un niveau (Etape 2 du projet)", canvas)
        );
    }

    private void dessinerPlan2D(GraphicsContext gc) {
        double ox = 20, oy = 10; // origine

        gc.setFill(Color.web("#f0f4f8"));
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Titre
        gc.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        gc.setFill(Color.web("#2c3e50"));
        gc.fillText("Plan 2D - Niveau 0 (RDC)  — Batiment B001", ox, oy + 14);

        gc.setFont(Font.font("Arial", 11));
        gc.setStroke(Color.web("#2c3e50"));
        gc.setLineWidth(2);

        // --- Piece 1 : Chambre (haut droite) ---
        double p1x = ox + 240, p1y = oy + 30, p1w = 200, p1h = 90;
        gc.setFill(Color.web("#d6eaf8"));
        gc.fillRect(p1x, p1y, p1w, p1h);
        gc.strokeRect(p1x, p1y, p1w, p1h);
        gc.setFill(Color.web("#1a5276"));
        gc.fillText("Chambre", p1x + 60, p1y + 48);
        gc.fillText("12.0 m²", p1x + 65, p1y + 64);

        // --- Piece 2 : Cuisine (bas droite) ---
        double p2x = ox + 240, p2y = oy + 120, p2w = 200, p2h = 100;
        gc.setFill(Color.web("#d5f5e3"));
        gc.fillRect(p2x, p2y, p2w, p2h);
        gc.strokeRect(p2x, p2y, p2w, p2h);
        gc.setFill(Color.web("#1e8449"));
        gc.fillText("Cuisine", p2x + 65, p2y + 45);
        gc.fillText("9.0 m²", p2x + 68, p2y + 61);

        // --- Piece 3 : Salon (bas gauche) ---
        double p3x = ox + 20, p3y = oy + 120, p3w = 220, p3h = 100;
        gc.setFill(Color.web("#fef9e7"));
        gc.fillRect(p3x, p3y, p3w, p3h);
        gc.strokeRect(p3x, p3y, p3w, p3h);
        gc.setFill(Color.web("#7d6608"));
        gc.fillText("Salon", p3x + 80, p3y + 45);
        gc.fillText("18.0 m²", p3x + 75, p3y + 61);

        // --- Piece 4 : Salle de bain (haut gauche) ---
        double p4x = ox + 20, p4y = oy + 30, p4w = 220, p4h = 90;
        gc.setFill(Color.web("#fadbd8"));
        gc.fillRect(p4x, p4y, p4w, p4h);
        gc.strokeRect(p4x, p4y, p4w, p4h);
        gc.setFill(Color.web("#922b21"));
        gc.fillText("Salle de bain", p4x + 60, p4y + 48);
        gc.fillText("6.5 m²", p4x + 75, p4y + 64);

        // Porte entre Salon et Chambre (interruption du mur)
        gc.setStroke(Color.web("#f0f4f8"));
        gc.setLineWidth(3);
        gc.strokeLine(p1x, p1y + 55, p1x, p1y + 75);
        gc.setStroke(Color.web("#2c3e50"));
        gc.setLineWidth(2);

        // Fenetre sur mur exterieur haut (tiret)
        gc.setStroke(Color.web("#2980b9"));
        gc.setLineWidth(3);
        gc.setLineDashes(6, 4);
        gc.strokeLine(p1x + 60, p1y, p1x + 140, p1y);
        gc.setLineDashes(0);
        gc.setLineWidth(2);
        gc.setStroke(Color.web("#2c3e50"));

        // Legende
        gc.setFont(Font.font("Arial", 9));
        gc.setFill(Color.web("#2980b9"));
        gc.fillRect(ox + 20, oy + 228, 20, 3);
        gc.setFill(Color.web("#2c3e50"));
        gc.fillText("— fenetre", ox + 44, oy + 231);
    }

    public Label             getLabelDevis()         { return labelDevis; }
    public ProgressBar       getProgressBar()        { return progressBar; }
    public ProgressIndicator getProgressIndicator()  { return progressIndicator; }
    public Canvas            getCanvas()             { return canvas; }
}
