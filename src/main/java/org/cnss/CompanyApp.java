package org.cnss;

import org.cnss.Classes.Company;
import org.cnss.Classes.Employee;
import org.cnss.Dao.CompanyDAO;
import org.cnss.Dao.EmployeeDAO;

import javax.swing.*;
import java.sql.Date;


public class CompanyApp {
    public static void main() {
        int choice = Integer.parseInt(JOptionPane.showInputDialog(null,"Bienvenue dans votre EspaceCompany" +
                "1 : Ajouter une nouvelle societé\n" +
                "2 : Ajouter un(e) employé(e)\n" +
                "3 : Declaration mensuelle\n" +
                "4 : "));
        switch (choice){
            case 1 : {
                    String companyName = null;
                    while (companyName == null || companyName.trim().equals("")){
                        companyName = JOptionPane.showInputDialog(null,"Entrer le nom de la societé a créer : ");
                    }
                    Company company = new Company(companyName);
                    CompanyDAO companyDAO = new CompanyDAO();

                    boolean checker = companyDAO.createCompany(company);

                    if (checker == false){
                     JOptionPane.showMessageDialog(null,"Une erreur est surevenu !");
                    }
                break;
            }
            case 2 : {
                    String matricule = JOptionPane.showInputDialog(null,"Entrer un matricule pour cet employé");
                    String name = JOptionPane.showInputDialog(null,"Entrer le nom de l'employé");
                    String bornDate = JOptionPane.showInputDialog(null,"Entrer la date de naissance de l'employé : yyyy-mm-dd");
                    String companyName = JOptionPane.showInputDialog(null,"Entrer le nom de la société");
                    String status = "non-retraite";

                    Company company = new Company(companyName);
                    CompanyDAO companyDAO = new CompanyDAO();
                    int companyId = companyDAO.getCompanyId(company);
                    if(companyId == 0){
                        JOptionPane.showMessageDialog(null,"Société introuvable","error",JOptionPane.ERROR_MESSAGE);
                    }else {
                        Employee employee = new Employee(matricule, name, bornDate, status, companyId);
                        EmployeeDAO employeeDAO = new EmployeeDAO();
                        boolean checker = employeeDAO.createEmployee(employee);

                        if (checker == false) {
                            JOptionPane.showMessageDialog(null, "Une erreur est surevenu !");
                        }
                    }
                break;
            }
            case 3 : {
                break;
            }
            case 4 : {
                break;
            }
            case 5 : {
                break;
            }
            case 6 : {
                break;
            }
            case 7 : {
                break;
            }
            default: JOptionPane.showMessageDialog(null,"Choix invalide","error",JOptionPane.ERROR_MESSAGE);
        }
    }
}
