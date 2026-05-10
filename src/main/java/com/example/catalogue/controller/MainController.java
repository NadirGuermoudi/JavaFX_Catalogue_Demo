package com.example.catalogue.controller;

import com.example.catalogue.model.CatalogueModel;
import com.example.catalogue.model.CategoryType;
import com.example.catalogue.view.*;

/**
 * Controleur principal : orchestre la creation des sous-controleurs
 * (un par onglet) et reagit au changement d'onglet pour mettre a jour
 * la barre d'etat.
 */
public class MainController {

    private final CatalogueModel model;
    private final MainView view;

    public MainController(CatalogueModel model, MainView view) {
        this.model = model;
        this.view = view;
        connecterSousControleurs();
        connecterEvenements();
    }

    private void connecterSousControleurs() {
        new BoutonsController(model, (BoutonsView) view.getVue(CategoryType.BOUTONS), view.getBarreEtat());
        new SaisieController(model, (SaisieView) view.getVue(CategoryType.SAISIE), view.getBarreEtat());
        new ChoixController(model, (ChoixView) view.getVue(CategoryType.CHOIX), view.getBarreEtat());
        new AffichageController(model, (AffichageView) view.getVue(CategoryType.AFFICHAGE), view.getBarreEtat());
        new TableauxController(model, (TableauxView) view.getVue(CategoryType.TABLEAUX), view.getBarreEtat());
        new MenusDialoguesController(model, (MenusDialoguesView) view.getVue(CategoryType.MENUS_DIALOGUES), view.getBarreEtat());
        // ConteneursView est purement demonstrative, pas de controleur dedie.

        // Pre-remplir la zone d'info de chaque onglet a partir du modele.
        for (CategoryType type : CategoryType.values()) {
            CategoryView vue = view.getVue(type);
            if (vue != null) {
                StringBuilder sb = new StringBuilder();
                model.getComposants(type).forEach(ci -> sb
                    .append("# ").append(ci.getNom()).append('\n')
                    .append(ci.getDescription()).append("\n\n")
                    .append(ci.getExempleCode()).append("\n\n---\n\n"));
                vue.getZoneInfo().setText(sb.toString());
                vue.getTitreInfo().setText("Documentation - " + type.getLibelle());
            }
        }
    }

    private void connecterEvenements() {
        view.getTabPane().getSelectionModel().selectedItemProperty().addListener((obs, ancien, nouveau) -> {
            if (nouveau != null && nouveau.getUserData() instanceof CategoryType type) {
                view.getBarreEtat().setText("Onglet courant : " + type.getLibelle());
            }
        });
    }
}
