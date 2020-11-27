package JavaSwingGUI;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Customers {
	
	private String lastName;
	private String firstName;
	private LocalDate DoB;
	private String address;
	private String phoneNum;
	private String email;
	private String paymentInfo;
	private boolean active;
	private double salesTaxPercentage;
	
	public Customers() {
		lastName = "";
		firstName = "";
		DoB = null;
		address = "";
		phoneNum = "";
		email = "";
		paymentInfo = "";
		active = false;
		salesTaxPercentage = 0;
	}
	
	public Customers(String lName, String fName, String birthday, String homeAddress, String phoneNumber,
						String emailAddress, String payment, boolean active, double salesTaxPercentage) {
		lastName = lName;
		firstName = fName;
		setDateOfBirth(birthday);
		address = homeAddress;
		phoneNum = phoneNumber;
		email = emailAddress;
		paymentInfo = payment;
		this.active = active;
		this.salesTaxPercentage = salesTaxPercentage;
	}
	
	private String getCustomerName() {
		return firstName + " " + lastName;
	}
	
	private String getCustomerAddress() {
		
		return address;
	}
	
	private String getCustomerPhoneNumber() {
		
		return phoneNum;
	}
	
	private String getCustomerEmail() {
		
		return email;
	}
	
	private String getCustomerPaymentInfo() {
		
		return paymentInfo;
	}
	
	private LocalDate getDoB() {
		
		return DoB;
	}
	
	private Customers getCustomer() {
		return this;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public boolean isActive() {
		return active;
	}

	public double getSalesTaxPercentage() {
		return salesTaxPercentage;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setDoB(LocalDate doB) {
		DoB = doB;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public void setPaymentInfo(String paymentInfo) {
		this.paymentInfo = paymentInfo;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public void setSalesTaxPercentage(double salesTaxPercentage) {
		this.salesTaxPercentage = salesTaxPercentage;
	}

	public void setDateOfBirth(LocalDate DoB) {
		this.DoB = DoB;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.DoB = LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}

	private void setCustomer(String lName, String fName, String birthday, String homeAddress, String phoneNumber,
						   String emailAddress, String payment, boolean active, double salesTaxPercentage) {
		lastName = lName;
		firstName = fName;
		setDateOfBirth(birthday);
		address = homeAddress;
		phoneNum = phoneNumber;
		email = emailAddress;
		paymentInfo = payment;
		this.active = active;
		this.salesTaxPercentage = salesTaxPercentage;
	}
}
