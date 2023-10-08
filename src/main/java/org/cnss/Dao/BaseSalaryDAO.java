package org.cnss.Dao;

import org.cnss.Classes.BaseSalary;
import org.cnss.JDBC.DatabaseConnection;

import javax.swing.*;
import java.sql.*;

public class BaseSalaryDAO {
    public final Connection connection;
    public BaseSalaryDAO(){
        connection = DatabaseConnection.getConnection();
    }
    public boolean fixSalary(String matricule,float salary) throws SQLException{
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
    }

    public boolean declareSalary(BaseSalary baseSalary) throws SQLException{
            String query = "INSERT INTO `base_salary` (employee_id , salary, working_days, date) VALUES (?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, baseSalary.getEmployeeId());
            preparedStatement.setFloat(2, baseSalary.getSalary());
            preparedStatement.setInt(3,baseSalary.getWorking_days());
            preparedStatement.setString(4,baseSalary.getDate());
            int checker = preparedStatement.executeUpdate();
            if (checker>0){
                JOptionPane.showMessageDialog(null,"Salaire declaré !");
            }else{
                JOptionPane.showMessageDialog(null,"Une erreur est survenue !","error",JOptionPane.ERROR_MESSAGE);
            }
            return true;
    }
    public float last95Salaries(int employee_id) {
        String sql = "SELECT salary FROM (SELECT salary FROM base_salary WHERE Employee_id = ? ORDER BY salary_id DESC LIMIT 95) AS lastSalaries";
        double totalMontant = 0.0;
        int nombreSalaires = 0;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, employee_id);
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

    public int workingDays(int employee_id) {
        String sql = "SELECT working_days FROM base_salary WHERE Employee_id = ? ORDER BY working_days DESC LIMIT 95;";
        int total = 0;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, employee_id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int totalDays = resultSet.getInt("working_days");
                total += totalDays;
            }
            System.out.println(total);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return total;
    }

}
