package org.cnss.Classes;

public class RetraitSalary {
    private String employeeMatricule;
    private float retraitSalary;

    public RetraitSalary(String employeeMatricule, float retraitSalary) {
        this.employeeMatricule = employeeMatricule;
        this.retraitSalary = retraitSalary;
    }

    public String getEmployeeMatricule() {
        return employeeMatricule;
    }

    public void setEmployeeMatricule(String employeeMatricule) {
        this.employeeMatricule = employeeMatricule;
    }

    public float getRetraitSalary() {
        return retraitSalary;
    }

    public void setRetraitSalary(float retraitSalary) {
        this.retraitSalary = retraitSalary;
    }
}
