package com.hr.data;

import com.hr.models.Departement;
import java.util.ArrayList;
import java.util.List;

public class DepartementRepository {
    private static List<Departement> departements = new ArrayList<>();

    static {
        // Ajout de départements par défaut
        Departement rh = new Departement("1", "RH", "Gestion de resource humaine");
        Departement di = new Departement("2", "DI", "Développement Informatique");
        Departement marketing = new Departement("3", "Marketing", "Stratégie et communication");
        Departement it = new Departement("4", "IT", "Stratégie et communication");
        

        departements.add(rh);
        departements.add(it);
        departements.add(marketing);
        departements.add(di);
    }

    // Récupérer tous les départements
    public static List<Departement> getAllDepartements() {
        return departements;
    }
    
    public static Departement getDepartementByName(String nom) {
        return departements.stream()
                .filter(dep -> dep.getNom().equalsIgnoreCase(nom))
                .findFirst()
                .orElse(null);
    }


    // Récupérer un département par ID
    public static Departement getDepartementById(String id) {
        for (Departement d : departements) {
            if (d.getId().equals(id)) {
                return d;
            }
        }
        return null;
    }

    // Ajouter un département
    public static void addDepartement(Departement departement) {
        departements.add(departement);
    }

    // Mettre à jour un département
    public static void updateDepartement(String id, String nom, String description) {
        Departement d = getDepartementById(id);
        if (d != null) {
            d.setNom(nom);
            d.setDescription(description);
        }
    }

    // Supprimer un département
    public static void removeDepartement(String id) {
        departements.removeIf(d -> d.getId().equals(id));
    }
}
