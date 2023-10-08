package org.cnss.Dao;

import org.cnss.Classes.Company;
import org.cnss.JDBC.DatabaseConnection;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class CompanyDAO {
    private final Connection connection;
    public CompanyDAO() {
        connection = DatabaseConnection.getConnection();
    }

    public  boolean createCompany(Company company){
        try {
            String query = "INSERT INTO `company` (name) VALUES (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,company.getName());
            int row = preparedStatement.executeUpdate();
            if (row>0){
                JOptionPane.showMessageDialog(null,"Entreprise bien ajouté !");
            }else{
                JOptionPane.showMessageDialog(null,"Une erreur est survenue !","error",JOptionPane.ERROR_MESSAGE);
            }
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getCompanyId(Company company){
        try {
            int id = 0;
            String query = "SELECT company.id FROM `company` WHERE name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,company.getName());
            ResultSet result = preparedStatement.executeQuery();
            while(result.next()){
                id = result.getInt("id");
            }
            return id;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
