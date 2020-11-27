package JavaSwingGUI;

import java.util.Date;

public class Employee {

    private String employeeFirstName;
    private String employeeLastName;
    private Date employeeDoB;
    private String employeeAddress;
    private String employeeEmail;
    private String employeeSSID;
    private String employeePhoneNumber;
    private String employeeMethodOfPayment;
    private double employeeCommissionPercentage;

    public Employee() {
        employeeFirstName = "";
        employeeLastName = "";
        employeeDoB = new Date();
        employeeAddress = "";
        employeeEmail = "";
        employeeSSID = "";
        employeePhoneNumber = "";
        employeeMethodOfPayment = "";
        employeeCommissionPercentage = 0;
    }

    public Employee(String employeeFirstName, String employeeLastName, Date employeeDoB,
                    String employeeAddress, String employeeEmail, String employeeSSID, String employeePhoneNumber,
                    String employeeMethodOfPayment, double employeeCommissionPercentage) {
        this.employeeFirstName = employeeFirstName;
        this.employeeLastName = employeeLastName;
        this.employeeDoB = employeeDoB;
        this.employeeAddress = employeeAddress;
        this.employeeEmail = employeeEmail;
        this.employeeSSID = employeeSSID;
        this.employeePhoneNumber = employeePhoneNumber;
        this.employeeMethodOfPayment = employeeMethodOfPayment;
        this.employeeCommissionPercentage = employeeCommissionPercentage;
    }

    public Employee getEmployee() {
        return this;
    }

    public String getEmployeeFirstname() {
        return employeeFirstName;
    }

    public String getEmployeeLastname() {
        return employeeLastName;
    }

    public Date getEmployeeDoB() {
        return employeeDoB;
    }

    public String getEmployeeAddress() {
        return employeeAddress;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public String getEmployeeSSID() {
        return employeeSSID;
    }

    public String getEmployeePhoneNumber() {
        return employeePhoneNumber;
    }

    public double getEmployeeCommissionPercentage() {
        return employeeCommissionPercentage;
    }

    public String getEmployeeMethodOfPayment() {
        return employeeMethodOfPayment;
    }

    public void setEmployeeFirstName(String employeeFirstName) {
        this.employeeFirstName = employeeFirstName;
    }

    public void setEmployeeLastName(String employeeLastName) {
        this.employeeLastName = employeeLastName;
    }

    public void setEmployeeDoB(Date employeeDoB) {
        this.employeeDoB = employeeDoB;
    }

    public void setEmployeeAddress(String employeeAddress) {
        this.employeeAddress = employeeAddress;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public void setEmployeePhoneNumber(String employeePhoneNumber) {
        this.employeePhoneNumber = employeePhoneNumber;
    }

    public void setEmployeeSSID(String employeeSSID) {
        this.employeeSSID = employeeSSID;
    }

    public void setEmployeeCommissionPercentage(double employeeCommissionPercentage) {
        this.employeeCommissionPercentage = employeeCommissionPercentage;
    }

    public void setEmployeeMethodOfPayment(String employeeMethodOfPayment) {
        this.employeeMethodOfPayment = employeeMethodOfPayment;
    }

    public void setEmployee(String employeeFirstName, String employeeLastName, Date employeeDoB,
                    String employeeAddress, String employeeEmail, String employeeSSID, String employeePhoneNumber,
                    String employeeMethodOfPayment, double employeeCommissionPercentage) {
        this.employeeFirstName = employeeFirstName;
        this.employeeLastName = employeeLastName;
        this.employeeDoB = employeeDoB;
        this.employeeAddress = employeeAddress;
        this.employeeEmail = employeeEmail;
        this.employeeSSID = employeeSSID;
        this.employeePhoneNumber = employeePhoneNumber;
        this.employeeMethodOfPayment = employeeMethodOfPayment;
        this.employeeCommissionPercentage = employeeCommissionPercentage;
    }

}
