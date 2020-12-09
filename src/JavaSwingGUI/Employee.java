package JavaSwingGUI;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Employee {

    private String employeeName;
    private LocalDate employeeDoB;
    private String employeeAddress;
    private String employeeEmail;
    private String employeeSSID;
    private String employeePhoneNumber;
    private String employeeMethodOfPayment;
    private double employeeTotalSales;
    private double employeeCommissionPercentage;

    public Employee() {
        employeeName = "";
        employeeDoB = null;
        employeeAddress = "";
        employeeEmail = "";
        employeeSSID = "";
        employeePhoneNumber = "";
        employeeMethodOfPayment = "";
        employeeTotalSales = 0;
        employeeCommissionPercentage = 0;
    }

    public Employee(String employeeName, String employeeDoB,
                    String employeeAddress, String employeeEmail, String employeeSSID, String employeePhoneNumber,
                    String employeeMethodOfPayment, double employeeCommissionPercentage) {
        this.employeeName = employeeName;
        setDateOfBirth(employeeDoB);
        this.employeeAddress = employeeAddress;
        this.employeeEmail = employeeEmail;
        this.employeeSSID = employeeSSID;
        this.employeePhoneNumber = employeePhoneNumber;
        this.employeeMethodOfPayment = employeeMethodOfPayment;
        this.employeeTotalSales = 0;
        this.employeeCommissionPercentage = employeeCommissionPercentage;
    }

    public Employee getEmployee() {
        return this;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public LocalDate getEmployeeDoB() {
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

    public double getEmployeeTotalSales() {
        return employeeTotalSales;
    }

    public double getEmployeeCommissionPercentage() {
        return employeeCommissionPercentage;
    }

    public String getEmployeeMethodOfPayment() {
        return employeeMethodOfPayment;
    }

    public void setEmployeeName(String employeeFirstName) {
        this.employeeName = employeeFirstName;
    }

    public void setEmployeeDoB(LocalDate employeeDoB) {
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

    public void setDateOfBirth(String dateOfBirth) {
        this.employeeDoB = LocalDate.parse(dateOfBirth,DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    }

    public void setEmployee(String employeeName, String employeeDoB,
                    String employeeAddress, String employeeEmail, String employeeSSID, String employeePhoneNumber,
                    String employeeMethodOfPayment, double employeeCommissionPercentage) {
        this.employeeName = employeeName;
        setDateOfBirth(employeeDoB);
        this.employeeAddress = employeeAddress;
        this.employeeEmail = employeeEmail;
        this.employeeSSID = employeeSSID;
        this.employeePhoneNumber = employeePhoneNumber;
        this.employeeMethodOfPayment = employeeMethodOfPayment;
        this.employeeTotalSales = 0;
        this.employeeCommissionPercentage = employeeCommissionPercentage;
    }

}
