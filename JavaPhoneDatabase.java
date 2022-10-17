package it21966;

import it21966.Contract;
import it21966.Customer;
import it21966.LandlineContract;
import it21966.MobilePhoneContract;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

/**  JavaPhoneDatabase is a class that contains two ArrayList attributes 
 * that store the Customer Class objects and the Contract Class objects.
 * The methods to create customer, create a contract, delete a contract
 * or to view Customers information are implemented in this class along
 * with some supportive and error checking methods.
 * 
 * WARNING!DO NOT PUT SPACES DURING INPUT.
 * 
 * @author it21966
 */

public class JavaPhoneDatabase {
	private ArrayList<Customer> customerList = new ArrayList<Customer>();
	private ArrayList<Contract> contractList = new ArrayList<Contract>();
	private Scanner input = new Scanner(System.in);

	/**
	 * Create a customer and add him in the ArrayList.User should provide their
	 * information according to the printed messages.
	 * 
	 */
	public void createCustomer() {

		Customer newCustomer = new Customer();

		// VAT number
		System.out.println("\nEnter your 9 digits VAT number: (e.g. 123456789)");
		String vat = input.next();

		// Checks if the VAT consists of 9 digits.
		if (newCustomer.isVat(vat) == false) {
			System.out.println("Invalid data, VAT should have 9 digits.");
			return;
		}

		// Checks if a user with the given VAT exists.
		if (hasVat(vat) == false) {
			System.out.println("Error, a user with this VAT number already exists.");
			return;
		}
		newCustomer.setVat(vat);

		// Residential address
		System.out.println("\nEnter your residential address: (e.g AgNikolaou)");
		String address = input.next();
		newCustomer.setAddress(address);

		// ID
		System.out.println("\nEnter your ID: (e.g am123456)");
		String id = input.next();

		// Checks if the ID consists of 2 letters and 6 digits.
		if (newCustomer.isId(id) == false) {
			System.out.println("Invalid data, ID should have 2 letters and 6 digits.");
			return;
		}

		// Checks if a user with the given ID exists
		if (hasId(id) == false) {
			System.out.println("Error, a user with this ID already exists.");
			return;
		}
		newCustomer.setId(id);

		// Status
		// Check if the status status is appropriate
		System.out.println("\nChoose your status: ");
		newCustomer.statusMenu();
		if (input.hasNextInt()) {
			int status = input.nextInt();
			if (status <= 0 || status > 3) {
				System.out.println("Invalid choice, status does not exist.");
				return;
			}
			newCustomer.selectStatus(status);
		} else {
			System.out.println("Invalid data type.");
			return;
		}

		// Email
		System.out.println("\nEnter your email: ");
		String email = input.next();
		newCustomer.setEmail(email);

		// Add customer to the ArrayList
		customerList.add(newCustomer);
		
		System.out.println("Added new customer to the database: ");
		System.out.println(newCustomer.toString());
	}

