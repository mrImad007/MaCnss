package org.cnss.Classes;

import java.util.Date;

public class BaseSalary {
    private String employeeMatricule;
    private float salary;
    private int working_days;

    public int getWorking_days() {
        return working_days;
    }

    public void setWorking_days(int working_days) {
        this.working_days = working_days;
    }

    private Date starting_date;
    private Date ending_date;

    public String getEmployeeMatricule() {
        return employeeMatricule;
    }

    public void setEmployeeMatricule(String employeeMatricule) {
        this.employeeMatricule = employeeMatricule;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public Date getStarting_date() {
        return starting_date;
    }

    public void setStarting_date(Date starting_date) {
        this.starting_date = starting_date;
    }

    public Date getEnding_date() {
        return ending_date;
    }

    public void setEnding_date(Date ending_date) {
        this.ending_date = ending_date;
    }

    public BaseSalary(String employeeMatricule, float salary,int working_days ,Date starting_date, Date ending_date) {
        this.employeeMatricule = employeeMatricule;
        this.salary = salary;
        this.working_days = working_days;
        this.starting_date = starting_date;
        this.ending_date = ending_date;
    }

}
