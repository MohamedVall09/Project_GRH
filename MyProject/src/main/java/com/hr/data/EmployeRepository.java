package com.hr.data;

import com.hr.models.Employe;
import java.util.ArrayList;
import java.util.List;

// Classe qui stocke les employés en mémoire (simule une base de données)
public class EmployeRepository {
    private static List<Employe> employes = new ArrayList<>();

    static {
        // Ajout de quelques employés par défaut
        employes.add(new Employe("Alice", "Administrateur", "RH"));
        employes.add(new Employe("Paul", "Responsable", "IT"));
        employes.add(new Employe("Jean", "Employé", "Marketing"));
    }

    // ✅ Correction du nom de la méthode (PAS getAllEmployees(), mais getAllEmployes())
    public static List<Employe> getAllEmployes() {
        return employes;
    }

    // ✅ Ajout d'une méthode pour ajouter un employé
    public static void addEmploye(Employe employe) {
        employes.add(employe);
    }

    // ✅ Ajout d'une méthode pour supprimer un employé par son nom
    public static void removeEmploye(String nom) {
        employes.removeIf(e -> e.getNom().equals(nom));
    }
}
