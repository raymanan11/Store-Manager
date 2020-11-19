import java.util.*;
import java.util.Scanner;

public class Customers {
	
	private String lastName;
	private String firstName;
	private Date DoB;
	private String address;
	private String phoneNum;
	private String email;
	private String paymentInfo;
	
	public Customers() {
		
		lastName = "";
		firstName = "";
		DoB = new Date();
		address = "";
		phoneNum = "";
		email = "";
		paymentInfo = "";
	}
	
	public Customers(String lName, String fName, Date birthday, String homeAddress, String phoneNumber,
						String emailAddress, String payment) {
		lastName = lName;
		firstName = fName;
		DoB = birthday;
		address = homeAddress;
		phoneNum = phoneNumber;
		email = emailAddress;
		paymentInfo = payment;
	}
	
	private String getCustomerName() {
		
		String name = firstName + " " + lastName;
		return name;
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
	
	private Date getDoB() {
		
		return DoB;
	}
	
	private Customers getCustomer() {
		return this;
	}
	
	private void setCustomer() {
		
		Scanner in = new Scanner(System.in);
		System.out.print("Enter last name: ");
		String lname = in.next();
		
		System.out.print("Enter first name: ");
		String fname = in.next();
		
		System.out.print("Enter date of birth: ");
		System.out.print("Year: ");
		int y = in.nextInt();
		System.out.print("Month: ");
		int m = in.nextInt();
		System.out.print("Day: ");
		int d = in.nextInt();
		Date birthday = new Date(y, m , d);
		
		System.out.print("Enter shipping/home address: ");
		String homeAddress = in.next();
		
		System.out.print("Enter phone number: ");
		String phoneNumber = in.next();
		
		System.out.print("Enter email address: ");
		String emailAddress = in.next();
		
		System.out.print("Enter payment information: ");
		String payment = in.next();
		
		Customers c = new Customers(lname, fname, birthday, homeAddress, phoneNumber, emailAddress, payment);
	}
}
