package org.cnss.Classes;

import java.sql.ResultSet;

public class Employee {
    private String employeeMatricule;
    private String status;
    private String employeeFirstname;
    private int workingDays;
    private int companyID;
    public boolean updateEmployee(String employeeMatricule){
        return false;
    }
    public boolean createEmployee(){
        return true;
    }
    public boolean deleteEmployee(){
        return true;
    }
    public ResultSet getEmployee(){
        return null;
    }


}
