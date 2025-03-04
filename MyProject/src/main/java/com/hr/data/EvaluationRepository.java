package com.hr.data;

import com.hr.models.Evaluation;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EvaluationRepository {
    private static List<Evaluation> evaluations = new ArrayList<>();

    static {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date dateEvaluation = formatter.parse("2025-03-03"); // Date par défaut

            Evaluation rh = new Evaluation("1","3", "2", 10, "Gestion du personnel", dateEvaluation);
            Evaluation it = new Evaluation("2","5", "3", 9, "Développement et maintenance des systèmes", dateEvaluation);
            Evaluation marketing = new Evaluation("3","5", "4", 16, "Stratégie et communication", dateEvaluation);

            evaluations.add(rh);
            evaluations.add(it);
            evaluations.add(marketing);
        } catch (Exception e) {
            System.out.println("Erreur : Impossible d'initialiser les évaluations par défaut.");
        }
    }

    public static List<Evaluation> getAllEvaluation() {
        return evaluations;
    }

    public static Evaluation getEvaluationById(String id) {
        return evaluations.stream().filter(e -> e.getId().equals(id)).findFirst().orElse(null);
    }

    public static void addEvaluation(Evaluation evaluation) {
        evaluations.add(evaluation);
    }

    public static boolean updateEvaluation(String id, Evaluation newEval) {
        for (Evaluation e : evaluations) {
            if (e.getId().equals(id)) {
                e.setNote(newEval.getNote());
                e.setCommentaire(newEval.getCommentaire());
                return true;
            }
        }
        return false;
    }

    public static boolean removeEvaluation(String id) {
        return evaluations.removeIf(e -> e.getId().equals(id));
    }
}
