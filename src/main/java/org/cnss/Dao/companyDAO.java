package org.cnss.Dao;

import org.cnss.Classes.Company;
import org.cnss.JDBC.DatabaseConnection;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class companyDAO {
    private final Connection connection;
    public companyDAO() {
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

    public boolean updateCompany(Company company){
        try {
            String query = "UPDATE `company`" +
                            "SET `name` = ?" +
                            "WHERE `id` = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,company.getName());
            preparedStatement.setInt(2,company.getId());
            int row = preparedStatement.executeUpdate();
            if (row>0){
                JOptionPane.showMessageDialog(null,"Nom mis à jour !");
            }else {
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

    public boolean deleteCompany(int id){
        try {
            String query = "DELETE FROM `company` WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            int row = preparedStatement.executeUpdate();
            if (row>0){
                JOptionPane.showMessageDialog(null,"Nom mis à jour !");
            }else {
                JOptionPane.showMessageDialog(null,"Une erreur est survenue !","error",JOptionPane.ERROR_MESSAGE);
            }
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
