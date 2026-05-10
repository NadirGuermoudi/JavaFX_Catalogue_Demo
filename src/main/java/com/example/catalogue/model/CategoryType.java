package com.example.catalogue.model;

/**
 * Enumere les categories de composants exposees par le catalogue.
 * Chaque categorie correspond a un onglet dans l'interface.
 */
public enum CategoryType {
    BOUTONS("Boutons"),
    SAISIE("Saisie de texte"),
    CHOIX("Choix & Selection"),
    AFFICHAGE("Affichage"),
    CONTENEURS("Conteneurs (Layouts)"),
    TABLEAUX("Tableaux & Listes"),
    MENUS_DIALOGUES("Menus & Dialogues");

    private final String libelle;

    CategoryType(String libelle) {
        this.libelle = libelle;
    }

    public String getLibelle() {
        return libelle;
    }
}
