package com.hr.models;

public class Employe {
    private String id;
    private String nom;
    private String prenom;
    private String numeroSecuriteSociale;
    private String adresse;
    private String telephone;
    private String email;
    private String role;
    private String departement;
    private String username;
    private String password;
    
    public Employe() {}

    public Employe(String id, String nom, String prenom, String numeroSecuriteSociale,
                   String adresse, String telephone, String email, 
                   String role, String departement, String username, String password) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.numeroSecuriteSociale = numeroSecuriteSociale;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email = email;
        this.role = role;
        this.departement = departement;
        this.username = username;
        this.password = password;
    }

    // Getters et Setters
    public String getId() { return id; }
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getNumeroSecuriteSociale() { return numeroSecuriteSociale; }
    public String getAdresse() { return adresse; }
    public String getTelephone() { return telephone; }
    public String getEmail() { return email; }
    public String getRole() { return role; }
    public String getDepartement() { return departement; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }

    public void setId(String id) {
        this.id = id;
    }

    public void setNom(String nom) { this.nom = nom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public void setNumeroSecuriteSociale(String numeroSecuriteSociale) { this.numeroSecuriteSociale = numeroSecuriteSociale; }
    public void setAdresse(String adresse) { this.adresse = adresse; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
    public void setEmail(String email) { this.email = email; }
    public void setRole(String role) { this.role = role; }
}
