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
    public boolean declareSalary(BaseSalary baseSalary) {
        String query = "INSERT INTO `base_salary` (employee_id, salary, working_days, date) VALUES (?,?,?,?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, baseSalary.getEmployeeId());
            preparedStatement.setFloat(2, baseSalary.getSalary());
            preparedStatement.setInt(3, baseSalary.getWorking_days());
            preparedStatement.setString(4, baseSalary.getDate());

            int checker = preparedStatement.executeUpdate();

            if (checker > 0) {
                JOptionPane.showMessageDialog(null, "Salaire déclaré !");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Une erreur est survenue !", "Erreur", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (SQLException e) {
            // Handle the SQL exception, log it, and provide detailed error information if needed
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erreur lors de la déclaration du salaire : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public float retirementSalary(int employee_id) {
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
