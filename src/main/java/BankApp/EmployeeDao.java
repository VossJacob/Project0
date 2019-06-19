package BankApp;

import java.sql.SQLException;

public interface EmployeeDao {
	
	int Employeelogin(String username, String password) throws SQLException;
	void createUser(String username, String password, String firstname, String lastname) throws SQLException;
	void seeAllBankAccounts() throws SQLException;

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

	public void setUserInfo(int id, EmployeeDao employee) throws SQLException;
}
