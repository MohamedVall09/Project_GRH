package com.hr.models;

public class Employe {
    private String nom;
    private String role;
    private String departement;

    public Employe(String nom, String role, String departement) {
        this.nom = nom;
        this.role = role;
        this.departement = departement;
    }

    public String getNom() { return nom; }
    public String getRole() { return role; }
    public String getDepartement() { return departement; }

    public void setNom(String nom) { this.nom = nom; }
    public void setRole(String role) { this.role = role; }
    public void setDepartement(String departement) { this.departement = departement; }
}
