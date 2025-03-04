package com.hr.models;

import java.util.Date;
import java.util.List;

public class Evaluation {
	private String id;
   	private String employeId;
   	private String responsableId;
    private int note;
    private String commentaire;
    private Date dateEvaluation;

    public Evaluation(String id, String responsableId, String employeId, int note, String commentaire, Date dateEvaluation) {
    	this.id = id;
    	this.employeId = employeId;
    	this.responsableId = responsableId;
        this.note = note;
        this.dateEvaluation = dateEvaluation;
        this.commentaire = commentaire;
    }
    
    public Evaluation() {
    }

    // ✅ Getters
    public String getId() {return id;}
    public String getEmployeId() { return employeId; }
    public String getResponsableId() { return responsableId; }
    public int getNote() { return note; }
    public String getCommentaire() { return commentaire; }
    public Date getDateEvaluation() { return dateEvaluation; }

    // ✅ Setters
    public void setId(String id) {this.id = id;}
    public void setEmployeId(String employeId) { this.employeId = employeId; }
    public void setResponsableId(String responsableId) { this.responsableId = responsableId; }
    public void setNote(int note) { this.note = note; }
    public void setCommentaire(String commentaire) { this.commentaire = commentaire; }
    public void setDateEvaluation(Date dateEvaluation) { this.dateEvaluation = dateEvaluation; }

}

