package it21966;

import java.time.LocalDate;

/**
 * A Class that stores the details about the WiFi data
 * and the SMS of the Contract.
 * Superclass: Contract
 * 
 * @author it21966
 *
 */
public class MobilePhoneContract extends Contract{
	private int phoneContractWiFiData;
	private int	phoneContractSMS;
	
	public MobilePhoneContract() {
		super();
		this.phoneContractWiFiData = 0;
		this.phoneContractSMS = 0;
	}

	public MobilePhoneContract(int contractKey, String contractPhoneNumber, String vat, int contractCustomerKey,
			int contractMinutes, LocalDate contractStartingDate, int contractDuration, double contractCost,
			boolean contractIsEBill, String contractPaymentPreference , int phoneContractWiFiData, int phoneContractSMS) {
		super(contractKey, contractPhoneNumber, vat, contractCustomerKey, contractMinutes, contractStartingDate,
				contractDuration, contractCost, contractIsEBill, contractPaymentPreference);
		this.phoneContractWiFiData = phoneContractWiFiData;
		this.phoneContractSMS = phoneContractSMS ;
	}

	public int getPhoneContractWiFiData() {
		return phoneContractWiFiData;
	}

	public void setPhoneContractWiFiData(int phoneContractWiFiData) {
		this.phoneContractWiFiData = phoneContractWiFiData;
	}

	public int getPhoneContractSMS() {
		return phoneContractSMS;
	}

	public void setPhoneContractSMS(int phoneContractSMS) {
		this.phoneContractSMS = phoneContractSMS;
	}
	
	/**
	 * Set minutes,data,sms and cost depending on this bundle
	 */
	public void mobileMinuteDataBundle() {
		setContractMinutes(400);
		setPhoneContractWiFiData(600);
		setPhoneContractSMS(200);
		setContractCost(8.5);
	}
	
	/**
	 * Print mobileMinuteDataBundle details
	 */
	private void mobileMinuteDataBundleDetails() {
		System.out.println("Details: ");
		System.out.println("400 minutes\t600 MB\t200 SMS");
		System.out.println("Cost: 8.50$ / month");
	}
	
	/**
	 * Set minutes,data,sms and cost depending on this bundle
	 */
	public void mobileMinuteDataBundlePlus() {
		setContractMinutes(1200);
		setPhoneContractWiFiData(1000);
		setPhoneContractSMS(400);
		setContractCost(12);
	}
	
	/**
	 * Print mobileMinuteDataBundlePlus details
	 */
	private void mobileMinuteDataBundlePlusDetails() {
		System.out.println("Details: ");
		System.out.println("1200 minutes\t1000 MB\t400 SMS");
		System.out.println("Cost: 12$ / month");
	}
	
	/**
	 * Set minutes,data,sms and cost depending on this bundle
	 */
	public void mobileMinuteBundle() {
		setContractMinutes(400);
		setPhoneContractWiFiData(0);
		setPhoneContractSMS(200);
		setContractCost(7.5);
	}
	
	/**
	 * Print mobileMinuteBundle bundle details
	 */
	private void mobileMinuteBundleDetails() {
		System.out.println("Details: ");
		System.out.println("400 minutes\t200 SMS");
		System.out.println("Cost: 7.50$ / month");
	}
	
	/**
	 * Set minutes,data,sms and cost depending on this bundle
	 */
	public void mobileMinuteBundlePlus() {
		setContractMinutes(1200);
		setPhoneContractWiFiData(0);
		setPhoneContractSMS(400);
		setContractCost(10);
	}
	
	/**
	 * Print mobileMinuteBundlePlus details
	 */
	private void mobileMinuteBundlePlusDetails() {
		System.out.println("Details: ");
		System.out.println("1200 minutes\t400 SMS");
		System.out.println("Cost: 10$ / month");
	}
	
	/**
	 * Set minutes,data,sms and cost depending on this bundle
	 */
	public void mobilePersonalBundle(int minutes) {
		setContractMinutes(minutes);
		double cost = minutes * 0.02;
		setPhoneContractWiFiData(2000);
		setPhoneContractSMS(600);
		setContractCost(cost);
	}
	
	/**
	 * Print mobilePersonalBundle details
	 */
	private void mobilePersonalBundleDetails() {
		System.out.println("Create your own bundle!");
		System.out.println("Cost: (0.02$ * min) / month");
		System.out.println("Extra gift:");
		System.out.println("600 SMS\t2 GB");
	}
	
	/**
	 * Print bundles and their details.
	 * 
	 */
	public void printBundles() {
		System.out.println("\nChoose one of the available bundles: \n");
		System.out.println();
		System.out.println("\n1. mobileMinuteDataBundle");
		mobileMinuteDataBundleDetails();
		System.out.println("\n2. mobileMinuteDataBundlePlus");
		mobileMinuteDataBundlePlusDetails();
		System.out.println("\n3. mobileMinuteBundle");
		mobileMinuteBundleDetails();
		System.out.println("\n4. mobileMinuteBundlePlus");
		mobileMinuteBundlePlusDetails();
		System.out.println("\n5. mobilePersonalBundle");
		mobilePersonalBundleDetails();
	}
	
	@Override
	public int whichChild() { return 1; }
	
	@Override
	public String toString() {
		return "\nContract Key: " + getContractKey() + "\tPhone Number: " + getContractPhoneNumber() + "\tVAT: " + getVat()
				+ "\nCustomer Key: " + getContractCustomerKey() + "\tMinutes: " + getContractMinutes()
				+ "\tStarting Date: " + getContractStartingDate() + "\nDuration(Months): " + getContractDuration()
				+ "\tCost: " + getContractCost() + "$" + "\tE-bill: " + isContractIsEBill()
				+ "\nPayment Preference: " + getContractPaymentPreference() + "\tWiFi Data: " + getPhoneContractWiFiData() 
				+ "\tSMS: " + getPhoneContractSMS() +"\n";
	}
}
