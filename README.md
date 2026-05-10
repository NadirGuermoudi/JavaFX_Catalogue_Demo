# Catalogue JavaFX - Projet Demo MVC

Application **JavaFX** pedagogique presentant un catalogue de composants JavaFX,
organisee selon le patron de conception **MVC (Model - View - Controller)**.

L'objectif est de fournir aux etudiant·e·s :
- un **catalogue interactif** des composants JavaFX classiques (Button, TextField,
  TableView, MenuBar, Alert, ...) ;
- un exemple concret d'**architecture MVC** sur lequel s'appuyer pour leur propre projet ;
- une **documentation** integree dans l'interface (description + extrait de code) pour chaque composant.

> Aucune base de donnees n'est utilisee : toutes les donnees sont en memoire dans la classe `CatalogueModel`.

---

## Sommaire

- [Catalogue JavaFX - Projet Demo MVC](#catalogue-javafx---projet-demo-mvc)
  - [Sommaire](#sommaire)
  - [Apercu](#apercu)
  - [Architecture MVC](#architecture-mvc)
  - [Categories couvertes](#categories-couvertes)
  - [Pre-requis](#pre-requis)
  - [Compilation et execution](#compilation-et-execution)
    - [Option 1 : avec Maven (recommande)](#option-1--avec-maven-recommande)
    - [Option 2 : avec `javac` et le SDK JavaFX](#option-2--avec-javac-et-le-sdk-javafx)
  - [Diagrammes](#diagrammes)
  - [Pour aller plus loin](#pour-aller-plus-loin)
  - [Licence](#licence)

---

## Apercu

L'application affiche une fenetre principale composee :

- d'un **TabPane** : un onglet par categorie de composants ;
- d'un **SplitPane** dans chaque onglet :
  - a gauche : une **zone de demonstration** avec les composants vivants ;
  - a droite : une **zone de documentation** (description + extrait de code Java) ;
- d'une **barre d'etat** en bas affichant le retour des actions de l'utilisateur.

Voir la maquette dans [`diagrams/Interface_Mockup.drawio`](diagrams/Interface_Mockup.drawio).

---

## Architecture MVC

```
src/main/java/com/example/catalogue/
+-- Main.java                  <-- point d'entree (heritage de javafx.application.Application)
+-- model/                     <-- couche MODEL : donnees pures, sans JavaFX (sauf observable si besoin)
|   +-- CatalogueModel.java
|   +-- ComponentInfo.java
|   +-- CategoryType.java
+-- view/                      <-- couche VIEW  : construction de l'interface
|   +-- MainView.java
|   +-- CategoryView.java       (classe abstraite : squelette commun)
|   +-- BoutonsView.java
|   +-- SaisieView.java
|   +-- ChoixView.java
|   +-- AffichageView.java
|   +-- ConteneursView.java
|   +-- TableauxView.java
|   +-- MenusDialoguesView.java
+-- controller/                <-- couche CONTROLLER : evenements et glue Model <-> View
    +-- MainController.java
    +-- BoutonsController.java
    +-- SaisieController.java
    +-- ChoixController.java
    +-- AffichageController.java
    +-- TableauxController.java
    +-- MenusDialoguesController.java
```

**Regles de separation respectees :**

| Couche      | Connait                | Ne doit PAS connaitre        |
|-------------|------------------------|------------------------------|
| Model       | -                      | View, Controller             |
| View        | composants JavaFX      | logique metier               |
| Controller  | View + Model           | -                            |

Voir le diagramme de classes : [`diagrams/UML_Class_Diagram.drawio`](diagrams/UML_Class_Diagram.drawio)

---

## Categories couvertes

| Onglet               | Composants demontres                                                                |
|----------------------|-------------------------------------------------------------------------------------|
| Boutons              | `Button`, `ToggleButton`, `RadioButton`, `CheckBox`, `Hyperlink`                    |
| Saisie de texte      | `TextField`, `PasswordField`, `TextArea`                                            |
| Choix & Selection    | `ChoiceBox`, `ComboBox`, `DatePicker`, `ColorPicker`, `Slider`, `Spinner`           |
| Affichage            | `Label`, `Tooltip`, `ProgressBar`, `ProgressIndicator`, `Canvas`                    |
| Conteneurs (Layouts) | `VBox`, `HBox`, `BorderPane`, `GridPane`, `StackPane`                               |
| Tableaux & Listes    | `ListView`, `TableView`, `TreeView`                                                 |
| Menus & Dialogues    | `MenuBar`, `ToolBar`, `ContextMenu`, `Alert`, `TextInputDialog`, `ChoiceDialog`, `FileChooser` |

---

## Pre-requis

- **JDK 17 ou superieur** (le projet a ete teste avec JDK 21 / 25).
- **Maven 3.8+** (option 1) **OU** le **SDK JavaFX** (option 2).

Verifier les versions installees :

```bash
java -version
javac -version
mvn -v   # si vous utilisez Maven
```

---

## Compilation et execution

### Option 1 : avec Maven (recommande)

Le `pom.xml` declare les dependances JavaFX et le plugin `javafx-maven-plugin`.
Maven telecharge automatiquement les bibliotheques JavaFX.

```bash
# Depuis le dossier du projet :
mvn clean compile           # compile les sources
mvn javafx:run              # lance l'application
```

### Option 2 : avec `javac` et le SDK JavaFX

1. Telecharger le SDK JavaFX depuis https://gluonhq.com/products/javafx/
   (par exemple JavaFX 21 ou 23) et le decompresser.

2. Definir une variable d'environnement pointant vers le dossier `lib/` du SDK :

```bash
# macOS / Linux
export PATH_TO_FX=/chemin/vers/javafx-sdk-26.0.1/lib

# Windows (PowerShell)
$env:PATH_TO_FX="C:\chemin\vers\javafx-sdk-26.0.1\lib"
```

3. Compiler depuis le dossier du projet :

```bash
# macOS / Linux
javac --module-path "$PATH_TO_FX" \
      --add-modules javafx.controls,javafx.graphics \
      -d out \
      $(find src/main/java -name "*.java")
```

```powershell
# Windows (PowerShell)
javac --module-path $env:PATH_TO_FX `
      --add-modules javafx.controls,javafx.graphics `
      -d out `
      (Get-ChildItem -Recurse src\main\java -Filter *.java | %{ $_.FullName })
```

4. Executer :

```bash
# macOS / Linux
java --module-path "$PATH_TO_FX" \
     --add-modules javafx.controls,javafx.graphics \
     -cp out \
     com.example.catalogue.Main
```

```powershell
# Windows (PowerShell)
java --module-path $env:PATH_TO_FX `
     --add-modules javafx.controls,javafx.graphics `
     -cp out `
     com.example.catalogue.Main
```

---

## Diagrammes

Tous les diagrammes sont au format **drawio** (lisibles avec
[https://app.diagrams.net](https://app.diagrams.net) ou l'extension VS Code
*Draw.io Integration*) :

| Fichier                                               | Contenu                                                                  |
|-------------------------------------------------------|--------------------------------------------------------------------------|
| `diagrams/UML_Class_Diagram.drawio`                   | Diagramme de classes : Model / View / Controller + relations             |
| `diagrams/UseCase_Diagram.drawio`                     | Diagramme de cas d'utilisation (acteur Etudiant + cas)                   |
| `diagrams/Interface_Mockup.drawio`                    | Maquette de l'IHM + arbre de composition des `Node` JavaFX               |

---

## Pour aller plus loin

- Documentation officielle JavaFX : https://openjfx.io/javadoc/21/
- Tutoriel JavaFX : https://openjfx.io/openjfx-docs/
- Outil visuel pour concevoir les vues : **Scene Builder** (https://gluonhq.com/products/scene-builder/)
- Pour structurer un plus gros projet, regarder le module **FXML** et l'annotation `@FXML`.

---

## Licence

Projet pedagogique - libre de reutilisation dans le cadre des cours.