	/**
	 * Creates a Contract. User should provide his vat to check if the customer
	 * exists in the database and proceed to choosing a contract.
	 * 
	 */
	public void newContract() {
		//Deletes contracts that expired
		deleteOutOfDateContracts();
		
		//Create newContract
		Contract newContract = new Contract();

		System.out.println("\nEnter your VAT number: (e.g. 123456789)");
		String vat = input.next();

		// Check if a user with this VAT number exist.
		if (hasVat(vat) == true) {
			System.out.println("Error, User with the id " + vat + " was not found.");
			return;
		}
		newContract.setVat(vat);

		//Generate Contract and Customer Key
		Random rng = new Random();
		
		int rand = 0;
		//While another user has the key, generate another
		do {
			rand = rng.nextInt(10000);
		}while(hasCustomerKey(rand) == true);
		newContract.setContractCustomerKey(rand);
		
		rand = 0;
		//While another user has the key, generate another
		do {
			rand = rng.nextInt(10000);
		}while(hasContractKey(rand) == true);
		newContract.setContractKey(rand);
		
		// Choose mobile or landline
		newContract.printContractOptions();
		if (input.hasNextInt()) {
			int contractType = input.nextInt();
			if (contractType <= 0 || contractType > 2) {
				System.out.println("Invalid choice.");
				return;
			}

			// Mobile Phone
			if (contractType == 1) {

				// Create a MobilePhoneContract and pass the data of the newContract
				MobilePhoneContract newPhoneContract = new MobilePhoneContract();
				newPhoneContract.setVat(newContract.getVat());
				newPhoneContract.setContractKey(newContract.getContractKey());
				newPhoneContract.setContractCustomerKey(newContract.getContractCustomerKey());

				// Choose the phone number.
				System.out.println("Enter your new mobile phone number: (e.g. 6909123512)");
				String mobilePhoneNumber = input.next();

				// Checks if the phone number consists of 10 digits and
				// whether the 1st digit is not 2.
				if (newPhoneContract.isMobilePhone(mobilePhoneNumber) == false) {
					System.out.println(
							"Invalid data, mobile numbers do not start with 2 and " + "should have 10 digits.");
					return;
				}

				// Checks if a user with this phone number already exists.
				if (hasPhone(mobilePhoneNumber) == false) {
					System.out.println("Error, number " + mobilePhoneNumber + " is already used.");
					return;
				}
				newPhoneContract.setContractPhoneNumber(mobilePhoneNumber);

				// Choose the duration of the contract.
				System.out.println("Choose the duration of the contract: ");
				newPhoneContract.printDurationOptions();
				if (input.hasNextInt()) {
					int duration = input.nextInt();
					if (duration <= 0 || duration > 2) {
						System.out.println("Invalid choice, this duration is not acceptable.");
						return;
					}
					newPhoneContract.selectDuration(duration);
				} else {
					System.out.println("Invalid data type.");
					return;
				}
				
				// Choose the starting date.

				System.out.println("Enter the year that you wish to start the contract: (e.g. 2021)");
				if (!input.hasNextInt()) {
					System.out.println("Invalid data type");
					return;
				}
				int year = input.nextInt();

				System.out.println("Enter the month that you wish to start the contract: (e.g. 12)");
				if (!input.hasNextInt()) {
					System.out.println("Invalid data type");
					return;
				}
				int month = input.nextInt();

				System.out.println("Enter the day that you wish to start the contract: (e.g. 31)");
				if (!input.hasNextInt()) {
					System.out.println("Invalid data type");
					return;
				}
				int dayOfMonth = input.nextInt();

				// Check if the Date has already passed.
				if (year < LocalDate.now().getYear() && month < LocalDate.now().getMonthValue()
						&& dayOfMonth < LocalDate.now().getDayOfMonth()) {
					System.out.println("Error, invalid date");
					return;
				}
				newPhoneContract.setContractStartingDate(year, month, dayOfMonth);

				// Choose bundle and cost.
				newPhoneContract.printBundles();
				if (input.hasNextInt()) {
					int phoneBundle = input.nextInt();
					if (phoneBundle <= 0 || phoneBundle > 5) {
						System.out.println("Invalid option.");
						return;
					}
					if (phoneBundle == 1)
						newPhoneContract.mobileMinuteDataBundle();
					if (phoneBundle == 2)
						newPhoneContract.mobileMinuteDataBundlePlus();
					if (phoneBundle == 3)
						newPhoneContract.mobileMinuteBundle();
					if (phoneBundle == 4)
						newPhoneContract.mobileMinuteBundlePlus();

					// Create your own bundle
					if (phoneBundle == 5) {

						// Provide minutes
						System.out.println("Enter the minutes for your personal bundle: (e.g 1000)");
						if (input.hasNextInt()) {
							int minutes = input.nextInt();
							// Check if the user provided negative minutes
							if (minutes < 0) {
								System.out.println("Error, minutes can't be negative.");
								return;
							}
							newPhoneContract.mobilePersonalBundle(minutes);
						} else {
							System.out.println("Invalid data type.");
							return;
						}

					}
				} else {
					System.out.println("Invalid data type.");
					return;
				}

				// Choose if the bill will be printed or not.
				System.out.println("Do you wish to use E-bill? (bills will be sent directly to your email)");
				System.out.println("1. Yes\n2. No");
				if (input.hasNextInt()) {
					int isEBill = input.nextInt();
					if (isEBill <= 0 || isEBill > 2) {
						System.out.println("Invalid option.");
						return;
					}
					newPhoneContract.selectEBill(isEBill);
				} else {
					System.out.println("Invalid data type.");
					return;
				}

				// Choose the payment method
				newPhoneContract.printPaymentPreference();
				if (input.hasNextInt()) {
					int paymentPreference = input.nextInt();
					if (paymentPreference <= 0 || paymentPreference > 3) {
						System.out.println("Invalid option.");
						return;
					}
					newPhoneContract.selectPaymentPreference(paymentPreference);
				} else {
					System.out.println("Invalid data type.");
					return;
				}

				// Update Discount

				// Status Discount
				for (Customer customer : customerList) {
					if (newPhoneContract.getVat().compareTo(customer.getVat()) == 0) {
						// If the customer is a professional 10% discount
						if (customer.getStatus().compareTo("Licensed professional") == 0) {
							customer.setDiscount(customer.getDiscount() + 0.10);
						}

						// If the customer is a student 15% discount
						if (customer.getStatus().compareTo("Student") == 0) {
							customer.setDiscount(customer.getDiscount() + 0.15);
						}
					}
				}

				// 5%-15% Discount(up to 3 contracts)
				customerDiscountPerContract(newPhoneContract.getVat());

				// 1000+ minute 11% Discount
				if (newPhoneContract.getContractMinutes() > 1000) {
					updateCustomerDiscount(newPhoneContract.getVat(), 0.11);
				}

				// 5% Payment Preference Discount
				if (newPhoneContract.getContractPaymentPreference().compareTo("Credit Card") == 0) {
					updateCustomerDiscount(newPhoneContract.getVat(), 0.05);
				}

				if (newPhoneContract.getContractPaymentPreference().compareTo("Standing order") == 0) {
					updateCustomerDiscount(newPhoneContract.getVat(), 0.05);
				}

				// 2% if it's E-bill
				if (newPhoneContract.isContractIsEBill() == true) {
					updateCustomerDiscount(newPhoneContract.getVat(), 0.02);
				}

				// Update Cost
				double newCost = newPhoneContract.getContractCost()
						- (findCustomerDiscount(newPhoneContract.getVat()) * newPhoneContract.getContractCost());
				newPhoneContract.setContractCost(newCost);

				// Add the Contract to the ArrayList
				contractList.add(newPhoneContract);
				System.out.println(newPhoneContract.toString());

			}

			// Landline Phone
			if (contractType == 2) {

				// Create a LandlineContract and pass the data of the newContract
				LandlineContract newPhoneContract = new LandlineContract();
				newPhoneContract.setVat(newContract.getVat());
				newPhoneContract.setContractKey(newContract.getContractKey());
				newPhoneContract.setContractCustomerKey(newContract.getContractCustomerKey());

				// Choose the phone number.
				System.out.println("Enter your new landline phone number: (e.g. 2102445111)");
				String landlinePhoneNumber = input.next();

				// Checks if the phone number consists of 10 digits and
				// whether the 1st digit is not 6.
				if (newPhoneContract.isLandline(landlinePhoneNumber) == false) {
					System.out.println(
							"Invalid data, landline numbers do not start with 6 and " + "should have 10 digits.");
					return;
				}

				// Checks if a user with this phone number already exists.
				if (hasPhone(landlinePhoneNumber) == false) {
					System.out.println("Error, number " + landlinePhoneNumber + " is already used.");
					return;
				}
				newPhoneContract.setContractPhoneNumber(landlinePhoneNumber);

				// Choose the duration of the contract.
				System.out.println("Choose the duration of the contract: ");
				newPhoneContract.printDurationOptions();
				if (input.hasNextInt()) {
					int duration = input.nextInt();
					if (duration <= 0 || duration > 2) {
						System.out.println("Invalid choice, this duration is not acceptable.");
						return;
					}
					newPhoneContract.selectDuration(duration);
				} else {
					System.out.println("Invalid data type.");
					return;
				}

				// Choose the starting date.

				System.out.println("Enter the year that you wish to start the contract: (e.g. 2021)");
				if (!input.hasNextInt()) {
					System.out.println("Invalid data type");
					return;
				}
				int year = input.nextInt();

				System.out.println("Enter the month that you wish to start the contract: (e.g. 12)");
				if (!input.hasNextInt()) {
					System.out.println("Invalid data type");
					return;
				}
				int month = input.nextInt();

				System.out.println("Enter the day that you wish to start the contract: (e.g 31)");
				if (!input.hasNextInt()) {
					System.out.println("Invalid data type");
					return;
				}
				int dayOfMonth = input.nextInt();

				// Check if the date has already passed
				if (year < LocalDate.now().getYear() && month < LocalDate.now().getMonthValue()
						&& dayOfMonth < LocalDate.now().getDayOfMonth()) {
					System.out.println("Error, invalid date");
					return;
				}
				newPhoneContract.setContractStartingDate(year, month, dayOfMonth);

				// Choose bundle and cost.
				newPhoneContract.printLandlineBundles();
				if (input.hasNextInt()) {
					int phoneBundle = input.nextInt();
					if (phoneBundle <= 0 || phoneBundle > 5) {
						System.out.println("Invalid option.");
						return;
					}
					if (phoneBundle == 1)
						newPhoneContract.landlineWithData24Bundle();
					if (phoneBundle == 2)
						newPhoneContract.landlineWithData50Bundle();
					if (phoneBundle == 3)
						newPhoneContract.landlineOnlyCallBundle();
					if (phoneBundle == 4)
						newPhoneContract.landlineOnlyCallBusinessBundle();

					// Create your own bundle
					if (phoneBundle == 5) {
						// Provide minutes
						System.out.println("Enter the minutes for your personal bundle: (e.g. 3000)");
						if (input.hasNextInt()) {
							int minutes = input.nextInt();
							// Check if the minutes are negative
							if (minutes < 0) {
								System.out.println("Error, minutes can't be negative.");
								return;
							}
							newPhoneContract.setContractMinutes(minutes);
						} else {
							System.out.println("Invalid data type.");
							return;
						}

						// Provide Internet speed(if the user wants Internet)
						System.out.println("Choose your Internet speed.");
						System.out.println("1. 24 Mbps");
						System.out.println("2. 50 Mbps");
						System.out.println("3. No Internet");
						if (input.hasNextInt()) {
							int speedChoice = input.nextInt();
							if (speedChoice <= 0 || speedChoice > 3) {
								System.out.println("Error, invalid choice.");
								return;
							}
							if (speedChoice == 1)
								newPhoneContract.setLandlinceInternetSpeed(24);
							if (speedChoice == 2)
								newPhoneContract.setLandlinceInternetSpeed(50);
							if (speedChoice == 3)
								newPhoneContract.setLandlinceInternetSpeed(0);

							// Set user's minutes, Internet and cost
							newPhoneContract.landlinePersonalBundle(newPhoneContract.getContractMinutes(),
									newPhoneContract.getLandlinceInternetSpeed());

						} else {
							System.out.println("Invalid data type.");
							return;
						}
					}
				} else {
					System.out.println("Invalid data type.");
					return;
				}

				// Choose if the bill will be printed or not.
				System.out.println("Do you wish to use E-bill? (bills will be sent directly to your email)");
				System.out.println("1. Yes\n2. No");
				if (input.hasNextInt()) {
					int isEBill = input.nextInt();
					if (isEBill <= 0 || isEBill > 2) {
						System.out.println("Invalid option.");
						return;
					}
					newPhoneContract.selectEBill(isEBill);
				} else {
					System.out.println("Invalid data type.");
					return;
				}

				// Choose the payment method
				newPhoneContract.printPaymentPreference();
				if (input.hasNextInt()) {
					int paymentPreference = input.nextInt();
					if (paymentPreference <= 0 || paymentPreference > 3) {
						System.out.println("Invalid option.");
						return;
					}
					newPhoneContract.selectPaymentPreference(paymentPreference);
				} else {
					System.out.println("Invalid data type.");
					return;
				}

				// Update Discount

				// Status Discount
				for (Customer customer : customerList) {
					if (newPhoneContract.getVat().compareTo(customer.getVat()) == 0) {
						// If the customer is a professional 10% discount
						if (customer.getStatus().compareTo("Licensed professional") == 0) {
							customer.setDiscount(customer.getDiscount() + 0.10);
						}

						// If the customer is a student 15% discount
						if (customer.getStatus().compareTo("Student") == 0) {
							customer.setDiscount(customer.getDiscount() + 0.15);
						}
					}
				}

				// 5%-15% Discount(up to 3 contracts)
				customerDiscountPerContract(newPhoneContract.getVat());

				// 1000+ minute 8% Discount
				if (newPhoneContract.getContractMinutes() > 1000) {
					updateCustomerDiscount(newPhoneContract.getVat(), 0.08);
				}

				// 5% Payment Preference Discount
				if (newPhoneContract.getContractPaymentPreference().compareTo("Credit Card") == 0) {
					updateCustomerDiscount(newPhoneContract.getVat(), 0.05);
				}

				if (newPhoneContract.getContractPaymentPreference().compareTo("Standing order") == 0) {
					updateCustomerDiscount(newPhoneContract.getVat(), 0.05);
				}

				// 2% if it's E-bill
				if (newPhoneContract.isContractIsEBill() == true) {
					updateCustomerDiscount(newPhoneContract.getVat(), 0.02);
				}

				// Update Cost
				double newCost = newPhoneContract.getContractCost()
						- (findCustomerDiscount(newPhoneContract.getVat()) * newPhoneContract.getContractCost());
				newPhoneContract.setContractCost(newCost);

				// Add the Contract to the ArrayList
				contractList.add(newPhoneContract);
				System.out.println(newPhoneContract.toString());
			}

		} else {
			System.out.println("Invalid data type.");
			return;
		}
	}

