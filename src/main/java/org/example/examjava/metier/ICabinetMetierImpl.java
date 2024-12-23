package org.example.examjava.metier;

import database.SingletonConnexionDB;
import models.Consultation;
import models.Medecin;
import models.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ICabinetMetierImpl implements ICabinetMetier {
    @Override
    public List<Patient> listPatients() {
        Connection connection = SingletonConnexionDB.getConnection();
        List<Patient> patients = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM patients");
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                Patient p = new Patient(res.getInt("id_patient"), res.getString("nom"), res.getString("prenom"), res.getString("cin"), res.getString("telephone"), res.getString("email"), res.getDate("date_naissance"));
                patients.add(p);
            }
        } catch (Exception e) {
            throw new RuntimeException("couldn't fetch patients from database", e);
        }
        return patients;
    }

    @Override
    public List<Patient> getPatientByKeyWork(String key) {
        Connection connection = SingletonConnexionDB.getConnection();
        List<Patient> patients = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM patients WHERE nom LIKE ? OR prenom LIKE ?");
            statement.setString(1, "%" + key + "%");
            statement.setString(2, "%" + key + "%");
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                Patient p = new Patient(res.getInt("id_patient"), res.getString("nom"), res.getString("prenom"), res.getString("cin"), res.getString("telephone"), res.getString("email"), res.getDate("date_naissance"));
                patients.add(p);
            }
        } catch (Exception e) {
            throw new RuntimeException("couldn't fetch patients from database", e);
        }
        return patients;
    }

    @Override
    public Patient ajouterPatient(Patient p) {
        Connection connection = SingletonConnexionDB.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO patients(id_patient, nom, prenom, cin, telephone, email, date_naissance) VALUES(?, ?, ?, ?, ?, ?, ?)");
            statement.setInt(1, p.getId_patient());
            statement.setString(2, p.getNom());
            statement.setString(3, p.getPrenom());
            statement.setString(4, p.getCin());
            statement.setString(5, p.getTelephone());
            statement.setString(6, p.getEmail());
            statement.setDate(7, new java.sql.Date(p.getDate_naissance().getTime()));
            statement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("couldn't create a new patient in database", e);
        }
        return p;
    }

    @Override
    public void supprimerPatient(int id) {
        Connection connection = SingletonConnexionDB.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM patients WHERE id_patient=?");
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("couldn't delete a patient from database", e);
        }
    }

    @Override
    public List<Consultation> listPatientConsultations(int idPatient) {
        Connection connection = SingletonConnexionDB.getConnection();
        List<Consultation> consultations = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM consultations WHERE id_patient=?");
            statement.setInt(1, idPatient);
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                Consultation c = new Consultation(res.getInt("id_consultation"), res.getDate("date_consultation"));
                consultations.add(c);
            }
        } catch (Exception e) {
            throw new RuntimeException("couldn't fetch consultations from database", e);
        }
        return consultations;
    }

    @Override
    public Medecin ajouterMedecin(Medecin m) {
        Connection connection = SingletonConnexionDB.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO medecins(id_medecin, nom, prenom, email, tel) VALUES(?, ?, ?, ?, ?)");
            statement.setInt(1, m.getId_medecin());
            statement.setString(2, m.getNom());
            statement.setString(3, m.getPrenom());
            statement.setString(4, m.getEmail());
            statement.setString(5, m.getTel());
            statement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("couldn't create a new medecin in database", e);
        }
        return m;
    }

    @Override
    public List<Medecin> listMedecins() {
        Connection connection = SingletonConnexionDB.getConnection();
        List<Medecin> medecins = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM medecins");
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                Medecin m = new Medecin(res.getInt("id_medecin"), res.getString("nom"), res.getString("prenom"), res.getString("email"), res.getString("tel"));
                medecins.add(m);
            }
        } catch (Exception e) {
            throw new RuntimeException("couldn't fetch medecins from database", e);
        }
        return medecins;
    }

    @Override
    public void supprimerMedecin(int id) {
        Connection connection = SingletonConnexionDB.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM medecins WHERE id_medecin=?");
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("couldn't delete a medecin from database", e);
        }
    }

    @Override
    public List<Consultation> listConsutationsByMedecin(int idMedecin) {
        Connection connection = SingletonConnexionDB.getConnection();
        List<Consultation> consultations = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM consultations WHERE id_medecin=?");
            statement.setInt(1, idMedecin);
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                Consultation c = new Consultation(res.getInt("id_consultation"), res.getDate("date_consultation"));
                consultations.add(c);
            }
        } catch (Exception e) {
            throw new RuntimeException("couldn't fetch consultations from database", e);
        }
        return consultations;
    }

    @Override
    public Consultation ajouterConsultation(Consultation c, int idMedecin, int idPatient) {
        Connection connection = SingletonConnexionDB.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO consultations(id_consultation, date_consultation, id_medecin, id_patient) VALUES(?, ?, ?, ?)");
            statement.setInt(1, c.getId_consultation());
            statement.setDate(2, new java.sql.Date(c.getDate_consultation().getTime()));
            statement.setInt(3, idMedecin);
            statement.setInt(4, idPatient);
            statement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("couldn't create a new consultation in database", e);
        }
        return c;
    }

    @Override
    public List<Consultation> listConsultations() {
        Connection connection = SingletonConnexionDB.getConnection();
        List<Consultation> consultations = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM consultations");
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                Consultation c = new Consultation(res.getInt("id_consultation"), res.getDate("date_consultation"));
                consultations.add(c);
            }
        } catch (Exception e) {
            throw new RuntimeException("couldn't fetch consultations from database", e);
        }
        return consultations;
    }

    @Override
    public void supprimerConsultation(int id) {
        Connection connection = SingletonConnexionDB.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM consultations WHERE id_consultation=?");
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("couldn't delete a consultation from database", e);
        }
    }
}
