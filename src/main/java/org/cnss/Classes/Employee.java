package org.cnss.Classes;


public class Employee {
    private String matricule;
    private String status;
    private String employeeUserName;
    private String born_date;
    private int companyID;

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmployeeUserName() {
        return employeeUserName;
    }

    public void setEmployeeUserName(String employeeUserName) {
        this.employeeUserName = employeeUserName;
    }

    public int getCompanyID() {
        return companyID;
    }

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }

    public String getBorn_date() {
        return born_date;
    }

    public void setBorn_date(String born_date) {
        this.born_date = born_date;
    }

    public Employee(String matricule, String employeeUserName, String born_date,String status, int companyID) {
        this.matricule = matricule;
        this.status = status;
        this.employeeUserName = employeeUserName;
        this.born_date = born_date;
        this.companyID = companyID;
    }
}