	/**
	 * Delete a contract. User provides the VAT number and the key of the contract
	 * to be deleted.
	 * 
	 */
	public void deleteContract() {

		System.out.println("\nEnter your VAT number: (e.g. 123456789)");
		String tmpVat = input.next();

		// Check if a user with this VAT number exist.
		if (hasVat(tmpVat) == true) {
			System.out.println("Error, User with the id " + tmpVat + "was not found.");
			return;
		}

		printCustomerActiveContracts(tmpVat);
		System.out.println("Enter the contract key of the contract you wish to delete: (-1 to go back)");

		// Check if the contract that user wants to delete exists.
		if (input.hasNextInt()) {
			int contractToDelete = input.nextInt();

			// Return to the menu if -1.
			if (contractToDelete == -1)
				return;

			// Check if the customer has this contract.
			if (!hasContractKey(contractToDelete, tmpVat)) {
				System.out.println("Invalid Key");
				return;
			}

			// Delete the contract.
			deleteActiveContract(contractToDelete, tmpVat);
		} else {
			System.out.println("Invalid data type.");
			return;
		}
	}

	/**
	 * Prints max,min and mean values of the minutes for each type of Contract and
	 * then user provides the VAT number to print user's active contracts
	 * 
	 */

	public void printActiveContractsAndStatistics() {
		System.out.println("Mobile Contacts Statistics: \n");

		ArrayList<Integer> mobile = new ArrayList<Integer>();
		int total = 0;
		int existingContracts = 0;
		for (Contract i : contractList) {
			if (i.whichChild() == 1) {
				mobile.add(i.getContractMinutes());
				total += i.getContractMinutes();
				existingContracts++;
			}
		}
		
		//If there are active contracts, print contract statistics
		if (existingContracts > 0) {
			Collections.sort(mobile);

			System.out.printf("Min minutes: %d\n", Collections.min(mobile));
			System.out.printf("Max minutes: %d\n", Collections.max(mobile));
			System.out.printf("Mean minutes: %d\n\n", total / mobile.size());

			System.out.println("Landline Contacts Statistics: \n");
		}
		
		//Reset
		total = 0;
		existingContracts = 0;

		ArrayList<Integer> landline = new ArrayList<Integer>();

		for (Contract i : contractList) {
			if (i.whichChild() == 2) {
				landline.add(i.getContractMinutes());
				total += i.getContractMinutes();
				existingContracts++;
			}
		}

		//If there are active contracts, print contract statistics
		if (existingContracts > 0) {
			Collections.sort(landline);

			System.out.printf("Min minutes: %d\n", Collections.min(landline));
			System.out.printf("Max minutes: %d\n", Collections.max(landline));
			System.out.printf("Mean minutes: %d", total / landline.size());
		}

		System.out.println("\nEnter your VAT number: (e.g. 123456789)");
		String vat = input.next();

		// Check if a user with this VAT number exist.
		if (hasVat(vat) == true) {
			System.out.println("Error, User with the id " + vat + "was not found.");
			return;
		}
		printCustomerActiveContracts(vat);
	}

