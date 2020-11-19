import java.util
import java.util.Scanner;*;

public class Customers {
	
	private String lastName;
	private String firstName;
	private Date DoB;
	private String address;
	private String phoneNum;
	private String email;
	private String paymentInfo;
	
	public Customer() {
		
		lastName = "";
		firstName = "";
		DoB = new Date();
		address = "";
		phoneNum = "";
		email = "";
		paymentInfo = "";
	}
	
	public Customer(String lName, String fName, Date birthday, String homeAddress, String phoneNumber, 
						String emailAddress, String payment, ) {
		
		lastName = lName;
		firstName = fName;
		DoB = birthday;
		address = homeAddress;
		phoneNum = phoneNumber;
		email = emailAdress;
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
		
		return birthday;
	}
	
	private getCustomer() {
		
		return this;
	}
	
	private setCustomer() {
		
		Scanner in = new Scanner(System.in);
		System.out.print("Enter last name: ");
		String lname = in.next();
		
		System.out.print("Enter first name: ");
		String fname = in.next();
		
		System.out.print("Enter date of birth: ");
		System.out.print("Year: ");
		String y = in.nextInt();
		System.out.print("Month: ");
		String m = in.nextInt();
		System.out.print("Day: ");
		String d = in.nextInt();
		Date birthday = new Date(y, m , d)
		
		System.out.print("Enter shipping/home address: ");
		String homeAddress = in.next();
		
		System.out.print("Enter phone number: ");
		String phoneNumber = in.next();
		
		System.out.print("Enter email address: ");
		String emailAddress = in.next();
		
		System.out.print("Enter payment information: ");
		String payment = in.next();
		
		Customer c = new Customer(lname, fname, birthday, homeAddress, phoneNumber, emailAddress, payment);
	}
}
