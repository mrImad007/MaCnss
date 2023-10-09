package org.cnss.Dao;

import org.cnss.Classes.Employee;
import org.cnss.JDBC.DatabaseConnection;

import javax.swing.*;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class EmployeeDAO {
    public final Connection connection;
    public EmployeeDAO() {
        connection = DatabaseConnection.getConnection();
    }
    public boolean updateEmployee(Employee employee) throws SQLException{
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
    }
    public boolean createEmployee(Employee employee) throws SQLException {

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
    }
    public ResultSet getEmployee(String matricule) throws SQLException{
            String query = "SELECT * FROM `employee` WHERE matricule = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,matricule);
            ResultSet result = preparedStatement.executeQuery();
            return result;
    }

    public Map<String, Object> getEmploys(String matricule) throws SQLException {
        String query = "SELECT * FROM `employee` WHERE matricule = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, matricule);
        ResultSet resultSet = preparedStatement.executeQuery();

        Map<String, Object> employeeData = new HashMap<>();

        if (resultSet.next()) {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i);
                Object columnValue = resultSet.getObject(i);
                employeeData.put(columnName, columnValue);
            }
        }

        resultSet.close();
        return employeeData;
    }

}
