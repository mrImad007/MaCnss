package org.cnss;

import org.cnss.Classes.Agent;
import org.cnss.Dao.AgentDAO;
import org.cnss.Classes.Medicine;
import org.cnss.Dao.MedicineDAO;
import org.cnss.Dao.PatientDAO;
import org.cnss.Dao.ReimbursementCaseDAO;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class AgentApp {
    public static void main() throws SQLException {
        AgentDAO agentDAO = new AgentDAO();
        PatientDAO patientDAO = new PatientDAO();
        ReimbursementCaseDAO reimbursementCaseDAO = new ReimbursementCaseDAO();
        MedicineDAO medicineDAO = new MedicineDAO();
        int agent_id;
        int patient_id = 0;

        String email = JOptionPane.showInputDialog(null, "Enter votre email");
        String password = JOptionPane.showInputDialog(null, "Enter votre mot de passe");

        agent_id = agentDAO.authenticate(email, password);

        if (agent_id >0) {
            int choice = Integer.parseInt(JOptionPane.showInputDialog(null, """
                    Bienvenue dans votre espace MaCss

                    1 : Créer un dossier pour un patient

                    2 : Voir la listes des dossiers

                    """));

            switch (choice) {
                case 1: {
                    String matricule = JOptionPane.showInputDialog(null, "Enter le matricule du patient");
                    ResultSet resultSet = patientDAO.getPatient(matricule);
                     while(resultSet.next()){
                        patient_id = resultSet.getInt("id");
                     }
                     if(patient_id>0){
                         boolean isCase = reimbursementCaseDAO.createCase(patient_id,agent_id);
                         if(isCase){
                             JOptionPane.showMessageDialog(null,"Dossier bien créé !");
                             int subchoice = Integer.parseInt(JOptionPane.showInputDialog(null,"Gestion du dossierCnss\n" +
                                     "1 : Ajouter un medicament\n" +
                                     "2 : Ajouter un radio \n" +
                                     "3 : Ajouter un scanner\n" +
                                     "4 : Ajouter une analyse\n" +
                                     "5 : Ajouter une visite médicale\n"));
                             switch (subchoice){
                                 case 1 : {
                                     int searchChoice = Integer.parseInt(JOptionPane.showInputDialog(null,"" +
                                             "1 : Entrer le nom du medicament\n" +
                                             "2 : Entrer le code du medicament"));
                                     switch (searchChoice){
                                         case 1 : {
                                             String name = JOptionPane.showInputDialog(null,"Entrer le nom du medicament");
                                             String rate = null;
                                             String code = null;
                                             String codeBare ;
                                             int payed_amount = 0;
                                             HashMap<String, String> medicineData = medicineDAO.checkMedicineByname(name);

                                             if (!medicineData.isEmpty()) {
                                                 rate = medicineData.get("rate");
                                                 code = medicineData.get("code");
                                             }
                                             if ("70%".equals(rate)) {
                                                 int Reimbursement_rate = 70;
                                                 codeBare = JOptionPane.showInputDialog(null,"Enter le codeBarre du medicament (optionel)");
                                                 if(codeBare.trim().isEmpty() || codeBare == null){
                                                     codeBare = null;
                                                 }
                                                 payed_amount = Integer.parseInt(JOptionPane.showInputDialog(null,"Enter le montant payé"));

                                                 Medicine medicine = new Medicine(code,codeBare,name,payed_amount,Reimbursement_rate);

                                                 boolean isDocAdded =medicineDAO.addDocument(medicine);

                                                 if (isDocAdded) {
                                                     JOptionPane.showMessageDialog(null,"Document bien ajouté !");
                                                 } else {
                                                     JOptionPane.showMessageDialog(null,"Erreur survenue !","error", JOptionPane.ERROR_MESSAGE);
                                                 }
                                             } else {
                                                 JOptionPane.showMessageDialog(null,"Ce produit n'est pas remboursable ou une erreur est survenue !","error", JOptionPane.ERROR_MESSAGE);
                                             }
                                             break;
                                         }
                                         case 2 : {
                                             String name = JOptionPane.showInputDialog(null,"Entrer le nom du medicament");
                                             String rate = null;
                                             String code = null;
                                             String codeBare ;
                                             int payed_amount = 0;
                                             HashMap<String, String> medicineData = medicineDAO.checkMedicineByCode(code);

                                             if (!medicineData.isEmpty()) {
                                                 rate = medicineData.get("rate");
                                                 code = medicineData.get("code");
                                             }
                                             if ("70%".equals(rate)) {
                                                 int Reimbursement_rate = 70;
                                                 codeBare = JOptionPane.showInputDialog(null,"Enter le codeBarre du medicament (optionel)");
                                                 if(codeBare.trim().isEmpty() || codeBare == null){
                                                     codeBare = null;
                                                 }
                                                 payed_amount = Integer.parseInt(JOptionPane.showInputDialog(null,"Enter le montant payé"));

                                                 Medicine medicine = new Medicine(code,codeBare,name,payed_amount,Reimbursement_rate);

                                                 boolean isDocAdded =medicineDAO.addDocument(medicine);

                                                 if (isDocAdded) {
                                                     JOptionPane.showMessageDialog(null,"Document bien ajouté !");
                                                 } else {
                                                     JOptionPane.showMessageDialog(null,"Erreur survenue !","error", JOptionPane.ERROR_MESSAGE);
                                                 }
                                             } else {
                                                 JOptionPane.showMessageDialog(null,"Ce produit n'est pas remboursable ou une erreur est survenue !","error", JOptionPane.ERROR_MESSAGE);
                                             }
                                         }
                                     }
                                     break;
                                 }
                             }
                         }else{
                             JOptionPane.showMessageDialog(null,"une erreur est survenue !", "error", JOptionPane.ERROR_MESSAGE);
                         }
                     }else{
                         JOptionPane.showMessageDialog(null,"Ce patient n'existe pas ! ", "error", JOptionPane.ERROR_MESSAGE);
                     }

                    break;
                }
                case 2: {
                    break;
                }
                default: {
                    JOptionPane.showMessageDialog(null, "Choix indisponible", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Agent introuvable !!", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}
