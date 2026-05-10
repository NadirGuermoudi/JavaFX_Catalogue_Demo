package com.example.catalogue.controller;

import com.example.catalogue.model.CatalogueModel;
import com.example.catalogue.view.AffichageView;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;

/** Anime la ProgressBar pour montrer un usage dynamique. */
public class AffichageController {

    public AffichageController(CatalogueModel model, AffichageView view, Label barreEtat) {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(80), e -> {
            double v = view.getProgressBar().getProgress() + 0.01;
            if (v > 1.0) v = 0.0;
            view.getProgressBar().setProgress(v);
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        view.getLabelDevis().setOnMouseClicked(e -> barreEtat.setText("Devis total : 48 750 €"));
    }
}
