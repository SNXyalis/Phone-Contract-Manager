package it21966;

import java.time.LocalDate;

/**
 * A Class that stores the details about the Internet speed
 * and the DSL type of the Contract.
 * Superclass: Contract
 * 
 * @author it21966
 *
 */
public class LandlineContract extends Contract{
	private int landlinceInternetSpeed;
	private String landlineDSLtype;
	
	public LandlineContract() {
		super();
		this.landlinceInternetSpeed = 0;
		this.landlineDSLtype = "";
	}
	
	public LandlineContract(int contractKey, String contractPhoneNumber, String vat, int contractCustomerKey,
			int contractMinutes, LocalDate contractStartingDate, int contractDuration, double contractCost,
			boolean contractIsEBill, String contractPaymentPreference , int landlinceInternetSpeed, String landlineDSLtype) {
		super(contractKey, contractPhoneNumber, vat, contractCustomerKey, contractMinutes, contractStartingDate,
				contractDuration, contractCost, contractIsEBill, contractPaymentPreference);
		this.landlinceInternetSpeed = landlinceInternetSpeed;
		this.landlineDSLtype = landlineDSLtype;
	}
	
	public int getLandlinceInternetSpeed() {
		return landlinceInternetSpeed;
	}
	public void setLandlinceInternetSpeed(int landlinceInternetSpeed) {
		this.landlinceInternetSpeed = landlinceInternetSpeed;
	}
	public String getLandlineDSLtype() {
		return landlineDSLtype;
	}
	public void setLandlineDSLtype(String landlineDSLtype) {
		this.landlineDSLtype = landlineDSLtype;
	}
	
	/**
	 * Set minutes, Internet speed and DSL type depending on this bundle.
	 */
	public void landlineWithData24Bundle() {
		setContractMinutes(2500);
		setLandlinceInternetSpeed(24);
		setLandlineDSLtype("ADSL");
		setContractCost(18.95);
	}
	
	/**
	 * Prints landlineWithData24Bundle details
	 */
	private void landlineWithData24BundleDetails() {
		System.out.println("Details: ");
		System.out.println("2500 minutes\t24 Mbps / ADSL");
		System.out.println("Cost: 18.95$ / month");
	}
	
	/**
	 * Set minutes, Internet speed and DSL type depending on this bundle.
	 */
	public void landlineWithData50Bundle() {
		setContractMinutes(2500);
		setLandlinceInternetSpeed(50);
		setLandlineDSLtype("VDSL");
		setContractCost(25.95);
	}
	
	/**
	 * Prints landlineWithData50Bundle details
	 */
	private void landlineWithData50BundleDetails() {
		System.out.println("Details: ");
		System.out.println("2500 minutes\t50 Mbps / VDSL");
		System.out.println("Cost: 25.95$ / month");
	}
	
	/**
	 * Set minutes, Internet speed and DSL type depending on this bundle.
	 */
	public void landlineOnlyCallBundle() {
		setContractMinutes(4000);
		setLandlinceInternetSpeed(0);
		setLandlineDSLtype("None");
		setContractCost(17.95);
	}
	
	/**
	 * Prints landlineOnlyCallBundle details
	 */
	private void landlineOnlyCallBundleDetails() {
		System.out.println("Details: ");
		System.out.println("4000 minutes");
		System.out.println("Cost: 17.95$ / month");
	}
	
	/**
	 * Set minutes, Internet speed and DSL type depending on this bundle.
	 */
	public void landlineOnlyCallBusinessBundle() {
		setContractMinutes(10000);
		setLandlinceInternetSpeed(0);
		setLandlineDSLtype("None");
		setContractCost(27.95);
	}
	
	/**
	 * Prints landlineOnlyCallBusinessBundle details
	 */
	private void landlineOnlyCallBusinessBundleDetails() {
		System.out.println("Details: ");
		System.out.println("10000 minutes");
		System.out.println("Cost: 27.95$ / month");
	}
	
	/**
	 * Set minutes, Internet speed and DSL type depending on this bundle.
	 */
	public void landlinePersonalBundle(int minutes, int speed) {
		setContractMinutes(minutes);
		double cost = minutes * 0.02;
		setLandlinceInternetSpeed(speed);
		if(speed == 24) {
			setLandlineDSLtype("ADSL");
			cost += 10;
		}else if(speed == 50) {
			setLandlineDSLtype("VDSL");
			cost += 20;
		}else {
			setLandlineDSLtype("None");
		}
		setContractCost(cost);
	}
	
	/**
	 * Prints landlinePersonalBundle details
	 */
	private void landlinePersonalBundleDetails() {
		System.out.println("Create your own bundle!");
		System.out.println("Cost: (0.02$ * min) / month");
		System.out.println("24 Mbps / ADSL: 10$");
		System.out.println("50 Mbps / VDSL: 20$");
	}
	
	/**
	 * Print bundles and their details
	 * 
	 */
	public void printLandlineBundles() {
		System.out.println("\nChoose one of the available bundles: \n");
		System.out.println();
		System.out.println("\n1. landlineWithData24Bundle");
		landlineWithData24BundleDetails();
		System.out.println("\n2. landlineWithData50Bundle");
		landlineWithData50BundleDetails();
		System.out.println("\n3. landlineOnlyCallBundle");
		landlineOnlyCallBundleDetails();
		System.out.println("\n4. landlineOnlyCallBusinessBundle");
		landlineOnlyCallBusinessBundleDetails();
		System.out.println("\n5. landlinePersonalBundle");
		landlinePersonalBundleDetails();
	}
	
	@Override
	public int whichChild() { return 2; }
	
	@Override
	public String toString() {
		return "\nContract Key: " + getContractKey() + "\tPhone Number: " + getContractPhoneNumber() + "\tVAT: " + getVat()
				+ "\nCustomer Key: " + getContractCustomerKey() + "\tMinutes: " + getContractMinutes()
				+ "\tStarting Date: " + getContractStartingDate() + "\nDuration(Months): " + getContractDuration()
				+ "\tCost: " + getContractCost() + "$" + "\tE-bill: " + isContractIsEBill()
				+ "\nPayment Preference: " + getContractPaymentPreference() + "\tInternet speed: " + getLandlinceInternetSpeed() 
				+ "\tDSL type: " + getLandlineDSLtype() +"\n";
	}
}
