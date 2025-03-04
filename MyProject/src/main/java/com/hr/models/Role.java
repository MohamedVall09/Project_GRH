package com.hr.models;

public class Role {
    private String id;
    private String nom;
    
    public Role () {}

    public Role(String id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public String getId() { return id; }
    public String getNom() { return nom; }
    
    public void setNom(String nom) { this.nom = nom; }
}
