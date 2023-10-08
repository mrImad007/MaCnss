package org.cnss;

import org.cnss.Classes.BaseSalary;
import org.cnss.Classes.Company;
import org.cnss.Classes.Employee;
import org.cnss.Dao.BaseSalaryDAO;
import org.cnss.Dao.CompanyDAO;
import org.cnss.Dao.EmployeeDAO;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class CompanyApp {
    public static void main() throws SQLException {
        while (true) {
            int choice = Integer.parseInt(JOptionPane.showInputDialog(null, "Bienvenue dans votre EspaceCompany\n" +
                    "1 : Ajouter une nouvelle societé\n" +
                    "2 : Ajouter un(e) employé(e)\n" +
                    "3 : Declaration mensuelle\n" +
                    "4 : Rubrique de la retraite \n" +
                    "5 : Quitter\n" +
                    "Choisissez une option:"));

            switch (choice) {
                case 1: {
                    String companyName = null;
                    while (companyName == null || companyName.trim().equals("")) {
                        companyName = JOptionPane.showInputDialog(null, "Entrer le nom de la societé à créer : ");
                    }
                    Company company = new Company(companyName);
                    CompanyDAO companyDAO = new CompanyDAO();

                    boolean checker = companyDAO.createCompany(company);

                    if (checker == false) {
                        JOptionPane.showMessageDialog(null, "Une erreur est survenue !", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                }
                case 2: {
                    String matricule = JOptionPane.showInputDialog(null, "Entrer un matricule pour cet employé");
                    String name = JOptionPane.showInputDialog(null, "Entrer le nom de l'employé");
                    String bornDate = JOptionPane.showInputDialog(null, "Entrer la date de naissance de l'employé : yyyy-mm-dd");
                    String companyName = JOptionPane.showInputDialog(null, "Entrer le nom de la société");
                    String status = "non-retraite";

                    Company company = new Company(companyName);
                    CompanyDAO companyDAO = new CompanyDAO();
                    int companyId = companyDAO.getCompanyId(company);
                    if (companyId == 0) {
                        JOptionPane.showMessageDialog(null, "Société introuvable", "Erreur", JOptionPane.ERROR_MESSAGE);
                    } else {
                        Employee employee = new Employee(matricule, name, bornDate, status, companyId);
                        EmployeeDAO employeeDAO = new EmployeeDAO();
                        boolean checker = employeeDAO.createEmployee(employee);

                        if (checker == false) {
                            JOptionPane.showMessageDialog(null, "Une erreur est survenue !", "Erreur", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    break;
                }
                case 3: {
                    int employee_id = 0;
                    String matricule = JOptionPane.showInputDialog(null, "Entrer le matricule de l'employé");
                    EmployeeDAO employeeDAO = new EmployeeDAO();
                    ResultSet resultSet = employeeDAO.getEmployee(matricule);

                    while (resultSet.next()) {
                        employee_id = resultSet.getInt("id");
                    }
                    if (employee_id > 0) {
                        int salary = Integer.parseInt(JOptionPane.showInputDialog(null, "Entrer le salaire du "));
                        int working_days = Integer.parseInt(JOptionPane.showInputDialog(null, "Entrer le nombre des jours travaillés"));
                        String date = JOptionPane.showInputDialog(null, "Entrer la date : yyyy-mm");

                        BaseSalary baseSalary = new BaseSalary(employee_id, salary, working_days, date);
                        BaseSalaryDAO baseSalaryDAO = new BaseSalaryDAO();
                        boolean checker = baseSalaryDAO.declareSalary(baseSalary);

                        if (checker == false) {
                            JOptionPane.showMessageDialog(null, "Une erreur est survenue !", "Erreur", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Employé introuvable !", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                }
                case 4: {
                    String matricule = JOptionPane.showInputDialog(null, "Entrer le matricule de l'employée:");
                    EmployeeDAO employeeDAO = new EmployeeDAO();
                    BaseSalaryDAO baseSalaryDAO = new BaseSalaryDAO();
                    ResultSet employee = employeeDAO.getEmployee(matricule);
                    int employee_id = 0;
                    String borndate = null;

                    while (employee.next()) {
                        employee_id = employee.getInt(1);
                        borndate = employee.getString("born_date");
                    }
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate birthDate = LocalDate.parse(borndate, formatter);

                    LocalDate currentDate = LocalDate.now();

                    Period age = Period.between(birthDate, currentDate);

                    int years = age.getYears();

                    if (employee_id == 0) {
                        JOptionPane.showMessageDialog(null, "Empoyé introuvable", "Erreur", JOptionPane.ERROR_MESSAGE);
                    } else {
                        if (years>55){
                            int totaldays = baseSalaryDAO.workingDays(employee_id);
                            if (totaldays>1320){
                                if (totaldays>3240){
                                    int extradays = totaldays - 3240;
                                    float percetage = 50;
                                    percetage += extradays/216;
                                    if (percetage>70){
                                        float total = baseSalaryDAO.last95Salaries(employee_id);
                                        total = (total*70)/100;
                                        if ((total) > 6000) {
                                            JOptionPane.showMessageDialog(null, "Le montant de votre retraite est : 6000 DH");
                                        } else if ((total) < 1000) {
                                            JOptionPane.showMessageDialog(null, "Le montant de votre retraite est : 1000 DH");
                                        } else {
                                            JOptionPane.showMessageDialog(null, "Le montant de votre retraite est :" + total + " DH");
                                        }
                                    }else {
                                        float total = baseSalaryDAO.last95Salaries(employee_id);
                                        total = (total*percetage)/100;
                                        if ((total) > 6000) {
                                            JOptionPane.showMessageDialog(null, "Le montant de votre retraite est : 6000 DH");
                                        } else if ((total) < 1000) {
                                            JOptionPane.showMessageDialog(null, "Le montant de votre retraite est : 1000 DH");
                                        } else {
                                            JOptionPane.showMessageDialog(null, "Le montant de votre retraite est :" + total + " DH");
                                        }
                                    }
                                }else {
                                    float total = baseSalaryDAO.last95Salaries(employee_id);
                                    if ((total/2) > 6000) {
                                        JOptionPane.showMessageDialog(null, "Le montant de votre retraite est : 6000 DH");
                                    } else if ((total/2) < 1000) {
                                        JOptionPane.showMessageDialog(null, "Le montant de votre retraite est : 1000 DH");
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Le montant de votre retraite est :" + total/2 + " DH");
                                    }
                                }
                            }else{
                                JOptionPane.showMessageDialog(null,"Cet employée n'a pas encore de droit de la retraite");
                            }
                        }else{
                            JOptionPane.showMessageDialog(null,"Cet employé n'a pas encore atteint 55 ans, il a : "+years+"ans");
                        }
                    }
                    break;
                }
                case 5: {
                    // Exit the program
                    System.exit(0);
                    break;
                }
                default:
                    JOptionPane.showMessageDialog(null, "Choix invalide", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
