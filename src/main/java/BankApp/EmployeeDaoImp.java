package BankApp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDaoImp extends UserDaoImp implements EmployeeDao {

	public void createUser(String username, String password, String firstname, String lastname) throws SQLException {
		
		Connection con = ConnectionUtil.getConnection();
		String sql = "insert into users (username, password, firstname, lastname, usertype)" + "values(?,?,?,?,1)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, username);
		ps.setString(2, password);
		ps.setString(3, firstname);
		ps.setString(4, lastname);
		ps.executeUpdate();
		
		System.out.println("The customer has been created successfully!");
		System.out.println();

	}

	public void seeAllBankAccounts() throws SQLException {
		Connection con = ConnectionUtil.getConnection();
		String sql = "select * from accounts where active = 1";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		if (rs.next()== false) {
			System.out.println("There are no active accounts at this time");
			System.out.println();
		}
		else {
			int user_id = rs.getInt("user_id");
			int type = rs.getInt("type");
			double bal = rs.getDouble("balance");
			System.out.println("user id = " +user_id+ " Account type = " +type+ " balance = " +bal);
			System.out.println();
			
			while (rs.next()) {
				int id = rs.getInt("user_id");
				int type2 = rs.getInt("type");
				double balance = rs.getDouble("balance");
				System.out.println("user id = " +id+ " Account type = " +type2+ " balance = " +balance);
				System.out.println();
				
			}
		}

		
		
		
	}

	public int Employeelogin(String username, String password) throws SQLException {
		Connection con = ConnectionUtil.getConnection();
		String sql = "select user_id, usertype from users where username = ? and password = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, username);
		ps.setString(2, password);
		ResultSet rs = ps.executeQuery();
		
		if (rs.next() == false) {
			return 0;
		}
		int userType = rs.getInt("userType");
		
		if (userType != 2) {
			System.out.println("You do not have the necessary accesslevel to login in as a Employee");
			System.out.println();
			return 0;
		}
		int id = rs.getInt("user_id");
		return id;
	}
	
	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUserInfo(int id, EmployeeDao employee) throws SQLException {
		Connection con = ConnectionUtil.getConnection();
		String sql = "select * from users where user_id = ? ";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		
		rs.next();
		
		employee.setUser_id(rs.getInt("user_id"));
		employee.setFirstName(rs.getString("firstname"));
		employee.setLastName(rs.getString("lastname"));
		employee.setUserType(rs.getInt("usertype"));
		employee.setUsername(rs.getString("username"));
		employee.setPassword(rs.getString("password"));
		
	
		
	
	}
	
	

}