	/**
	 * We run this method to create 2 profiles
	 * and 4 contracts as an example.
	 * 
	 */
	public void createExamples() {
		//Create and add 2 customers
		Customer customerA = new Customer("123456789", "Solomou", "AB123456", "Student", "testA@gmail.com", 0);
		Customer customerB = new Customer("987655321", "Pindou", "CD987654", "Licensed professional", "testB@gmail.com", 0);
		customerList.add(customerA);
		customerList.add(customerB);
		
		//Create and add 4 contracts
		LocalDate testDate = LocalDate.now();
		MobilePhoneContract contractA = new MobilePhoneContract(1, "6987654321", "123456789", 242, 400, testDate, 12, 8.5, true, "Credit card", 600, 200);
		MobilePhoneContract contractB = new MobilePhoneContract(2, "6987654322", "123456789", 324, 400, testDate, 24, 8.5, false, "Cash", 600, 200);
		contractList.add(contractA);
		contractList.add(contractB);
		
		LandlineContract contractC = new LandlineContract(3, "2987654234", "987655321", 21321, 2500, testDate, 12, 18.95, true, "Credit card", 24, "ADSL");
		MobilePhoneContract contractD = new MobilePhoneContract(4, "6987654555", "987655321", 32412, 1200, testDate, 24, 10, true, "Standing order", 0, 400);
		contractList.add(contractC);
		contractList.add(contractD);
	}
	
