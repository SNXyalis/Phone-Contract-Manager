package it21966;

import java.time.LocalDate;
import java.util.Random;

/**
 * A Class that stores the details of the Contract.
 * Subclasses: MobilePhoneContract, LandlineContract
 * 
 * @author it21966
 *
 */
public class Contract {
	private int contractKey;
	private String contractPhoneNumber;
	private String vat;
	private int contractCustomerKey;
	private int contractMinutes;
	private LocalDate contractStartingDate;
	private int contractDuration;
	private double contractCost;
	private boolean contractIsEBill;
	private String contractPaymentPreference;
	
	public Contract() {
		Random rng = new Random();
		
		this.contractKey = rng.nextInt(10000);;
		this.contractPhoneNumber = "";
		this.vat = "";
		this.contractCustomerKey = rng.nextInt(10000);
		this.contractMinutes = 0;
		this.contractStartingDate = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth());
		this.contractDuration = 0;
		this.contractCost = 0;
		this.contractIsEBill = false;
		this.contractPaymentPreference = "";
	}
	
	public Contract(int contractKey, String contractPhoneNumber, String vat, int contractCustomerKey,
			int contractMinutes, LocalDate contractStartingDate, int contractDuration, double contractCost,
			boolean contractIsEBill, String contractPaymentPreference) {
		
		this.contractKey = contractKey;
		this.contractPhoneNumber = contractPhoneNumber;
		this.vat = vat;
		this.contractCustomerKey = contractCustomerKey;
		this.contractMinutes = contractMinutes;
		this.contractStartingDate = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth());
		this.contractDuration = contractDuration;
		this.contractCost = contractCost;
		this.contractIsEBill = contractIsEBill;
		this.contractPaymentPreference = contractPaymentPreference;
	}
	
	public int getContractKey() {
		return contractKey;
	}


	public void setContractKey(int contractKey) {
		this.contractKey = contractKey;
	}


	public String getContractPhoneNumber() {
		return contractPhoneNumber;
	}


	public void setContractPhoneNumber(String contractPhoneNumber) {
		this.contractPhoneNumber = contractPhoneNumber;
	}


	public String getVat() {
		return vat;
	}


	public void setVat(String vat) {
		this.vat = vat;
	}


	public int getContractCustomerKey() {
		return contractCustomerKey;
	}


	public void setContractCustomerKey(int contractCustomerKey) {
		this.contractCustomerKey = contractCustomerKey;
	}


	public int getContractMinutes() {
		return contractMinutes;
	}


	public void setContractMinutes(int contractMinutes) {
		this.contractMinutes = contractMinutes;
	}


	public LocalDate getContractStartingDate() {
		return contractStartingDate;
	}


	public void setContractStartingDate(int year, int month, int dayOfMonth) {
		this.contractStartingDate = LocalDate.of(year, month, dayOfMonth);
	}


	public int getContractDuration() {
		return contractDuration;
	}


	public void setContractDuration(int contractDuration) {
		this.contractDuration = contractDuration;
	}


	public double getContractCost() {
		return contractCost;
	}


	public void setContractCost(double contractCost) {
		this.contractCost = contractCost;
	}


	public boolean isContractIsEBill() {
		return contractIsEBill;
	}


	public void setContractIsEBill(boolean contractIsEBill) {
		this.contractIsEBill = contractIsEBill;
	}


	public String getContractPaymentPreference() {
		return contractPaymentPreference;
	}


	public void setContractPaymentPreference(String contractPaymentPreference) {
		this.contractPaymentPreference = contractPaymentPreference;
	}
	
	/**
	 * Check if the phoneNumber consists of 10 digits and if it starts 
	 * with a digit that is not 6.  
	 * 
	 * @param phoneNumber
	 * @return true if the number is eligible to be a landline number
	 */
	public boolean isLandline(String phoneNumber) {
		return phoneNumber.matches("^[0-57-9][0-9]{9}$");
	}
	
	/**
	 * Check if the phoneNumber consists of 10 digits and if it starts 
	 * with a digit that is not 2.  
	 * 
	 * @param phoneNumber
	 * @return true if the number is eligible to be a phone number
	 */
	public boolean isMobilePhone(String phoneNumber) {
		return phoneNumber.matches("^[0-13-9][0-9]{9}$");
	}
	
	/**
	 * Prints COntract options.
	 */
	public void printContractOptions() {
		System.out.println("\nChoose a type of contract: ");
		System.out.println();
		System.out.println("1. Mobile phone contract");
		System.out.println("2. Home phone contract");
	}
	
	/**
	 * Print contract's duration options.
	 */
	public void printDurationOptions() {
		System.out.println("1. 12 months");
		System.out.println("2. 24 months");
	}
	
	/**
	 * Print payment preference options.
	 */
	public void printPaymentPreference() {
		System.out.println("Select payment method: ");
		System.out.println();
		System.out.println("1. Credit card");
		System.out.println("2. Cash");
		System.out.println("3. Standing order");
	}
	
	/**
	 * Set the duration depending on the choice.
	 * 
	 * @param choice
	 */
	public void selectDuration(int choice) {
		if(choice == 1) setContractDuration(12);
		if(choice == 2) setContractDuration(24);
	}
	
	/**
	 * Set the Ebill option depending on the choice.
	 * 
	 * @param choice
	 */
	public void selectEBill(int choice) {
		if(choice == 1) setContractIsEBill(true);
		if(choice == 2) setContractIsEBill(false);
	}
	
	/**
	 * Set the payment preference depending on the choice.
	 * 
	 * @param choice
	 */
	public void selectPaymentPreference(int choice) {
		if(choice == 1) setContractPaymentPreference("Credit Card");
		if(choice == 2) setContractPaymentPreference("Cash");
		if(choice == 3) setContractPaymentPreference("Standing order");
	}
	
	/**
	 * We use this to determine the type of contract.
	 * If it's a mobile contract, the overrided method will return 1,
	 * if it's a landline contract, the overrided method will return 2.
	 * It was used to get the correct Statistics.
	 * 
	 * @return
	 */
	public int whichChild() { return 0; }

	@Override
	public String toString() {
		return "\nContract Key: " + contractKey + "\tPhone Number: " + contractPhoneNumber + "\tVAT: " + vat
				+ "\nCustomer Key: " + contractCustomerKey + "\tMinutes: " + contractMinutes
				+ "\tStarting Date: " + contractStartingDate + "\nDuration:" + contractDuration
				+ "\tCost: " + contractCost + "\tE-bill: " + contractIsEBill
				+ "\nPayment Preference: " + contractPaymentPreference + "\n";
	}
}
