package BankApp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserDaoImp extends User implements UserDao {
	

	

	public double depositChecking(double amount, int user_id) throws SQLException {
		Connection con = ConnectionUtil.getConnection();
		
		double currentBalance = checkingBalance(user_id);
		if (currentBalance == -1) {
			return -1;
		}
		double newBalance = currentBalance + amount;
		
		String sql = "update accounts set balance = ? where user_id = ? and type = 1 and active = 1 ";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setDouble(1, newBalance);
		ps.setInt(2, user_id);
		ps.executeUpdate();
		
		
		double balance = checkingBalance(user_id);
		
		return balance;
	}

	public double depositSavings(double amount, int user_id) throws SQLException {
		Connection con = ConnectionUtil.getConnection();
		
		double currentBalance = savingsBalance(user_id);
		if (currentBalance == -1) {
			return -1;
		}
		double newBalance = currentBalance + amount;
		
		String sql = "update accounts set balance = ? where user_id = ? and type = 2 and active = 1";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setDouble(1, newBalance);
		ps.setInt(2, user_id);
		ps.executeUpdate();
		

		double balance = savingsBalance(user_id);
		return balance;
	}

	public double withdrawChecking(double amount, int user_id) throws SQLException {
		double currentBalance = checkingBalance(user_id);
		if (currentBalance == -1) {
			return -1;
		}
		double newBalance = currentBalance - amount;
		
		Connection con = ConnectionUtil.getConnection();
		String sql = "update accounts set balance = ? where user_id = ? and type = 1 and active = 1";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setDouble(1, newBalance);
		ps.setInt(2, user_id);
		ps.executeUpdate();
		
		double balance = checkingBalance(user_id);
		return balance;
	}

	public double withdrawSavings(double amount, int user_id) throws SQLException {
		double currentBalance = savingsBalance(user_id);
		if (currentBalance == -1) {
			return -1;
		}
		double newBalance = currentBalance - amount;
		
		Connection con = ConnectionUtil.getConnection();
		String sql = "update accounts set balance = ? where user_id = ? and type = 2 and active = 1";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setDouble(1, newBalance);
		ps.setInt(2, user_id);
		ps.executeUpdate();
		
		double balance = savingsBalance(user_id);
		return balance;
	}

	public double checkingBalance(int user_id) throws SQLException {
		Connection con = ConnectionUtil.getConnection();
		String sql2 = "select balance from accounts where user_id = ? and type = 1 and active = 1";
		PreparedStatement ps2 = con.prepareStatement(sql2);
		ps2.setInt(1, user_id);
		ResultSet rs = ps2.executeQuery();
		if (rs.next() == false) {
			System.out.println("You do not have a Checking Account at this time");
			System.out.println();
			return -1;
		}
		else {
		double balance = rs.getDouble("balance");
		return balance;
		}
		}

	public double savingsBalance(int user_id) throws SQLException {
		Connection con = ConnectionUtil.getConnection();
		String sql2 = "select balance from accounts where user_id = ? and type = 2 and active = 1";
		PreparedStatement ps2 = con.prepareStatement(sql2);
		ps2.setInt(1, user_id);
		ResultSet rs = ps2.executeQuery();
		
		if (rs.next() == false) {
			System.out.println("You do not have a Savings Account at this time");
			System.out.println();
			return -1;
		}
		double balance = rs.getDouble("balance");
		return balance;
	}

	public void openAccount(int user_id, int type) throws SQLException {
		Connection con = ConnectionUtil.getConnection();
		String sqlCheck = "select type from accounts where user_id = ? and type = ? and active = 1";
		PreparedStatement psCheck = con.prepareStatement(sqlCheck);
		psCheck.setInt(1, user_id);
		psCheck.setInt(2, type);
		ResultSet rs = psCheck.executeQuery();

		if (rs.next() == false) {
					String sql = "insert into accounts (user_id, type, active, balance)" + "values(?,?,1,0)";
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setInt(1, user_id);
					ps.setInt(2, type);
					ps.executeUpdate();
					if(type ==1) {
						System.out.println();
						System.out.println("Congrats! You have successfully opened a new Checking Account");
						System.out.println("You can now deposit into the new account");
						System.out.println();
					}
					if(type ==2 ) {
						System.out.println();
						System.out.println("Congrats! You have successfully opened a new Savings Account");
						System.out.println("You can now deposit into the new account");
						System.out.println();
					}
		}
		else {
			int typeOfOpenAccount = rs.getInt("type");
			
			if (typeOfOpenAccount == 1 & type == 1) {
				System.out.println("You cannot have more than one Checking Account open at a time.. Sorry");
			}
			else if (typeOfOpenAccount == 2 & type == 2) {
				System.out.println("You cannot have more than one Savings Account open at a time... Sorry");
			}
			else if (typeOfOpenAccount == 2 & type == 1) {
				String sql = "insert into accounts (user_id, type, active, balance)" + "values(?,?,1,0)";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1, user_id);
				ps.setInt(2, type);
				ps.executeUpdate();
				System.out.println();
				System.out.println("Congrats! You have successfully opened a new Checking Account");
				System.out.println("You can now deposit into the new account");
				System.out.println();
			}
			else if (typeOfOpenAccount == 1 & type == 2) {
				String sql = "insert into accounts (user_id, type, active, balance)" + "values(?,?,1,0)";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1, user_id);
				ps.setInt(2, type);
				ps.executeUpdate();
				System.out.println();
				System.out.println("Congrats! You have successfully opened a new Savings Account");
				System.out.println("You can now deposit into the new account");
				System.out.println();
			}
		}
		
	}

	public void closeAccount(int user_id, int type) throws SQLException {
		Connection con = ConnectionUtil.getConnection();
		String closeSql = "Update accounts set active = 0 where user_id = ? and type = ?";
		PreparedStatement ps = con.prepareStatement(closeSql);
		ps.setInt(1, user_id);
		ps.setInt(2, type);
		ps.executeUpdate();
		System.out.println();
		System.out.println("Your account has been closed");
		System.out.println();

	}
	
	public int getAccounts(int user_id) throws SQLException {
		Connection con = ConnectionUtil.getConnection();
		String sql = "select accountnum, type, balance from accounts where user_id = ? and active =1";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, user_id);
		ResultSet rs = ps.executeQuery();
		int count = 0;
		
		if (rs.next() == false) {
			System.out.println("I am sorry, but you do not have any open accounts at this time");
			System.out.println();
			return 0;
		}
		else {
			
			int firstType = rs.getInt("type");
			double balance = rs.getDouble("balance");
			if(firstType ==1) {
				System.out.println("Checking Account with a balance of:  "+balance );
				System.out.println();
				count++;
			}
			else if (firstType == 2) {
				System.out.println("Savings Account with a balance of:  "+balance );
				System.out.println();
				count++;
			}

			while (rs.next()) {
				int type = rs.getInt("type");
				double bal = rs.getDouble("balance");
				
				if(type ==1) {
					System.out.println("Checking Account with a balance of:  "+bal );
					System.out.println();
					count++;
				}
				else if (type == 2) {
					System.out.println("Savings Account with a balance of:  "+bal );
					System.out.println();
					count++;
				}
		}

}
	return count;
	}

	public int login(String username, String password) throws SQLException {
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
		
		if (userType != 1) {
			System.out.println("You do not have the necessary accesslevel to login in as a Customer");
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

	public void setUserInfo(int id, UserDao customer) throws SQLException {
			Connection con = ConnectionUtil.getConnection();
			String sql = "select * from users where user_id = ? ";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			
			customer.setUser_id(rs.getInt("user_id"));
			customer.setFirstName(rs.getString("firstname"));
			customer.setLastName(rs.getString("lastname"));
			customer.setUserType(rs.getInt("usertype"));
			customer.setUsername(rs.getString("username"));
			customer.setPassword(rs.getString("password"));
			
		
		}




}
