	public void print() {
		System.out.println(customerList);
	}

	public void eprint() {
		System.out.println(contractList);
	}

	/**
	 * Prints all the contracts of the person that has the vat number of the
	 * parameter.
	 * 
	 * @param vat
	 */
	public void printCustomerActiveContracts(String vat) {
		System.out.println("Current Active Contracts: ");

		int counter = 0;

		for (Contract i : contractList) {
			if (vat.compareTo(i.getVat()) == 0) {
				System.out.println(i.toString());
				counter++;
			}
		}

		if (counter == 0) {
			System.out.println("User does not have any contracts.");
		}
	}

	/**
	 * Loop through every contract in the ArrayList. If the current contract has the
	 * same contractKey as the parameter, it removes the contract. The vat parameter
	 * is used to make sure that the contract belongs to the owner.
	 * 
	 * @param contractKey
	 */
	private void deleteActiveContract(int contractKey, String vat) {
		for (Contract i : contractList) {
			if (hasVat(vat) == false && contractKey == i.getContractKey()) {
				contractList.remove(i);
				return;
			}
		}
	}

	/**
	 * Check if the VAT already exists in the ArrayList.
	 * 
	 * @param vat
	 * @return
	 */
	private boolean hasVat(String vat) {
		for (Customer i : customerList) {
			if (vat.compareTo(i.getVat()) == 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Check if the ID already exists in the ArrayList.
	 * 
	 * @param id
	 * @return
	 */
	private boolean hasId(String id) {
		for (Customer i : customerList) {
			if (id.compareTo(i.getId()) == 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Check if the phone number is already used.
	 * 
	 * @param phoneNumber
	 * @return
	 */
	private boolean hasPhone(String phoneNumber) {
		for (Contract i : contractList) {
			if (phoneNumber.compareTo(i.getContractPhoneNumber()) == 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Check if the customer's contract key exists.
	 * 
	 * @param contractKey
	 * @return
	 */
	private boolean hasContractKey(int contractKey, String vat) {
		for (Contract i : contractList) {
			if (hasVat(vat) == false && i.getContractKey() == contractKey) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Set the discount of a customer between 5% and 15% depending on the number of
	 * contracts.
	 * 
	 * @param vat
	 */
	private void customerDiscountPerContract(String vat) {
		int counter = 0;

		// Check how many contracts the customer have(up to 3)
		for (Contract i : contractList) {
			if (vat.compareTo(i.getVat()) == 0) {
				System.out.println(i.toString());
				if (counter == 3) {
					break;
				}
				counter++;
			}
		}

		// Find the customer with these contracts and set the discount(5%-15%)
		updateCustomerDiscount(vat, counter * 0.05);
	}

	/**
	 * Find the customer and update the discount.
	 * 
	 */
	private void updateCustomerDiscount(String vat, double discount) {
		for (Customer customer : customerList) {
			if (vat.compareTo(customer.getVat()) == 0) {
				customer.setDiscount(customer.getDiscount() + discount);
			}
		}
	}

	/**
	 * Returns Customer Discount
	 * 
	 * @param vat
	 * @return
	 */
	private double findCustomerDiscount(String vat) {
		for (Customer customer : customerList) {
			if (vat.compareTo(customer.getVat()) == 0) {
				return customer.getDiscount();
			}
		}
		return 0;
	}
	
	private boolean hasCustomerKey(int rand) {
		for(Contract i : contractList) {
			if(i.getContractCustomerKey() == rand) {
				return true;
			}
		}
		return false;
	}
	
	private boolean hasContractKey(int rand) {
		for(Contract i : contractList) {
			if(i.getContractKey() == rand) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Delete contacts that are out of date
	 * 
	 */
	public void deleteOutOfDateContracts() {
		for (Contract i : contractList) {
			LocalDate tmp;
			
			if(i.getContractDuration() == 12) {
				tmp = LocalDate.of(i.getContractStartingDate().getYear() + 1, i.getContractStartingDate().getMonth(), i.getContractStartingDate().getDayOfMonth());
				if(tmp.equals(LocalDate.now())) {
					contractList.remove(contractList.indexOf(i));
				}
			
			}
			if(i.getContractDuration() == 24) {
				tmp = LocalDate.of(i.getContractStartingDate().getYear() + 2, i.getContractStartingDate().getMonth(), i.getContractStartingDate().getDayOfMonth());
				if(tmp.equals(LocalDate.now())) {
					contractList.remove(contractList.indexOf(i));
				}
			}
		}		
	}
}
