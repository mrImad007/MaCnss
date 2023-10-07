package org.cnss.Dao;

import org.cnss.Classes.Employee;
import org.cnss.JDBC.DatabaseConnection;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDAO {
    public final Connection connection;
    public EmployeeDAO() {
        connection = DatabaseConnection.getConnection();
    }
    public boolean updateEmployee(Employee employee){
        try {
            String query = "UPDATE employee" +
                            "SET matricule = ?, username = ?, status = ?, born_date = ?, companyID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,employee.getMatricule());
            preparedStatement.setString(2, employee.getEmployeeUserName());
            preparedStatement.setString(3, employee.getStatus());
            preparedStatement.setString(4,employee.getBorn_date());
            preparedStatement.setInt(5,employee.getCompanyID());
            int row = preparedStatement.executeUpdate();
            if(row>0){
                JOptionPane.showMessageDialog(null,"Employé mis à jour!");
            }else{
                JOptionPane.showMessageDialog(null,"Une erreur est survenue","error", JOptionPane.ERROR_MESSAGE);
            }
            return true;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    public boolean createEmployee(Employee employee){
        try {
            String query = "INSERT INTO `employee` (matricule,username,born_date,companyID) VALUES (?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,employee.getMatricule());
            preparedStatement.setString(2, employee.getEmployeeUserName());
            preparedStatement.setString(3,employee.getBorn_date());
            preparedStatement.setInt(4,employee.getCompanyID());
            int row = preparedStatement.executeUpdate();
            if(row>0){
                JOptionPane.showMessageDialog(null,"Employé ajouté!");
            }else{
                JOptionPane.showMessageDialog(null,"Une erreur est survenue","error", JOptionPane.ERROR_MESSAGE);
            }
            return true;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    public boolean deleteEmployee(String matricule){
        try {
            String query = "DELETE FROM `employee` WHERE matricule = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,matricule);
            int row = preparedStatement.executeUpdate();
            if (row>0){
                JOptionPane.showMessageDialog(null,"employee suprimé");
            }else {
                JOptionPane.showMessageDialog(null,"Une erreur est survenue !","error",JOptionPane.ERROR_MESSAGE);
            }
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ResultSet getEmployee(String matricule){
        try {
            String query = "SELECT * FROM `employee` WHERE matricule = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,matricule);
            ResultSet result = preparedStatement.executeQuery();
            return result;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
