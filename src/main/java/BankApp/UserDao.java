package BankApp;

import java.sql.SQLException;

public interface UserDao {

	
	int login(String username, String password) throws SQLException;
	double depositChecking (double amount, int checking_id)throws SQLException;
	double depositSavings(double amount, int savings_id) throws SQLException;
	double withdrawChecking(double amount, int checking_id) throws SQLException;
	double withdrawSavings(double amount, int savings_id) throws SQLException;
	double checkingBalance(int checking_id) throws SQLException;
	double savingsBalance(int savings_id) throws SQLException;
	void openAccount(int user_id, int type) throws SQLException;
	void closeAccount(int user_id, int type) throws SQLException;
	public int getAccounts(int user_id) throws SQLException;
	
	public Integer getUser_id();
	public void setUser_id(Integer user_id);
	public String getFirstName();
	public void setFirstName(String firstName);
	public String getLastName();
	public void setLastName(String lastName);
	
	public Integer getUserType();
	public void setUserType(Integer userType);
	public String getUsername();
	void setUsername(String username);
	public String getPassword();
	public void setPassword(String password);

	public void setUserInfo(int id, UserDao customer) throws SQLException;
	
	
	
}
