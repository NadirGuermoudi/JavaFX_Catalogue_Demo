package com.example.catalogue.model;

/**
 * Represente les informations descriptives d'un composant JavaFX
 * (nom, description courte et exemple de code).
 * Utilise par le modele pour fournir un texte d'aide a la vue.
 */
public class ComponentInfo {

    private final String nom;
    private final String description;
    private final String exempleCode;

    public ComponentInfo(String nom, String description, String exempleCode) {
        this.nom = nom;
        this.description = description;
        this.exempleCode = exempleCode;
    }

    public String getNom() { return nom; }
    public String getDescription() { return description; }
    public String getExempleCode() { return exempleCode; }
}
