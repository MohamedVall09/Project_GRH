package com.hr.data;

import com.hr.models.Departement;
import java.util.ArrayList;
import java.util.List;

public class DepartementRepository {
    private static List<Departement> departements = new ArrayList<>();

    static {
        // Ajout de départements par défaut
        Departement rh = new Departement("1", "Ressources Humaines", "Gestion du personnel");
        Departement it = new Departement("2", "Informatique", "Développement et maintenance des systèmes");
        Departement marketing = new Departement("3", "Marketing", "Stratégie et communication");

        departements.add(rh);
        departements.add(it);
        departements.add(marketing);
    }

    // Récupérer tous les départements
    public static List<Departement> getAllDepartements() {
        return departements;
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
