package com.hr.data;

import com.hr.models.Employe;
import java.util.ArrayList;
import java.util.List;

public class EmployeRepository {
    private static List<Employe> employes = new ArrayList<>();

    static {
        employes.add(new Employe("1", "Alice", "Dupont", "123456", "10 rue A", "0601020304", "alice@mail.com", "Administrateur", "RH", "alice", "123"));
        employes.add(new Employe("2", "Paul", "Martin", "654321", "20 rue B", "0612345678", "paul@mail.com", "Responsable", "IT", "paul", "1234"));
        employes.add(new Employe("3", "Marie", "Curie", "987654", "30 rue C", "0623456789", "marie@mail.com", "Employé", "Marketing", "marie", "abcd"));
    }

    // Récupérer tous les employés
    public static List<Employe> getAllEmployes() {
    	System.out.println(" Nombre d'employés dans la liste: " + employes.size());
        return employes;
    }

    // Récupérer un employé par ID
    public static Employe getEmployeById(String id) {
        for (Employe e : employes) {
            if (e.getId().equals(id)) {
                return e;
            }
        }
        return null;
    }

    // Ajouter un employé
    public static void addEmploye(Employe employe) {
        employes.add(employe);
        System.out.println("📜 Nouvelle liste des employés après ajout : " + employes.size());
        
        for (Employe e : employes) {
            System.out.println("✔ Employé : " + e.getNom() + " - ID : " + e.getId());
        }
    }


    // Modifier les informations d'un employé
    public static void updateEmploye(String id, String nom, String prenom, String numeroSecuriteSociale,
                                     String adresse, String telephone, String email, String role) {
        Employe employe = getEmployeById(id);
        if (employe != null) {
            employe.setNom(nom);
            employe.setPrenom(prenom);
            employe.setNumeroSecuriteSociale(numeroSecuriteSociale);
            employe.setAdresse(adresse);
            employe.setTelephone(telephone);
            employe.setEmail(email);
            employe.setRole(role);
        }
    }
    public static Employe getEmployeByLogin(String username, String password) {
        for (Employe e : employes) {
            if (e.getUsername().equals(username) && e.getPassword().equals(password)) {
                return e;
            }
        }
        return null;
    }
    
    public static List<Employe> getEmployesByDepartement(String departement) {
        List<Employe> employesParDepartement = new ArrayList<>();
        for (Employe e : employes) {
            if (e.getDepartement().equals(departement)) {
                employesParDepartement.add(e);
            }
        }
        return employesParDepartement;
    }
    
    



    // Supprimer un employé
    public static void removeEmploye(String id) {
        employes.removeIf(e -> e.getId().equals(id));
    }
}
