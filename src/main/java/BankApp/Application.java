package BankApp;


import java.sql.SQLException;
import java.util.Scanner;

public class Application {
	
	public static void main(String[] args) throws SQLException {

		Scanner sc = new Scanner(System.in);
		final EmployeeDao employee = new EmployeeDaoImp();
		final UserDao customer = new UserDaoImp();
		
		int id;
		int accessLevel;
		boolean successfullLogIn = false;
		do {
		System.out.println();
		System.out.println("Welcome to Jacob's Banking Application!");
		System.out.println("Enter 1 if you are a Customer:");
		System.out.println("Enter 2 if you are an Employee:");
		accessLevel = sc.nextInt();
		
		if (accessLevel == 1 || accessLevel == 2) {
			
		System.out.println("Please enter your User Name:");
		String username = sc.next();
		System.out.println("Please enter your Password:");
		String password = sc.next();
		
		if (accessLevel ==1) {
			try {
			id = customer.login(username, password);
			if (id == 0) {
				System.out.println();
				System.out.println("your User Name or Password was incorrect, please try again!");
				System.out.println();
			}
			else {
				customer.setUserInfo(id, customer);
				successfullLogIn = true;
			}
		} catch (SQLException e) {};
		}
		
		
		else if (accessLevel == 2) {
			try {
			id = employee.Employeelogin(username, password);
			if (id == 0) {
				System.out.println();
				System.out.println("your User Name or Password was incorrect, please try again!");
				System.out.println();
				
			}
			else {
				employee.setUserInfo(id, employee);
				successfullLogIn = true;
			}
			
			
		}catch (SQLException e) {};
		}
		}
		else {
			System.out.println("Please enter a valid option");
		}
		}while(successfullLogIn == false);

		
		if (accessLevel == 2) {
			boolean done = false;
			do {
			System.out.println();
			System.out.println("Welcome " +employee.getFirstName().toUpperCase()+ " how may I help you today?" );
			System.out.println();
			System.out.println("Enter 1 to create a new customer:");
			System.out.println("Enter 2 to view all Bank Accounts:");
			System.out.println("Enter 3 to EXIT:");
			int answer = sc.nextInt();
			
			if (answer == 1) {
				try {
				System.out.println("Please enter customers username: ");
				String username = sc.next();
				System.out.println("Please enter customers password: ");
				String password = sc.next();
				System.out.println("Please enter customers first name: ");
				String firstname = sc.next();
				System.out.println("Please enter customers last name: ");
				String lastname = sc.next();
				System.out.println();
				employee.createUser(username, password, firstname, lastname);
			}catch(SQLException e) {};
			}
			
			else if (answer == 2) {
				try {
				employee.seeAllBankAccounts();
			}catch(SQLException e ) {};
			}
			
			else if (answer == 3) {
				System.out.println("Thank you come again!");
				done = true;
			}
			else {
				System.out.println("Please enter a valid option:");
			}
		}while (done == false);
		
		}
		if (accessLevel ==1) {
			boolean finished = false;
			do {
			System.out.println();
			System.out.println("Welcome " +customer.getFirstName().toUpperCase()+ " how may I help you today?");
			System.out.println();
			System.out.println("Enter 1 to check account balances:");
			System.out.println("Enter 2 to make a deposit");
			System.out.println("Enter 3 to make a withdraw");
			System.out.println("Enter 4 to open a new account");
			System.out.println("Enter 5 to close an account");
			System.out.println("Enter 6 to EXIT application");
			
			int answer = sc.nextInt();
			switch(answer) {
			case 1:
				try {
				while (true) {
					System.out.println();
					System.out.println("Enter 1 to see your Checking Account balance:");
					System.out.println("Enter 2 to see your Savings Account balance");
					System.out.println("Enter 3 to go back to the Main Menu:");
					int option = sc.nextInt();
				
					if (option == 1) {
						System.out.println();
						double checkBal = customer.checkingBalance(customer.getUser_id());
						if(checkBal == -1) {
							continue;
						}
						else {
							System.out.println();
							System.out.println("Your checking account balance is: " +checkBal);
							System.out.println();
						}
					}
					else if (option == 2) {
						System.out.println();
						double savBal = customer.savingsBalance(customer.getUser_id());
						if (savBal == -1) {
							continue;
						}
						else {
							System.out.println();
							System.out.println("Your savings accounts balance is: " +savBal);
							System.out.println();
						}
					}
					else if (option == 3) {
						break;
					}
					else {
						continue;
					}
				}
				}catch(SQLException e ) {};
				break;
				
				
			//make a deposit	
			case 2:
				try {
				while (true) {
					System.out.println();
					System.out.println("Enter 1 to deposit into Checking");
					System.out.println("Enter 2 to deposit into Savings");
					System.out.println("Enter 3 to EXIT to Main Menu");
					System.out.println();
					int option = sc.nextInt();	
				
					if (option == 1) {
						double checkBal = customer.checkingBalance(customer.getUser_id());
						if(checkBal == -1) {
							continue;
							}
						else {
							System.out.println();
							System.out.println("How much would you like to deposit into your Checking account?");
							double amount = sc.nextDouble();
							if (amount < 1) {
								System.out.println();
								System.out.println("You cannot deposit less than $1");
								System.out.println("Please try again");
								System.out.println();
							}
							else {
								double depBal = customer.depositChecking(amount, customer.getUser_id());
								if (depBal == -1) {
									continue;
								}
								else {
									System.out.println();
									System.out.println("Your new checking account balance is = $" +depBal);
									System.out.println();
									continue;
								}	
							}		

						}
					}
					else if (option == 2) {
						double savBal = customer.savingsBalance(customer.getUser_id());
						if (savBal == -1) {
							continue;
						}
						else {
							System.out.println();
							System.out.println("How much would you like to deposit into your Savings Account?");
							double savAmount = sc.nextDouble();
							if (savAmount <1) {
								System.out.println();
								System.out.println("You cannot deposit less than $1");
								System.out.println("Please try again");
								System.out.println();
								continue;
							}
							else {
								double depSavBal = customer.depositSavings(savAmount, customer.getUser_id());
								if (depSavBal ==-1) {
									continue;
								}
								else {
									System.out.println();
									System.out.println("Your new savings account balance is = $" +depSavBal );
									System.out.println();
									continue;
								}
							}
						}
					}
					else if (option == 3) {
						break;
					}
					else {
						continue;
					}
				}
				}catch (SQLException e) {}
				break;
				
			//make a withdraw	
			case 3:
				try {
				while(true) {
					System.out.println("Enter 1 to withdraw from Checking");
					System.out.println("Enter 2 to withdrawv from Savings");
					System.out.println("Enter 3 to EXIT to Main Menu");
					int option = sc.nextInt();	
					
					if (option == 1) {
						double checkBal = customer.checkingBalance(customer.getUser_id());
						if(checkBal == -1) {
							continue;
							}
						else {
							System.out.println();
							System.out.println("How much would you like to withdraw from your Checking account?");
							System.out.println();
							double withdrawCheckAmount = sc.nextDouble();
							double currBal = customer.checkingBalance(customer.getUser_id());
							if (withdrawCheckAmount >= currBal) {
								System.out.println();
								System.out.println("That amount is greater than your current balance");
								System.out.println("Please try again");
								System.out.println("current balance = $"+currBal);
								System.out.println();
								continue;
							}
							else if (withdrawCheckAmount < 1) {
								System.out.println();
								System.out.println("you cannot withdraw less than $1");
								System.out.println("Please try again");
								System.out.println("current balance = $"+currBal);
								System.out.println();
								continue;
							}
							else {
								double withCheckBal = customer.withdrawChecking(withdrawCheckAmount, customer.getUser_id());
								if(withCheckBal == -1) {
									continue;
								}	
								else {
									System.out.println();
									System.out.println("Your new checking account balance is = $" +withCheckBal);
									System.out.println();
									continue;
								}		
							}	
							}
						}
						
					else if (option == 2) {
						double savBal = customer.savingsBalance(customer.getUser_id());
						if (savBal == -1) {
							continue;
						}
						else {
							System.out.println("How much would you like to withdraw from your Savings account?");
							double withdrawSavingsAmount = sc.nextDouble();
							double currBal = customer.savingsBalance(customer.getUser_id());
							if (withdrawSavingsAmount >= currBal ) {
								System.out.println();
								System.out.println("That amount is greater than your current balance");
								System.out.println("Please try again");
								System.out.println("current balance = $"+currBal);
								System.out.println();
							}
							else if (withdrawSavingsAmount < 1) {
								System.out.println();
								System.out.println("you cannot withdraw less than $1");
								System.out.println("Please try again");
								System.out.println("current balance = $"+currBal);
								System.out.println();
							}
							else {
								double withSavBal = customer.withdrawSavings(withdrawSavingsAmount, customer.getUser_id());
								System.out.println("withdraw Savings Balance =" +withSavBal);
								if (withSavBal == -1) {
									continue;
								}
								else {
									System.out.println();
									System.out.println("Your new savings account balance is = $" +withSavBal);
									System.out.println();
									break;
								}
							}
						}
					}
					else if(option == 3) {
						break;
					}
					else {
						continue;
					}
				}
				}catch(SQLException e) {};
				break;
			
				//open a new account	
			case 4:
				while(true) {
					System.out.println("Enter 1 to open a checking account:");
					System.out.println("Enter 2 to open a savings account:");
					System.out.println("Enter 3 to go back to main menu");
					int option = sc.nextInt();
					if (option == 1 | option ==2) {
						customer.openAccount(customer.getUser_id(),option);
						continue;
					}
					else if (option == 3) {
						break;
					}
					else {
						System.out.println("Please enter a valid option");
						continue;
					}
				}
				break;
				
			case 5:
				try {
				while(true) {
					int numOfAccounts = customer.getAccounts(customer.getUser_id());
					if (numOfAccounts == 0) {
						break;
					}
					else {
						System.out.println("Enter 1 to delete your Checking Account: ");
						System.out.println("Enter 2 to delete your Savings Account: ");
						System.out.println("Enter 3 to go back to main menu: ");
						int option = sc.nextInt();
						if (option == 1 | option == 2) {
							try {
								customer.closeAccount(customer.getUser_id(), option);
								continue;
							}catch (SQLException e) {
								System.out.println("you tried to delete an account you do not have.");
								System.out.println("Please try again");
								continue;
							}
						}
						else if (option == 3) {
							break;
						}
						else {
							System.out.println("Please enter a valid option");
							continue;
						}
					}
				}
				}catch(SQLException e) {};
				break;
			case 6:
				System.out.println("Thank you for using our application. Have a wonderful day!");
				finished = true;
				break;
			default:
				System.out.println("The option you entered was invalid. Please try again!");
			
			}
			}while(finished == false);
			
		}
			
	sc.close();	
	}	
}
