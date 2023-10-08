package org.cnss.Classes;

public class BaseSalary {
    private int employeeid;
    private float salary;
    private int working_days;

    private String date;

    public int getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(int employeeid) {
        this.employeeid = employeeid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getWorking_days() {
        return working_days;
    }

    public void setWorking_days(int working_days) {
        this.working_days = working_days;
    }

    public int getEmployeeId() {
        return employeeid;
    }

    public void setEmployeeId(int employeeid) {
        this.employeeid = employeeid;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }


    public BaseSalary(int employeeid, float salary,int working_days, String date ) {
        this.employeeid = employeeid;
        this.salary = salary;
        this.working_days = working_days;
        this.date = date;
    }

}
