package it21966;

/**
 * A Class that stores customer's personal information
 * 
 * @author it21966
 *
 */
public class Customer {
	private String vat;
	private String address;
	private String id;
	private String status;
	private String email;
	private double discount;
	
	public Customer() {
		this.vat = "";
		this.address = "";
		this.id = "";
		this.status = "";
		this.email = "";
		this.discount = 0;
	}

	public Customer(String vat, String address, String id, String status, String email, double discount) {
		this.vat = vat;
		this.address = address;
		this.id = id;
		this.status = status;
		this.email = email;
		this.discount = discount;
	}
	
	public String getVat() {
		return this.vat;
	}

	public void setVat(String vat) {
		this.vat = vat;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public double getDiscount() {
		return discount;
	}
	
	public void setDiscount(double discount) {
		if(discount > 0.45) {
			this.discount = 0.45;
			return;
		}
		this.discount = discount;
	}
	
	/**
	 * Print status options.
	 * 
	 */
	public void statusMenu() {
		System.out.println();
		System.out.println("1. Private sector");
		System.out.println("2. Student");
		System.out.println("3. Licensed professional");
	}
	
	/**
	 * Set status depending on the choice.
	 * 
	 * @param choice
	 */
	public void selectStatus(int choice) {
		if(choice == 1) {
			setStatus("Private sector");
		}
		if(choice == 2) {
			setStatus("Student");
		}
		if(choice == 3) {
			setStatus("Licensed professional");
		}
	}
	
	/**
	 * Check if the VAT is a 9 digit String.
	 * 
	 * @param vat
	 * @return
	 */
	public boolean isVat(String vat) {
		return vat.matches("^[0-9]{9}$");
	}
	
	/**
	 * Check if the ID has 2 letters and 6 digits.
	 * 
	 * @param id
	 * @return
	 */
	public boolean isId(String id) {
		return id.matches("^[a-zA-Z]{2}[0-9]{6}$");
	}
	
	@Override
	public String toString() {
		return "\nVAT number: " + getVat() + "\nAddress: " + getAddress() + "\nID: " + getId() 
				+ "\nStatus: " + getStatus() + "\nEmail: " + getEmail() + "\n";
	}
	
}
