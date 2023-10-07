package org.cnss.Dao;

import org.cnss.Classes.BaseSalary;
import org.cnss.JDBC.DatabaseConnection;

import javax.swing.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BaseSalaryDAO {
    public final Connection connection;
    public BaseSalaryDAO(){
        connection = DatabaseConnection.getConnection();
    }
    public boolean fixSalary(String matricule,float salary){
        try {
            String query = "UPDATE `base_salary`" +
                            "SET `salary` = ?" +
                            "WHERE employee_matricule = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setFloat(1,salary);
            preparedStatement.setString(2,matricule);
            int row = preparedStatement.executeUpdate();
            if (row>0){
                JOptionPane.showMessageDialog(null,"Salaire corrigé !");
            }else{
                JOptionPane.showMessageDialog(null,"Une erreur est survenue !","error",JOptionPane.ERROR_MESSAGE);
            }
            return true;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public boolean EndOldSalary(BaseSalary baseSalary){
        try {
            String query = "UPDATE `base_salary`" +
                            "SET ending_date = ?" +
                            "WHERE employee_matricule = ? AND `salary` = ?,";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDate(1, (Date) baseSalary.getEnding_date());
            preparedStatement.setString(2, baseSalary.getEmployeeMatricule());
            preparedStatement.setFloat(3,baseSalary.getSalary());
            int row = preparedStatement.executeUpdate();
            if (row>0){
                JOptionPane.showMessageDialog(null,"Fin de salaire ancien !");
            }else{
                JOptionPane.showMessageDialog(null,"Une erreur est survenue !","error",JOptionPane.ERROR_MESSAGE);
            }
            return true;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public boolean declareSalary(BaseSalary baseSalary){
        try {
            String query = "INSERT INTO `base_salary` (employee_matricule , salary, starting_date) VALUES (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, baseSalary.getEmployeeMatricule());
            preparedStatement.setFloat(2, baseSalary.getSalary());
            preparedStatement.setDate(3,(Date) baseSalary.getStarting_date());
            int checker = preparedStatement.executeUpdate();
            if (checker>0){
                JOptionPane.showMessageDialog(null,"Salaire declaré !");
            }else{
                JOptionPane.showMessageDialog(null,"Une erreur est survenue !","error",JOptionPane.ERROR_MESSAGE);
            }
            return true;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    public float last95Salaries(String matricule) {
        String sql = "SELECT salary FROM (SELECT salary FROM base_salary WHERE Employee = ? ORDER BY id DESC LIMIT 95) AS lastSalaries";
        double totalMontant = 0.0;
        int nombreSalaires = 0;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, matricule);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                float salary = resultSet.getFloat("salary");
                totalMontant += salary;
                nombreSalaires++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (nombreSalaires > 0) {
            return (float) (totalMontant / nombreSalaires);
        } else {
            return 0;
        }
    }
}
