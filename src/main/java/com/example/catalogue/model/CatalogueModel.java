package com.example.catalogue.model;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * Modele du catalogue : centralise les donnees descriptives utilisees
 * par les vues (texte d'aide, exemples de code).
 * Les descriptions sont adaptees au contexte du projet "Devis estimatif d'un batiment".
 */
public class CatalogueModel {

    private final Map<CategoryType, List<ComponentInfo>> donnees = new EnumMap<>(CategoryType.class);
    private String journal = "";

    public CatalogueModel() {
        chargerDonnees();
    }

    private void chargerDonnees() {
        donnees.put(CategoryType.BOUTONS, List.of(
            new ComponentInfo("Button",
                "Declenche une action : calculer le devis, valider un formulaire de saisie, enregistrer.",
                "Button b = new Button(\"Calculer le devis\");\nb.setOnAction(e -> calculerDevis());"),
            new ComponentInfo("ToggleButton",
                "Active/desactive une option : ex. afficher/masquer les murs de facade.",
                "ToggleButton t = new ToggleButton(\"Afficher facades\");\nt.setOnAction(e -> toggleFacades(t.isSelected()));"),
            new ComponentInfo("RadioButton",
                "Choix exclusif : type de batiment (Maison ou Immeuble).",
                "ToggleGroup g = new ToggleGroup();\nRadioButton r1 = new RadioButton(\"Maison\");\nRadioButton r2 = new RadioButton(\"Immeuble\");\nr1.setToggleGroup(g);\nr2.setToggleGroup(g);"),
            new ComponentInfo("CheckBox",
                "Option independante : inclure l'isolation facade, afficher le plafond, etc.",
                "CheckBox c = new CheckBox(\"Inclure isolation facade\");\nc.setSelected(true);"),
            new ComponentInfo("Hyperlink",
                "Lien vers une documentation, un catalogue en ligne ou une ressource externe.",
                "Hyperlink h = new Hyperlink(\"Voir catalogue revetements\");\nh.setOnAction(e -> ouvrirCatalogue());")
        ));

        donnees.put(CategoryType.SAISIE, List.of(
            new ComponentInfo("TextField",
                "Saisir un identifiant ou une valeur numerique : idBatiment, idNiveau, idAppart, largeur mur.",
                "TextField tf = new TextField();\ntf.setPromptText(\"ID Batiment (ex: B001)\");"),
            new ComponentInfo("PasswordField",
                "Proteger l'acces a l'application (authentification optionnelle).",
                "PasswordField pf = new PasswordField();\npf.setPromptText(\"Mot de passe\");"),
            new ComponentInfo("TextArea",
                "Saisir une description detaillee du batiment ou des notes sur le devis.",
                "TextArea ta = new TextArea();\nta.setPromptText(\"Notes sur le devis...\");\nta.setWrapText(true);")
        ));

        donnees.put(CategoryType.CHOIX, List.of(
            new ComponentInfo("ChoiceBox",
                "Choisir le type de batiment : Maison individuelle ou Immeuble.",
                "ChoiceBox<String> cb = new ChoiceBox<>();\ncb.getItems().addAll(\"Maison\", \"Immeuble\");\ncb.getSelectionModel().selectFirst();"),
            new ComponentInfo("ComboBox",
                "Selectionner un type de revetement dans le catalogue (peinture, carrelage, parquet...).",
                "ComboBox<String> cb = new ComboBox<>();\ncb.getItems().addAll(\"Peinture\", \"Carrelage\", \"Parquet\",\n        \"Bardage\", \"Isolant projete\");\ncb.setEditable(true);"),
            new ComponentInfo("DatePicker",
                "Choisir la date d'etablissement du devis.",
                "DatePicker dp = new DatePicker(LocalDate.now());\ndp.setPromptText(\"Date du devis\");"),
            new ComponentInfo("ColorPicker",
                "Choisir la couleur d'une peinture ou d'un revetement.",
                "ColorPicker cp = new ColorPicker(Color.WHITE);\ncp.setOnAction(e -> appliquerCouleur(cp.getValue()));"),
            new ComponentInfo("Slider",
                "Ajuster la hauteur sous plafond d'un niveau (ex: 2.0 a 4.0 m).",
                "Slider s = new Slider(2.0, 4.0, 2.5);\ns.setMajorTickUnit(0.5);\ns.setShowTickLabels(true);"),
            new ComponentInfo("Spinner",
                "Saisir le nombre de niveaux ou d'appartements (valeur numerique bornee).",
                "Spinner<Integer> sp = new Spinner<>(1, 20, 1);\nsp.setEditable(true);")
        ));

        donnees.put(CategoryType.AFFICHAGE, List.of(
            new ComponentInfo("Label",
                "Afficher un resultat : surface totale, prix d'un revetement, devis total (euros).",
                "Label l = new Label(\"Devis total : 12 500 €\");\nl.setStyle(\"-fx-font-size:16; -fx-font-weight:bold;\");"),
            new ComponentInfo("ProgressBar",
                "Indiquer la progression d'un calcul (ex: calcul de devis sur un grand batiment).",
                "ProgressBar pb = new ProgressBar();\npb.setProgress(0.7); // 70%"),
            new ComponentInfo("ProgressIndicator",
                "Indicateur circulaire de progression (chargement d'un fichier catalogue).",
                "ProgressIndicator pi = new ProgressIndicator();\npi.setProgress(ProgressIndicator.INDETERMINATE_PROGRESS);"),
            new ComponentInfo("Canvas",
                "Dessiner le plan 2D des pieces d'un niveau (murs, ouvertures, labels).",
                "Canvas canvas = new Canvas(400, 300);\nGraphicsContext gc = canvas.getGraphicsContext2D();\ngc.strokeRect(x, y, largeur, hauteur); // Piece\ngc.fillText(\"Chambre\", x+10, y+20);  // Label")
        ));

        donnees.put(CategoryType.CONTENEURS, List.of(
            new ComponentInfo("VBox",
                "Organiser verticalement les champs de saisie d'un formulaire (batiment, niveau, appartement).",
                "VBox form = new VBox(10,\n  new Label(\"ID:\"), new TextField(),\n  new Label(\"Type:\"), new ComboBox<>());"),
            new ComponentInfo("HBox",
                "Aligner horizontalement les boutons d'action (Calculer, Sauvegarder, Afficher plan).",
                "HBox actions = new HBox(10,\n  new Button(\"Calculer\"),\n  new Button(\"Sauvegarder\"),\n  new Button(\"Plan 2D\"));"),
            new ComponentInfo("BorderPane",
                "Structure principale : plan 2D au centre, catalogue a gauche, detail devis a droite, toolbar en haut.",
                "BorderPane bp = new BorderPane();\nbp.setCenter(canvasPlan2D);\nbp.setRight(panneauDevis);\nbp.setLeft(catalogueRevetements);\nbp.setTop(toolBar);"),
            new ComponentInfo("GridPane",
                "Formulaire de saisie en grille : label + champ cote a cote (idBatiment, nbreNiveaux...).",
                "GridPane g = new GridPane();\ng.setHgap(10); g.setVgap(8);\ng.add(new Label(\"ID Batiment:\"), 0, 0);\ng.add(new TextField(), 1, 0);\ng.add(new Label(\"Nb niveaux:\"), 0, 1);\ng.add(new Spinner<>(1,10,1), 1, 1);"),
            new ComponentInfo("StackPane",
                "Superposer le plan 2D et une couche d'annotations (noms des pieces, cotations).",
                "StackPane calque = new StackPane(canvas, coucheAnnotations);"),
            new ComponentInfo("ScrollPane",
                "Permettre le defilement du plan 2D d'un grand batiment ou de la liste des pieces.",
                "ScrollPane sp = new ScrollPane(canvasPlan);\nsp.setPannable(true);")
        ));

        donnees.put(CategoryType.TABLEAUX, List.of(
            new ComponentInfo("ListView",
                "Lister les usages de pieces disponibles (chambre, cuisine, salle de bain, etc.).",
                "ListView<String> lv = new ListView<>();\nlv.getItems().addAll(\n  \"Chambre\", \"Cuisine\", \"Salon\",\n  \"Salle de bain\", \"Degagement\", \"WC\");"),
            new ComponentInfo("TableView",
                "Afficher le catalogue de revetements avec leurs caracteristiques et prix unitaires.",
                "TableView<Revetement> table = new TableView<>();\nTableColumn<Revetement, String> colType =\n  new TableColumn<>(\"Type\");\ncolType.setCellValueFactory(new PropertyValueFactory<>(\"type\"));\nTableColumn<Revetement, Double> colPrix =\n  new TableColumn<>(\"Prix €/m²\");\ncolPrix.setCellValueFactory(new PropertyValueFactory<>(\"prixUnitaire\"));"),
            new ComponentInfo("TreeView",
                "Representer la hierarchie complete : Batiment > Niveau > Appartement > Piece.",
                "TreeItem<String> bat = new TreeItem<>(\"Batiment B001\");\nTreeItem<String> niv = new TreeItem<>(\"Niveau 0 (RDC)\");\nTreeItem<String> app = new TreeItem<>(\"Appart A01\");\napp.getChildren().add(new TreeItem<>(\"Chambre\"));\nniv.getChildren().add(app);\nbat.getChildren().add(niv);")
        ));

        donnees.put(CategoryType.MENUS_DIALOGUES, List.of(
            new ComponentInfo("MenuBar",
                "Barre de menus : Fichier (nouveau/ouvrir catalogue/sauvegarder devis), Devis (calculer/afficher).",
                "MenuBar mb = new MenuBar();\nMenu fichier = new Menu(\"Fichier\");\nMenu devis = new Menu(\"Devis\");\nfichier.getItems().addAll(\n  new MenuItem(\"Nouveau batiment\"),\n  new MenuItem(\"Ouvrir catalogue...\"),\n  new MenuItem(\"Sauvegarder devis...\"));\ndevis.getItems().add(new MenuItem(\"Calculer devis\"));\nmb.getMenus().addAll(fichier, devis);"),
            new ComponentInfo("ContextMenu",
                "Menu contextuel sur une piece du plan 2D (clic droit : modifier, choisir revetement).",
                "ContextMenu cm = new ContextMenu(\n  new MenuItem(\"Modifier la piece\"),\n  new MenuItem(\"Choisir revetement\"),\n  new MenuItem(\"Supprimer\"));\ncanvas.setOnMouseClicked(e -> {\n  if (e.getButton() == MouseButton.SECONDARY)\n    cm.show(canvas, e.getScreenX(), e.getScreenY());\n});"),
            new ComponentInfo("Alert",
                "Afficher le devis total calcule ou signaler une erreur de saisie.",
                "Alert a = new Alert(Alert.AlertType.INFORMATION);\na.setTitle(\"Devis estimatif\");\na.setHeaderText(\"Batiment B001 - Immeuble 3 niveaux\");\na.setContentText(\"Devis total : 48 750 €\");\na.showAndWait();"),
            new ComponentInfo("TextInputDialog",
                "Demander l'identifiant du batiment ou d'un appartement a l'utilisateur.",
                "TextInputDialog d = new TextInputDialog(\"B001\");\nd.setTitle(\"Nouveau batiment\");\nd.setHeaderText(\"Identifiant du batiment :\");\nOptional<String> id = d.showAndWait();"),
            new ComponentInfo("FileChooser",
                "Ouvrir le fichier catalogue de revetements ou sauvegarder le devis en fichier texte.",
                "FileChooser fc = new FileChooser();\nfc.setTitle(\"Ouvrir catalogue de revetements\");\nfc.getExtensionFilters().add(\n  new ExtensionFilter(\"Fichiers texte\", \"*.txt\"));\nFile f = fc.showOpenDialog(stage);")
        ));
    }

    public List<ComponentInfo> getComposants(CategoryType type) {
        return donnees.getOrDefault(type, List.of());
    }

    public void ajouterAuJournal(String message) {
        journal += message + "\n";
    }

    public String getJournal() { return journal; }
    public void viderJournal()  { journal = ""; }
}
