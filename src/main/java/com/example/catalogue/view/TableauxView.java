package com.example.catalogue.view;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.DoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

/**
 * Demonstrations des composants de type liste / tableau / arbre.
 * Les donnees sont issues du domaine du projet : catalogue de revetements,
 * usages de pieces, hierarchie Batiment > Niveau > Appartement > Piece.
 */
public class TableauxView extends CategoryView {

    /**
     * Represente une ligne du catalogue de revetements
     * (id, type, support applicable, prix unitaire euros/m2).
     */
    public static class Revetement {
        private final StringProperty id      = new SimpleStringProperty();
        private final StringProperty type    = new SimpleStringProperty();
        private final StringProperty support = new SimpleStringProperty();
        private final DoubleProperty prix    = new SimpleDoubleProperty();

        public Revetement(String id, String type, String support, double prix) {
            this.id.set(id);
            this.type.set(type);
            this.support.set(support);
            this.prix.set(prix);
        }

        public String getId()           { return id.get(); }
        public String getType()         { return type.get(); }
        public String getSupport()      { return support.get(); }
        public double getPrixUnitaire() { return prix.get(); }
        public StringProperty idProperty()      { return id; }
        public StringProperty typeProperty()    { return type; }
        public StringProperty supportProperty() { return support; }
        public DoubleProperty prixProperty()    { return prix; }
    }

    private ListView<String>      listView;
    private TableView<Revetement> tableView;
    private TreeView<String>      treeView;

    @Override
    @SuppressWarnings("unchecked")
    protected void construireDemos(VBox conteneur) {
        // ---- ListView : usages de pieces ----
        listView = new ListView<>();
        listView.getItems().addAll(
            "Chambre", "Salon", "Cuisine", "Salle de bain", "Degagement", "WC", "Buanderie"
        );
        listView.setPrefHeight(130);

        // ---- TableView : catalogue de revetements ----
        tableView = new TableView<>();

        TableColumn<Revetement, String> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colId.setPrefWidth(70);

        TableColumn<Revetement, String> colType = new TableColumn<>("Type");
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colType.setPrefWidth(160);

        TableColumn<Revetement, String> colSupport = new TableColumn<>("Support");
        colSupport.setCellValueFactory(new PropertyValueFactory<>("support"));
        colSupport.setPrefWidth(140);

        TableColumn<Revetement, Double> colPrix = new TableColumn<>("Prix (€/m²)");
        colPrix.setCellValueFactory(new PropertyValueFactory<>("prixUnitaire"));
        colPrix.setPrefWidth(100);

        ObservableList<Revetement> catalogue = FXCollections.observableArrayList(
            new Revetement("REV001", "Peinture",         "Mur / Plafond",  8.50),
            new Revetement("REV002", "Carrelage",        "Sol / Mur",     35.00),
            new Revetement("REV003", "Parquet",          "Sol",           45.00),
            new Revetement("REV004", "Papier peint",     "Mur",           12.00),
            new Revetement("REV005", "Bardage",          "Facade",        60.00),
            new Revetement("REV006", "Isolant projete",  "Facade",        50.00),
            new Revetement("REV007", "Isolant rapporte", "Facade",        55.00)
        );
        tableView.setItems(catalogue);
        tableView.getColumns().addAll(colId, colType, colSupport, colPrix);
        tableView.setPrefHeight(200);

        // ---- TreeView : Batiment > Niveau > Appartement > Piece ----
        TreeItem<String> batiment = new TreeItem<>("Batiment B001 (Immeuble)");

        TreeItem<String> niv0 = new TreeItem<>("Niveau 0 - RDC");
        TreeItem<String> a01  = new TreeItem<>("Appart A01");
        a01.getChildren().addAll(
            new TreeItem<>("Chambre"), new TreeItem<>("Cuisine"),
            new TreeItem<>("Salon"),   new TreeItem<>("Salle de bain")
        );
        TreeItem<String> a02 = new TreeItem<>("Appart A02");
        a02.getChildren().addAll(new TreeItem<>("Chambre"), new TreeItem<>("Cuisine"));
        niv0.getChildren().addAll(a01, a02);

        TreeItem<String> niv1 = new TreeItem<>("Niveau 1");
        TreeItem<String> a11  = new TreeItem<>("Appart A11");
        a11.getChildren().addAll(
            new TreeItem<>("Chambre 1"), new TreeItem<>("Chambre 2"),
            new TreeItem<>("Cuisine"),   new TreeItem<>("Salle de bain")
        );
        niv1.getChildren().add(a11);

        batiment.getChildren().addAll(niv0, niv1);
        batiment.setExpanded(true);
        niv0.setExpanded(true);

        treeView = new TreeView<>(batiment);
        treeView.setPrefHeight(200);

        conteneur.getChildren().addAll(
            bloc("ListView  —  Usages de pieces", listView),
            bloc("TableView  —  Catalogue de revetements", tableView),
            bloc("TreeView  —  Hierarchie Batiment > Niveau > Appartement > Piece", treeView)
        );
    }

    public ListView<String>      getListView()  { return listView; }
    public TableView<Revetement> getTableView() { return tableView; }
    public TreeView<String>      getTreeView()  { return treeView; }
}
