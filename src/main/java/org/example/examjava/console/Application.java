package org.example.examjava.console;

import metier.ICabinetMetierImpl;
import models.Consultation;
import models.Medecin;
import models.Patient;

import java.util.Date;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        ICabinetMetierImpl metier = new ICabinetMetierImpl();

        System.out.println("Adding patients...");
        metier.ajouterPatient(new Patient(1, "John", "Doe", "AABBCCDD", "0611223344", "email@gmail.com", new Date()));
        metier.ajouterPatient(new Patient(2, "Jane", "Smith", "DDCCBBAA", "0622334455", "jane@gmail.com", new Date()));

        System.out.println("Listing all patients:");
        List<Patient> patients = metier.listPatients();
        patients.forEach(System.out::println);

        System.out.println("Deleting patient with ID 1...");
        metier.supprimerPatient(1);

        System.out.println("Listing all patients after deletion:");
        patients = metier.listPatients();
        patients.forEach(System.out::println);

        System.out.println("Searching for patients with keyword 'Jane':");
        List<Patient> searchedPatients = metier.getPatientByKeyWork("Jane");
        searchedPatients.forEach(System.out::println);

        System.out.println("Adding doctors...");
        metier.ajouterMedecin(new Medecin(1, "Dr. Smith", "John", "smith_john@mail.com", "0777888999"));
        metier.ajouterMedecin(new Medecin(2, "Dr. Brown", "Emily", "brown_emily@mail.com", "0666555444"));

        System.out.println("Listing all doctors:");
        List<Medecin> doctors = metier.listMedecins();
        doctors.forEach(System.out::println);

        System.out.println("Deleting doctor with ID 1...");
        metier.supprimerMedecin(1);

        System.out.println("Listing all doctors after deletion:");
        doctors = metier.listMedecins();
        doctors.forEach(System.out::println);

        System.out.println("Adding consultations...");
        Consultation consultation1 = new Consultation(1, new Date("2023/10/10"));
        Consultation consultation2 = new Consultation(2, new Date("2023/11/11"));

        metier.ajouterConsultation(consultation1, 2, 2);
        metier.ajouterConsultation(consultation2, 2, 2);

        System.out.println("Listing all consultations:");
        List<Consultation> consultations = metier.listConsultations();
        consultations.forEach(System.out::println);

        System.out.println("Listing consultations for Patient ID 2:");
        List<Consultation> patientConsultations = metier.listPatientConsultations(2);
        patientConsultations.forEach(System.out::println);

        System.out.println("Deleting consultation with ID 1...");
        metier.supprimerConsultation(1);

        System.out.println("Listing all consultations after deletion:");
        consultations = metier.listConsultations();
        consultations.forEach(System.out::println);

        System.out.println("Listing consultations for Doctor ID 2:");
        List<Consultation> doctorConsultations = metier.listConsutationsByMedecin(2);
        doctorConsultations.forEach(System.out::println);
    }
}