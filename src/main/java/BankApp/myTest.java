package BankApp;

import static org.junit.Assert.assertEquals;


import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

public class myTest {
	

	private static final String Jeremy_First_Name = "Jeremy";
	private static final String Snowfrog_Last_Name = "Snowfrog";
	private static final String Yolo_Username = "yolo";
	private static final String Pass_Password = "password";
	private static final int userId1 = 62;
	
	private static final String John_First_Name = "John";
	private static final String Mahomes_Last_Name = "Mahomes";
	private static final String Majosh_Username = "majohn";
	private static final String Password123 = "pass123";
	private static final int userId = 1234 ;
	

	private  UserDaoImp jeremy;
	private EmployeeDao john;
	
	@Before
	public void setUp() {
		jeremy = new UserDaoImp();
		jeremy.setFirstName(Jeremy_First_Name);
		jeremy.setLastName(Snowfrog_Last_Name);
		jeremy.setUsername(Yolo_Username);
		jeremy.setPassword(Pass_Password);
		jeremy.setUser_id(userId1);
		
		john = new EmployeeDaoImp();
		john.setFirstName(John_First_Name);
		john.setLastName(Mahomes_Last_Name);
		john.setUsername(Majosh_Username);
		john.setPassword(Password123);
		john.setUser_id(userId);
		
	}
	
	//customer get methods
	@Test
	public void attemptToGetCustomerFirstName(){
		String result = jeremy.getFirstName();
		String expectedOutput = "Jeremy";
		assertEquals(expectedOutput,result);
	}
	@Test
	public void attemptToGetCustomerUserId() {
		int result = jeremy.getUser_id();
		int expectedInt = userId1;
		assertEquals(expectedInt, result);
	}

	@Test
	public void attemptToGetCustomerLastName() {
		String result = jeremy.getLastName();
		String expected = "Snowfrog";
		assertEquals(expected,result);
	}
	
	@Test
	public void attemptToGetCustomerUsername() {
		String result = jeremy.getUsername();
		String expected = "yolo";
		assertEquals(expected,result);
	}
	
	@Test
	public void attemptToGetCustomerPassword() {
		String result = jeremy.getPassword();
		String expected = "password";
		assertEquals(expected,result);
	}
	
	
	//get employee methods
	@Test
	public void attemptToGetEmployeeFirstName(){
		String result = john.getFirstName();
		String expectedOutput = "John";
		assertEquals(expectedOutput,result);
	}
	@Test
	public void attemptToGetEmployeeUserId() {
		int result = john.getUser_id();
		int expectedInt = userId;
		assertEquals(expectedInt, result);
	}

	@Test
	public void attemptToGetEmployeeLastName() {
		String result = john.getLastName();
		String expected = "Mahomes";
		assertEquals(expected,result);
	}
	
	@Test
	public void attemptToGetEmployeeUsername() {
		String result = john.getUsername();
		String expected = "majohn";
		assertEquals(expected,result);
	}
	
	@Test
	public void attemptToGetEmployeePassword() {
		String result = john.getPassword();
		String expected = "pass123";
		assertEquals(expected,result);
	}
	
	
	
	//account methods for customer
	@Test
	public void attemptToGetAccounts() throws SQLException {
		int result = jeremy.getAccounts(userId1);
		int expected = 0;		
		assertEquals(expected,result);
	}
	

	@Test 
	public void attemptToCheckCheckingBalance() throws SQLException {
		int result = (int) jeremy.checkingBalance(userId1);
		int expected = -1;
		assertEquals(expected,result);
	}
	@Test
	public void attemptToCheckSavingsBalance() throws SQLException {
		int result = (int) jeremy.savingsBalance(userId1);
		int expected = -1;
		assertEquals(expected,result);
	}
	@Test
	public void attemptToDepositIntoChecking() throws SQLException {
		int result = (int) jeremy.depositChecking(423, userId1);
		int expected = -1;
		assertEquals(expected,result);
	}
	@Test
	public void attemptToWithdrawFromChecking() throws SQLException {
		int result = (int) jeremy.withdrawChecking(43, userId1);
		int expected = -1;
		assertEquals(expected,result);
	}
	@Test
	public void attemptToCheckDepositIntoSavings() throws SQLException {
		int result = (int) jeremy.depositSavings(333, userId1);
		int expected = -1;
		assertEquals(expected,result);
	}
	@Test
	public void attemptToWithdrawFromSavings() throws SQLException {
		int result = (int) jeremy.withdrawSavings(4, userId1);
		int expected = -1;
		assertEquals(expected,result);
	}
	


}
