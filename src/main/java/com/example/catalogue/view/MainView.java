package com.example.catalogue.view;

import com.example.catalogue.model.CategoryType;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;

import java.util.EnumMap;
import java.util.Map;

/**
 * Vue principale : BorderPane contenant un TabPane (un onglet par categorie)
 * et une barre d'etat en bas. La vue ne contient AUCUNE logique applicative.
 */
public class MainView {

    private final BorderPane root = new BorderPane();
    private final TabPane tabPane = new TabPane();
    private final Label barreEtat = new Label("Pret. Selectionnez un onglet pour explorer les composants.");
    private final Map<CategoryType, CategoryView> vues = new EnumMap<>(CategoryType.class);

    public MainView() {
        construire();
    }

    private void construire() {
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        for (CategoryType type : CategoryType.values()) {
            CategoryView vue = creerVue(type);
            vues.put(type, vue);
            Tab onglet = new Tab(type.getLibelle(), vue.getRoot());
            onglet.setUserData(type);
            tabPane.getTabs().add(onglet);
        }

        barreEtat.setStyle("-fx-padding: 6 10; -fx-background-color: #eef; -fx-border-color: #ccd;");
        barreEtat.setMaxWidth(Double.MAX_VALUE);

        root.setCenter(tabPane);
        root.setBottom(barreEtat);
    }

    private CategoryView creerVue(CategoryType type) {
        return switch (type) {
            case BOUTONS         -> new BoutonsView();
            case SAISIE          -> new SaisieView();
            case CHOIX           -> new ChoixView();
            case AFFICHAGE       -> new AffichageView();
            case CONTENEURS      -> new ConteneursView();
            case TABLEAUX        -> new TableauxView();
            case MENUS_DIALOGUES -> new MenusDialoguesView();
        };
    }

    public Region getRoot() { return root; }
    public TabPane getTabPane() { return tabPane; }
    public Label getBarreEtat() { return barreEtat; }
    public CategoryView getVue(CategoryType type) { return vues.get(type); }
    public Map<CategoryType, CategoryView> getVues() { return vues; }
}
