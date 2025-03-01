package com.hr.models;

import java.util.ArrayList;
import java.util.List;

public class Departement {
    private String id;
    private String nom;
    private String description;
    private List<String> employes; // ✅ Liste des noms des employés dans ce département

    public Departement(String id, String nom, String description) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.employes = new ArrayList<>();
    }

    // ✅ Getters
    public String getId() { return id; }
    public String getNom() { return nom; }
    public String getDescription() { return description; }
    public List<String> getEmployes() { return employes; }

    // ✅ Setters
    public void setNom(String nom) { this.nom = nom; }
    public void setDescription(String description) { this.description = description; }
    public void ajouterEmploye(String employeNom) { this.employes.add(employeNom); }
    public void retirerEmploye(String employeNom) { this.employes.remove(employeNom); }
}

