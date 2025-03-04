package com.hr.data;

import com.hr.models.Role;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RoleRepository {
    private static List<Role> roles = new ArrayList<>();

    static {
        // Rôles par défaut
        roles.add(new Role(UUID.randomUUID().toString(), "Administrateur"));
        roles.add(new Role(UUID.randomUUID().toString(), "Responsable"));
        roles.add(new Role(UUID.randomUUID().toString(), "Employé"));
    }

    public static List<Role> getAllRoles() {
        return roles;
    }

    public static Role getRoleById(String id) {
        return roles.stream().filter(r -> r.getId().equals(id)).findFirst().orElse(null);
    }

    public static void addRole(String nom) {
        roles.add(new Role(UUID.randomUUID().toString(), nom));
    }

    public static void updateRole(String id, String newName) {
        Role role = getRoleById(id);
        if (role != null) {
            role.setNom(newName);
        }
    }
    
    public static Role getRoleByName(String nom) {
        return roles.stream()
                .filter(role -> role.getNom().equalsIgnoreCase(nom))
                .findFirst()
                .orElse(null);
    }


    public static void removeRole(String id) {
        roles.removeIf(r -> r.getId().equals(id));
    }
}

