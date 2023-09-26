package org.cnss.Dao;

import org.cnss.Classes.LaboratoryDoc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LaboratoryDocDAO implements DocumentDAO<LaboratoryDoc>{
    private final Connection connection;

    public LaboratoryDocDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public ResultSet getAllDocuments() {
        try {
            String query = "SELECT * FROM laboratory_docs";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println(resultSet);
            return resultSet;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean addDocument(LaboratoryDoc doc) {
        try {
            int checker = 0;
            String query = "INSERT INTO laboratory_docs (code , laboratory, description, category, payed_amount, reimbursement_rate) VALUES (?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,doc.getCode());
            preparedStatement.setString(2,doc.getLaboratory());
            preparedStatement.setString(3, String.valueOf(doc.getDescription()));
            preparedStatement.setString(4, String.valueOf(doc.getCategory()));
            preparedStatement.setInt(5,doc.getPayedAmount());
            preparedStatement.setInt(6,doc.getReimbursementRate());
            checker = preparedStatement.executeUpdate();

            if (checker>0){
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateDocument(LaboratoryDoc doc) {
        return false;
    }
}
