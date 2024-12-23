package org.example.examjava.metier;

import models.Consultation;
import models.Medecin;
import models.Patient;

import java.util.List;

public interface ICabinetMetier {
    // 1
    public List<Patient> listPatients();
    public List<Patient> getPatientByKeyWork(String key);
    public Patient ajouterPatient(Patient p);
    public void supprimerPatient(int id);
    public List<Consultation> listPatientConsultations(int idPatient);
    // 2
    public Medecin ajouterMedecin(Medecin m);
    public List<Medecin> listMedecins();
    public void supprimerMedecin(int id);
    public List<Consultation> listConsutationsByMedecin(int idMedecin);
    // 3
    public Consultation ajouterConsultation(Consultation c, int idMedecin, int idPatient);
    public List<Consultation> listConsultations();
    public void supprimerConsultation(int id);
}
