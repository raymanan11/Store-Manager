package JavaSwingGUI;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Customers {
	
	private String customerName;
	private LocalDate DoB;
	private String address;
	private String phoneNum;
	private String email;
	private String paymentInfo;
	private boolean active;
	private double salesTaxPercentage;
	
	public Customers() {
		customerName = "";
		DoB = null;
		address = "";
		phoneNum = "";
		email = "";
		paymentInfo = "";
		active = false;
		salesTaxPercentage = 0;
	}
	
	public Customers(String customerName, String birthday, String homeAddress, String phoneNumber,
						String emailAddress, String payment, boolean active, double salesTaxPercentage) {
		this.customerName = customerName;
		setDateOfBirth(birthday);
		address = homeAddress;
		phoneNum = phoneNumber;
		email = emailAddress;
		paymentInfo = payment;
		this.active = active;
		this.salesTaxPercentage = salesTaxPercentage;
	}
	
	public String getCustomerName() {
		return customerName;
	}

	public String getCustomerAddress() {
		
		return address;
	}

	public String getCustomerPhoneNumber() {
		
		return phoneNum;
	}

	public String getCustomerEmail() {
		
		return email;
	}

	public String getCustomerPaymentInfo() {
		
		return paymentInfo;
	}

	public LocalDate getDoB() {
		
		return DoB;
	}

	public Customers getCustomer() {
		return this;
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

	public void setAddress(String address) {
		this.address = address;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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
		this.DoB = LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
	}

	public void setCustomer(String customerName, String birthday, String homeAddress, String phoneNumber,
						   String emailAddress, String payment, boolean active, double salesTaxPercentage) {
		this.customerName = customerName;
		setDateOfBirth(birthday);
		address = homeAddress;
		phoneNum = phoneNumber;
		email = emailAddress;
		paymentInfo = payment;
		this.active = active;
		this.salesTaxPercentage = salesTaxPercentage;
	}
}
